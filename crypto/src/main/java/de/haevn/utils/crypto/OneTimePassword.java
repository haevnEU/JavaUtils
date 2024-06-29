package de.haevn.utils.crypto;

import de.haevn.annotations.Draft;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;

@Draft
public final class OneTimePassword {

    private static final long TIME_STEP = 30_000L;
    private static final int SECRET_KEY_LENGTH = 20;

    public static String generateTOTP(final String secret) {
        byte[] key = Base32Util.decode(secret);
        final long time = (Instant.now().toEpochMilli() / TIME_STEP);

        final byte[] data = ByteBuffer.allocate(8).putLong(time).array();
        final byte[] hash = hmacSha1(key, data);

        final int offset = hash[hash.length - 1] & 0xF;
        final int binaryCode = (hash[offset] & 0x7F) << 24 |
                (hash[offset + 1] & 0xFF) << 16 |
                (hash[offset + 2] & 0xFF) << 8 |
                (hash[offset + 3] & 0xFF);

        // Generate a 6-digit OTP
        final int otp = binaryCode % 1_000_000;

        // Format the OTP to ensure it's 6 digits
        return String.format("%06d", otp);
    }

    private static byte[] hmacSha1(final byte[] key, final byte[] data) {
        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA1");
            final Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error generating TOTP", e);
        }
    }

    public static String generateSecretKey() {
        final SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[SECRET_KEY_LENGTH];
        random.nextBytes(bytes);
        return Base32Util.encode(bytes);
    }


    public static String generateCode() {
        return generateTOTP(generateSecretKey());
    }

    public static String generateCode(final String secret) {
        return generateTOTP(secret);
    }

    public static String generateCodeAndShow(final String name) throws IOException {
        return generateCodeAndShow(name, generateSecretKey());
    }

    public static String generateCodeAndShow(final String name, final String secret) throws IOException {
        final String totp = generateTOTP(secret);

        final String qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=otpauth://totp/%s?secret=%s";
        final URI uri = URI.create(String.format(qrCodeUrl, name, secret));

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
                otp.setText(generateTOTP(secret));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return totp;
    }
}
