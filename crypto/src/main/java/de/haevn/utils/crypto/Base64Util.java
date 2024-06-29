package de.haevn.utils.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * <h1>Base64Util</h1>
 * <br>
 * <br> This class provides method to encode and decode data with Base64.
 * <br> The data can be a text, a byte array or a file.
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
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public final class Base64Util {

    private Base64Util() {
    }


    /**
     * <h2>encode(byte[])</h2>
     * <p>Encodes the given byte array.</p>
     *
     * @param bytes The bytes to encode
     * @return The encoded bytes
     */
    public static String encode(final byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * <h2>encode(String)</h2>
     * <p>Encodes the given text.</p>
     * <p>This method converts the given text into a byte array and calls {@link Base64Util#encode(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.encode("Hello World");
     *     }
     * </pre>
     *
     * @param text The text to encode
     * @return The encoded text
     */
    public static String encode(final String text) {
        return encode(text.getBytes());
    }

    /**
     * <h2>encode({@link File})</h2>
     * <p>Reads the content of the given file and encodes it.</p>
     * <p>This method create a new {@link FileInputStream} and calls {@link Base64Util#encode(InputStream)}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.encode(new File("./home/test.txt"));
     *     }
     * </pre>
     *
     * @param file The file to encode
     * @return The encoded content of the file
     * @throws IOException If an I/O error occurs
     */
    public static String encode(final File file) throws IOException {
        return encode(new FileInputStream(file));
    }

    /**
     * <h2>encode({@link FileInputStream})</h2>
     * <p>Reads the content of the given {@link FileInputStream} and encodes it.</p>
     * <p>This method reads all bytes from the {@link FileInputStream} and calls {@link Base64Util#encode(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.encode(new FileInputStream(new File("./home/test.txt")));
     *     }
     * </pre>
     *
     * @param fileInputStream The file to encode
     * @return The encoded content of the file
     * @throws IOException If an I/O error occurs
     */
    public static String encode(final FileInputStream fileInputStream) throws IOException {
        final byte[] data = fileInputStream.readAllBytes();
        return encode(data);
    }

    /**
     * <h2>encode({@link InputStream})</h2>
     * <p>Reads the content of the given {@link InputStream} and encodes it.</p>
     * <p>This method reads all bytes from the {@link InputStream} and calls {@link Base64Util#encode(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.encode(new new DataInputStream(new FileInputStream(data)));
     *     Base64Util.encode(new new DataInputStream(new ByteArrayInputStream(data)));
     *     }
     * </pre>
     *
     * @param inputStream The file to encode
     * @return The encoded content of the file
     * @throws IOException If an I/O error occurs
     */
    public static String encode(final InputStream inputStream) throws IOException {
        final byte[] data = inputStream.readAllBytes();
        return encode(data);
    }

    /**
     * <h2>decode(byte[])</h2>
     * <p>Decodes the given bytes.</p>
     *
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     byte[] data = {...}
     *     Base64Util.decode(data);
     *     }
     * </pre>
     *
     * @param bytes The bytes to decode
     * @return The decoded bytes
     */
    public static String decode(final byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes));
    }

    /**
     * <h2>decode(String)</h2>
     * <p>Decodes the given text.</p>
     * <p>This method converts the given text into a byte array and calls {@link Base64Util#decode(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.decode("SGVsbG8gV29ybGQ=");
     *     }
     * </pre>
     *
     * @param text The text to decode
     * @return The decoded text
     */
    public static String decode(final String text) {
        return decode(text.getBytes());
    }

    /**
     * <h2>decode({@link File})</h2>
     * <p>Reads the content of the given file and decodes it.</p>
     * <p>This method create a new {@link FileInputStream} and calls {@link Base64Util#decode(InputStream)}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.decode(new File("./home/test.txt"));
     *     }
     * </pre>
     *
     * @param file The file to decode
     * @return The decoded content of the file
     * @throws IOException If an I/O error occurs
     */
    public static String decode(final File file) throws IOException {
        return decode(new FileInputStream(file));
    }

    /**
     * <h2>decode({@link FileInputStream})</h2>
     * <p>Reads the content of the given file and decodes it.</p>
     * <p>This method reads all bytes from the {@link FileInputStream} and calls {@link Base64Util#decode(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.decode(new FileInputStream(new File("./home/test.txt")));
     *     }
     * </pre>
     *
     * @param fileInputStream The file to decode
     * @return The decoded content of the file
     * @throws IOException If an I/O error occurs
     */
    public static String decode(final FileInputStream fileInputStream) throws IOException {
        final byte[] data = fileInputStream.readAllBytes();
        return decode(data);
    }

    /**
     * <h2>decode({@link InputStream})</h2>
     * <p>Reads the content of the given file and decodes it.</p>
     * <p>This method reads all bytes from the {@link InputStream} and calls {@link Base64Util#decode(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     Base64Util.decode(new new DataInputStream(new FileInputStream(data)));
     *     Base64Util.decode(new new DataInputStream(new ByteArrayInputStream(data)));
     *     }
     * </pre>
     *
     * @param inputStream The file to decode
     * @return The decoded content of the file
     * @throws IOException If an I/O error occurs
     */
    public static String decode(final InputStream inputStream) throws IOException {
        final byte[] data = inputStream.readAllBytes();
        return decode(data);
    }
}
