package de.haevn.utils.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * This class provides method to encode and decode data with Base64.
 * <pre>
 * {@code
 * // Encoding example
 * final String encoded = Base64Util.Encoder.encode("Hello World");
 * final String encoded = Base64Util.Encoder.encode("Hello World".getBytes());
 * final String encoded = Base64Util.Encoder.encode(new File("./home/test.txt"));
 * final String encoded = Base64Util.Encoder.encode(new FileInputStream(new File("./home/test.txt")));
 *
 *
 * final String decoded = Base64Util.Decoder.decode("SGVsbG8gV29ybGQ=");
 * final String decoded = Base64Util.Decoder.decode("SGVsbG8gV29ybGQ=".getBytes());
 * final String decoded = Base64Util.Decoder.decode(new File("./home/test.txt"));
 * final String decoded = Base64Util.Decoder.decode(new FileInputStream(new File("./home/test.txt")));
 * }
 * </pre>
 * @since 1.1
 * @version 1.1
 * @author haevn
 */
public final class Base64Util {

    private Base64Util() {
    }

    /**
     * This class provides methods to encode data with Base64.
     */
    public static class Encoder {
        private Encoder() {
        }


        /**
         * Encodes the given text.
         * @param text The text to encode
         * @return The encoded text
         */
        public static String encode(final String text) {
            return encode(text.getBytes());
        }

        /**
         * Encodes the given bytes.
         * @param bytes The bytes to encode
         * @return The encoded bytes
         */
        public static String encode(final byte[] bytes) {
            return Base64.getEncoder().encodeToString(bytes);
        }

        /**
         * Reads the content of the given file and encodes it.
         * @param file The file to encode
         * @return The encoded content of the file
         * @throws IOException If an I/O error occurs
         */
        public static String encode(final File file) throws IOException {
            return encode(new FileInputStream(file));
        }

        /**
         * Reads the content of the given file and encodes it.
         * @param fileInputStream The file to encode
         * @return The encoded content of the file
         * @throws IOException If an I/O error occurs
         */
        public static String encode(final FileInputStream fileInputStream) throws IOException {
            final byte[] data = fileInputStream.readAllBytes();
            return encode(data);
        }

        /**
         * Reads the content of the given file and encodes it.
         * @param inputStream The file to encode
         * @return The encoded content of the file
         * @throws IOException If an I/O error occurs
         */
        public static String encode(final InputStream inputStream) throws IOException {
            final byte[] data = inputStream.readAllBytes();
            return encode(data);
        }
    }

    /**
     * This class provides methods to decode data with Base64.
     */
    public static class Decoder {
        private Decoder() {
        }

        /**
         * Decodes the given text.
         * @param text The text to decode
         * @return The decoded text
         */
        public static String decode(final String text) {
            return decode(text.getBytes());
        }

        /**
         * Decodes the given bytes.
         * @param bytes The bytes to decode
         * @return The decoded bytes
         */
        public static String decode(final byte[] bytes) {
            return new String(Base64.getDecoder().decode(bytes));
        }

        /**
         * Reads the content of the given file and decodes it.
         * @param file The file to decode
         * @return The decoded content of the file
         * @throws IOException If an I/O error occurs
         */
        public static String decode(final File file) throws IOException {
            return decode(new FileInputStream(file));
        }

        /**
         * Reads the content of the given file and decodes it.
         * @param fileInputStream The file to decode
         * @return The decoded content of the file
         * @throws IOException If an I/O error occurs
         */
        public static String decode(final FileInputStream fileInputStream) throws IOException {
            final byte[] data = fileInputStream.readAllBytes();
            return decode(data);
        }

        /**
         * Reads the content of the given file and decodes it.
         * @param inputStream The file to decode
         * @return The decoded content of the file
         * @throws IOException If an I/O error occurs
         */
        public static String decode(final InputStream inputStream) throws IOException {
            final byte[] data = inputStream.readAllBytes();
            return decode(data);
        }
    }
}
