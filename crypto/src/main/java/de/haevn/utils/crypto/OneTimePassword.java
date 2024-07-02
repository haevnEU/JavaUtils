package de.haevn.utils.crypto;

import de.haevn.utils.TimeUtils;
import de.haevn.utils.exceptions.ApplicationException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;


public class OneTimePassword {
    private final Builder builder;

    /**
     * <h2>OneTimePassword({@link Builder})</h2>
     * <p>This is the internal constructor for the OneTimePassword class.</p>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * OneTimePassword otp = OneTimePassword.getInstance(Algorithm.OTP.SHA512)
     *     .setSecretKeyLength(20)
     *     .setTimeStep(30)
     *     .showQrCode(true, "MyApp")
     *     .build();
     * var key = otp.generateSecretKey();
     * otp.showToken(key);
     * }
     * </pre>
     *
     * @param builder The builder instance.
     */
    private OneTimePassword(Builder builder) {
        this.builder = builder;
    }

    public static Builder getInstance(final Algorithm.OTP algorithm) {
        return new Builder(algorithm);
    }

    /**
     * <h2>generateSecretKey()</h2>
     * <p>Generates a random secret key for the TOTP algorithm.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final OneTimePassword otp = OneTimePassword.getInstance(Algorithm.OTP.SHA512).build();
     *     final String secret = otp.generateSecretKey();
     *     }
     * </pre>
     *
     * @return The generated secret key
     */
    public String generateSecretKey() throws ApplicationException {
        final SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[builder.secretKeyLength];
        random.nextBytes(bytes);
        final String secret = Base32Util.encode(bytes);
        if (builder.showQrCode) {
            try {
                showQrCode(builder.name, secret);
            } catch (IOException ex) {
                throw new ApplicationException(ex);
            }
        }
        return secret;
    }

    /**
     * <h2>generateCode()</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on a random secret key.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final OneTimePassword otp = OneTimePassword.getInstance(Algorithm.OTP.SHA512).build();
     *     final String token = otp.generateCode();
     *     }
     * </pre>
     *
     * @return The generated OTP
     */
    public String generateCode() {
        return generateCode(generateSecretKey());
    }

    /**
     * <h2>generateCode(String)</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on the given secret key.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final OneTimePassword otp = OneTimePassword.getInstance(Algorithm.OTP.SHA512).build();
     *     final String key = otp.generateSecretKey();
     *     final String token = otp.generateCode(secret);
     *     }
     * </pre>
     *
     * @param secret The secret key
     * @return The generated OTP
     */
    public String generateCode(final String secret) throws ApplicationException {
        try {
            final byte[] key = Base32Util.decode(secret);
            long time = Instant.now().getEpochSecond() / builder.timeStep;
            final byte[] data = ByteBuffer.allocate(8).putLong(time).array();
            final byte[] hash;

            final String algorithm = builder.algorithm.algorithm;
            final Mac mac = Mac.getInstance(algorithm);

            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
            mac.init(secretKeySpec);
            hash = mac.doFinal(data);

            int offset = hash[hash.length - 1] & 0xF;
            int binary =
                    ((hash[offset] & 0x7f) << 24) |
                            ((hash[offset + 1] & 0xff) << 16) |
                            ((hash[offset + 2] & 0xff) << 8) |
                            (hash[offset + 3] & 0xff);
            int otp = (binary % 1000000);
            return String.format("%06d", otp);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new ApplicationException(ex);
        }
    }

    public void showToken(final String secret) {
        final String code = generateCode(secret);

        final JLabel secretField = new JLabel(code);
        secretField.setFont(new Font("Arial", Font.BOLD, 20));
        secretField.setHorizontalAlignment(SwingConstants.CENTER);

        final JButton btCopy = new JButton("Copy to Clipboard");
        btCopy.addActionListener(_ -> Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new java.awt.datatransfer.StringSelection(secretField.getText()), null));


        final JProgressBar progressBar = new JProgressBar();
        progressBar.setMaximum((int) builder.timeStep - 1);

        final JFrame frame = new JFrame();

        final Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(padding);
        contentPanel.add(secretField, BorderLayout.CENTER);
        contentPanel.add(progressBar, BorderLayout.NORTH);
        contentPanel.add(btCopy, BorderLayout.SOUTH);

        frame.setTitle(builder.name);
        frame.setContentPane(contentPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(250, 150);
        frame.setResizable(false);
        Thread.ofVirtual().start(() -> {
            while (frame.isVisible()) {
                secretField.setText(generateCode(secret));
                progressBar.setValue((int) (Instant.now().getEpochSecond() % builder.timeStep));
                TimeUtils.sleepMillis(500);
            }
        });
    }

    private void showQrCode(String name, String secret) throws IOException {
        final String data = String.format("otpauth://totp/%s?secret=%s&algorithm=%s", name, secret, builder.algorithm.name());
        final String qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + URLEncoder.encode(data, StandardCharsets.UTF_8);
        final URI uri = URI.create(qrCodeUrl);

        final BufferedImage image = ImageIO.read(uri.toURL());
        final JTextField secretField = new JTextField(secret);
        secretField.setHorizontalAlignment(SwingConstants.CENTER);
        final JLabel label = new JLabel(new ImageIcon(image));
        final JButton btSave = new JButton("Save QR Code");

        btSave.addActionListener(_ -> {
            try {
                ImageIO.write(image, "png", new java.io.File(name + "_token_qr.png"));
                Files.write(Paths.get(name + "_token_secret.txt"), secret.getBytes());
            } catch (IOException ignored) {
            }
        });

        secretField.setFont(new Font("Arial", Font.PLAIN, 20));
        secretField.setEditable(false);
        final Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(padding);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(secretField, BorderLayout.NORTH);
        contentPanel.add(btSave, BorderLayout.SOUTH);


        final JFrame frame = new JFrame();
        frame.setTitle("One Time Password Generator");
        frame.setContentPane(contentPanel);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(500, 350);
    }

    public static final class Builder {
        private final Algorithm.OTP algorithm;
        private long timeStep = 30;
        private int secretKeyLength = 20;
        private boolean showQrCode = false;
        private String name = "";

        /**
         * <h2>Builder(Algorithm.OTP)</h2>
         * <p>Constructor for the Builder class.</p>
         */
        private Builder(final Algorithm.OTP algorithm) {
            this.algorithm = algorithm;
        }

        /**
         * <h2>build()</h2>
         * Build the OneTimePassword instance.
         *
         * @return The OneTimePassword instance.
         */
        public OneTimePassword build() {
            return new OneTimePassword(this);
        }

        /**
         * <h2>setTimeStep(long)</h2>
         * Set the time step in seconds.
         *
         * @param timeStep The time step in seconds.
         * @return The builder instance.
         */
        public Builder setTimeStep(final long timeStep) {
            this.timeStep = timeStep;
            return this;
        }

        /**
         * <h2>setSecretKeyLength(int)</h2>
         * Set the length of the secret key.
         *
         * @param secretKeyLength The length of the secret key.
         * @return The builder instance.
         */

        public Builder setSecretKeyLength(final int secretKeyLength) {
            this.secretKeyLength = secretKeyLength;
            return this;
        }


        /**
         * <h2>showQrCode(boolean)</h2>
         * Show the QR code.
         *
         * @param showQrCode Show the QR code.
         * @return The builder instance.
         */
        public Builder showQrCode(final boolean showQrCode, final String name) {
            this.showQrCode = showQrCode;
            this.name = name;
            return this;
        }
    }


}
