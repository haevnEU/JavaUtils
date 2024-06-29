package de.haevn.utils.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>ICryptoUtils</h1>
 * <br>
 * <p> This interface provides an abstraction layer for decryption and encryption of data.</p>
 * <p> The data can be a {@link File}, {@link String text} or a byte array.</p>
 * <p> The interface provides a couple default operation which call the main methods {@link ICryptoUtils#encrypt(byte[])} and {@link ICryptoUtils#decrypt(byte[])}</p>
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public interface ICryptoUtils {

    /**
     * <h2>encrypt({@link File})</h2>
     * <p>Reads the content of the given file and encrypts it using {@link ICryptoUtils#encrypt(String)}.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     ICryptoUtils encryptor = new DummyEncryptor();
     *     final byte[] encrypted = encryptor.encrypt(new File("./home/test.txt"));
     *     }
     * </pre>
     *
     * @param file The file to encrypt
     * @return The encrypted content of the file
     * @throws IOException If an I/O error occurs
     */
    default byte[] encrypt(final File file) throws IOException {
        final String content = String.join("\n", Files.readAllLines(file.toPath()));
        return encrypt(content);
    }

    /**
     * <h2>encrypt(String)</h2>
     * <p>Encrypts the given text.</p>
     * <p>This method converts the given text into a byte array and calls {@link ICryptoUtils#encrypt(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     ICryptoUtils encryptor = new DummyEncryptor();
     *     final byte[] encrypted = encryptor.encrypt("Hello World");
     *     }
     * </pre>
     *
     * @param text The text to encrypt
     * @return The encrypted text
     */
    default byte[] encrypt(final String text) {
        return encrypt(text.getBytes());
    }

    /**
     * <h2>encrypt(byte[])</h2>
     * <p>Encrypts the given bytes.</p>
     * <p>This method <b>must</b> be implemented and serves as a common base</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     byte[] data = {...}
     *     ICryptoUtils encryptor = new DummyEncryptor();
     *     final byte[] encrypted = encryptor.encrypt(data);
     *     }
     * </pre>
     *
     * @param bytes The bytes to encrypt
     * @return The encrypted bytes
     */
    byte[] encrypt(final byte[] bytes);


    /**
     * <h2>decrypt({@link File})</h2>
     * <p>Reads the content of the given file and decrypts it using {@link ICryptoUtils#decrypt(String)}.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     ICryptoUtils encryptor = new DummyEncryptor();
     *     final byte[] decrypted = encryptor.decrypt(new File("./home/test.txt"));
     *     }
     * </pre>
     *
     * @param file The file to decrypt
     * @return The decrypted content of the file
     * @throws IOException If an I/O error occurs
     */
    default byte[] decrypt(final File file) throws IOException {
        final String content = String.join("\n", Files.readAllLines(file.toPath()));
        return decrypt(content);
    }

    /**
     * <h2>decrypt(String)</h2>
     * <p>Decrypts the given text.</p>
     * <p>This method converts the given text into a byte array and calls {@link ICryptoUtils#decrypt(byte[])}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     ICryptoUtils encryptor = new DummyEncryptor();
     *     final byte[] decrypted = encryptor.decrypt("Hello World");
     *     }
     * </pre>
     *
     * @param text The text to decrypt
     * @return The decrypted text
     */
    default byte[] decrypt(final String text) {
        return decrypt(text.getBytes());
    }

    /**
     * <h2>decrypt(byte[])</h2>
     * <p>Decrypts the given bytes.</p>
     * <p>This method <b>must</b> be implemented and serves as a common base</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     byte[] data = {...}
     *     ICryptoUtils encryptor = new DummyEncryptor();
     *     final byte[] decrypted = encryptor.decrypt(data);
     *     }
     * </pre>
     *
     * @param bytes The bytes to decrypt
     * @return The decrypted bytes
     */
    byte[] decrypt(final byte[] bytes);

}
