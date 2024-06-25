package de.haevn.utils.crypto;

import de.haevn.annotations.Draft;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;

@Draft
public final class OneTimePassword {

    private static final long TIME_STEP = 30000L;
    private static final int SECRET_KEY_LENGTH = 20;

    public static String generateTOTP(String secret) {
        // Decode the Base64-encoded secret key
        byte[] key = Base32Util.decode(secret);

        // Get the current timestamp
        final long time = (Instant.now().toEpochMilli() / TIME_STEP);

        // Encode the time in a byte array
        final byte[] data = ByteBuffer.allocate(8).putLong(time).array();

        // Generate HMAC-SHA1 hash from the time and secret key
        final byte[] hash = hmacSha1(key, data);

        // Extract the dynamic binary code
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

    private static byte[] hmacSha1(byte[] key, byte[] data) {
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
    public static void main(String[] args) throws Exception {

        // Generate a secret key
        String secret = generateSecretKey();

        System.out.println("Secret: " + secret);

        URL url = new URL("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=otpauth://totp/Test?secret=" + secret);
        BufferedImage image = ImageIO.read(url);
        JTextField secretField = new JTextField(secret);

        JLabel label = new JLabel(new ImageIcon(image));
        JLabel otp = new JLabel();

        otp.setFont(new Font("Arial", Font.PLAIN, 20));
        secretField.setFont(new Font("Arial", Font.PLAIN, 20));
        secretField.setEditable(false);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(secretField, BorderLayout.NORTH);
        frame.getContentPane().add(otp, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocation(200, 200);
        frame.setVisible(true);


        while(true){
            String totp = generateTOTP(secret);
            System.out.println("TOTP: " + totp);
            otp.setText("TOTP: " + totp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
