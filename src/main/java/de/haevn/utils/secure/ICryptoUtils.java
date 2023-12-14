package de.haevn.utils.secure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This interface provides an abstraction layer for decryption and encryption of data.
 * @since 1.1
 * @version 1.1
 * @author haevn
 */
public interface ICryptoUtils {

    /**
     * Reads the content of the given file and encrypts it.
     * @param file The file to encrypt
     * @return The encrypted content of the file
     * @throws IOException If an I/O error occurs
     */
    default byte[] encrypt(final File file) throws IOException{
        final String content = String.join("\n", Files.readAllLines(file.toPath()));
        return encrypt(content);
    }

    /**
     * Encrypts the given text.
     * @param text The text to encrypt
     * @return The encrypted text
     */
    default byte[] encrypt(final String text){
        return encrypt(text.getBytes());
    }

    /**
     * Encrypts the given bytes.
     * @param bytes The bytes to encrypt
     * @return The encrypted bytes
     */
    byte[] encrypt(final byte[] bytes);


    /**
     * Reads the content of the given file and decrypts it.
     * @param file The file to decrypt
     * @return The decrypted content of the file
     * @throws IOException If an I/O error occurs
     */
    default byte[] decrypt(final File file) throws IOException{
        final String content = String.join("\n", Files.readAllLines(file.toPath()));
        return decrypt(content);
    }

    /**
     * Decrypts the given text.
     * @param text The text to decrypt
     * @return The decrypted text
     */
    default byte[] decrypt(final String text){
        return decrypt(text.getBytes());
    }

    /**
     * Decrypts the given bytes.
     * @param bytes The bytes to decrypt
     * @return The decrypted bytes
     */
    byte[] decrypt(final byte[] bytes);

}
