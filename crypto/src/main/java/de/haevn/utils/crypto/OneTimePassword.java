package de.haevn.utils.crypto;

import de.haevn.utils.TimeUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;

/**
 * <h1>OneTimePassword</h1>
 * <br>
 * <br> This class provides methods to generate a One Time Password (OTP) based on the Time-based One Time Password (TOTP) algorithm.
 * <br> The TOTP algorithm is based on the HMAC-SHA algorithm and generates a 6-digit OTP.
 * <br> The class also provides methods to generate a secret key and a QR code for the secret key.
 * <br>
 * <h3>Example:</h3>
 * <pre>
 *     {@code
 *     final String secret = OneTimePassword.generateSecretKey();
 *     final String otp = OneTimePassword.generateTOTP(secret);
 *     final String otp = OneTimePassword.generateCode();
 *     final String otp = OneTimePassword.generateCode(secret);
 *     final String otp = OneTimePassword.generateCodeAndShow("MyApp");
 *     final String otp = OneTimePassword.generateCodeAndShow("MyApp", secret);
 *     }
 * </pre>
 */
public final class OneTimePassword {

    /**
     * The time step in milliseconds.
     */
    private static final long TIME_STEP = 30;
    /**
     * The length of the secret key.
     */
    private static final int SECRET_KEY_LENGTH = 20;

    private static final Enryption ALGORITHM = Enryption.SHA512;

    private OneTimePassword() {
    }

    /**
     * <h2>generateTOTP(String)</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on the given secret key.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String secret = OneTimePassword.generateSecretKey();
     *     final String otp = OneTimePassword.generateTOTP(secret);
     *     }
     * </pre>
     *
     * @param secret The secret key
     * @return The generated OTP
     */
    public static String generateTOTP(final String secret) {
        final byte[] key = Base32Util.decode(secret);
        long time = Instant.now().getEpochSecond() / TIME_STEP;
        final byte[] data = ByteBuffer.allocate(8).putLong(time).array();
        final byte[] hash = hmacSha(key, data);

        // Convert the hash to an integer value
        int offset = hash[hash.length - 1] & 0xF;
        int binary =
                ((hash[offset] & 0x7f) << 24) |
                        ((hash[offset + 1] & 0xff) << 16) |
                        ((hash[offset + 2] & 0xff) << 8) |
                        (hash[offset + 3] & 0xff);
        // Generate a 6-digit OTP
        int otp = (binary % 1000000);

        return String.format("%06d", otp);
    }

    /**
     * <h2>hmacSha(byte[], byte[])</h2>
     * <p>Generates a HMAC-SHA hash based on the given key and data.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final byte[] key = {...};
     *     final byte[] data = {...};
     *     final byte[] hash = OneTimePassword.hmacSha1(key, data);
     *     }
     * </pre>
     *
     * @param key  The key
     * @param data The data
     * @return The generated hash
     */
    private static byte[] hmacSha(final byte[] key, final byte[] data) {
        try {
            final String algorithm = ALGORITHM.algorithm;
            final Mac mac = Mac.getInstance(algorithm);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
            mac.init(secretKeySpec);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error generating TOTP", e);
        }
    }

    /**
     * <h2>generateSecretKey()</h2>
     * <p>Generates a random secret key for the TOTP algorithm.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String secret = OneTimePassword.generateSecretKey();
     *     }
     * </pre>
     *
     * @return The generated secret key
     */
    public static String generateSecretKey() {
        final SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[SECRET_KEY_LENGTH];
        random.nextBytes(bytes);
        return Base32Util.encode(bytes);
    }

    /**
     * <h2>generateCode()</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on a random secret key.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String otp = OneTimePassword.generateCode();
     *     }
     * </pre>
     *
     * @return The generated OTP
     */
    public static String generateCode() {
        return generateTOTP(generateSecretKey());
    }

    /**
     * <h2>generateCode(String)</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on the given secret key.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String secret = OneTimePassword.generateSecretKey();
     *     final String otp = OneTimePassword.generateCode(secret);
     *     }
     * </pre>
     *
     * @param secret The secret key
     * @return The generated OTP
     */
    public static String generateCode(final String secret) {
        return generateTOTP(secret);
    }

    /**
     * <h2>generateCodeAndShow(String)</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on a random secret key and shows a QR code with
     * the secret key.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String otp = OneTimePassword.generateCodeAndShow("MyApp");
     *     }
     * </pre>
     *
     * @param name The name of the application
     * @return The generated OTP
     * @throws IOException If an I/O error occurs
     */
    public static String generateCodeAndShow(final String name) throws IOException {
        return generateCodeAndShow(name, generateSecretKey());
    }

    /**
     * <h2>generateCodeAndShow(String, String)</h2>
     * <p>Generates a Time-based One Time Password (TOTP) based on the given secret key and shows a QR code with
     * the secret key.</p>
     * <p>The OTP is updated every second.</p>
     * <p>The QR code will be generated by an external service.</p>
     * <a href="https://goqr.me/api/">https://goqr.me/api/</a>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String secret = OneTimePassword.generateSecretKey();
     *     final String otp = OneTimePassword.generateCodeAndShow("MyApp", secret);
     *     }
     * </pre>
     *
     * @param name   The name of the application
     * @param secret The secret key
     * @return The generated OTP
     * @throws IOException If an I/O error occurs
     */
    public static String generateCodeAndShow(final String name, final String secret) throws IOException {
        final String totp = generateTOTP(secret);
        final String data = String.format("otpauth://totp/%s?secret=%s&algorithm=%s", name, secret, ALGORITHM.name());
        final String qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + URLEncoder.encode(data, StandardCharsets.UTF_8);
        final URI uri = URI.create(qrCodeUrl);

        final BufferedImage image = ImageIO.read(uri.toURL());
        final JTextField secretField = new JTextField(secret);
        final JLabel label = new JLabel(new ImageIcon(image));
        final JLabel otp = new JLabel();

        otp.setFont(new Font("Arial", Font.PLAIN, 20));
        secretField.setFont(new Font("Arial", Font.PLAIN, 20));
        secretField.setEditable(false);

        final JFrame frame = new JFrame();
        frame.setTitle("One Time Password Generator");
        frame.add(label, BorderLayout.CENTER);
        frame.add(secretField, BorderLayout.NORTH);
        frame.add(otp, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(450, 300);
        Thread.ofVirtual().start(() -> {
            while (frame.isVisible()) {
                String otpValue = generateTOTP(secret);
                otp.setText(otpValue);
                TimeUtils.sleepSecond(1);
            }
        });
        return totp;
    }

    /**
     * <h2>Enrypption</h2>
     * <p>Enumeration of the supported encryption algorithms.</p>
     */
    private enum Enryption {

        /**
         * @deprecated This is a deprecated algorithm and should not be used.
         */
        SHA1("HmacSHA1", "SHA-1"),
        SHA256("HmacSHA256", "SHA-256"),
        SHA512("HmacSHA512", "SHA-512");

        final String algorithm;
        final String algorithmName;

        Enryption(String algorithm, String algorithmName) {
            this.algorithm = algorithm;
            this.algorithmName = algorithmName;
        }
    }
}
