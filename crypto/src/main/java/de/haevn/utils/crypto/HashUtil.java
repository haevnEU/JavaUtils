package de.haevn.utils.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <h1>HashUtil</h1>
 * <p>Utility class to hash strings, byte arrays or files.</p>
 * <p>Supported algorithms: MD5, SHA-1, SHA-256, SHA-512</p>
 * <p>Example usage:</p>
 * <pre>
 * {@code
 *     HashUtil.Result result = HashUtil.getInstance(HashUtil.HashType.SHA256)
 *    .hash("Hello World!")
 *    .getResult();
 * }
 *    </pre>
 */
public class HashUtil {
    private final HashType type;
    private byte[] hash;

    /**
     * <h2>HashUtil({@link HashType})</h2>
     * <p>Private constructor to create a new HashUtil instance.</p>
     * <p>Use {@link #getInstance(HashType)} to create a new instance.</p>
     * <p>Supported algorithms: MD5, SHA-1, SHA-256, SHA-512</p>
     *
     * @param type HashType (MD5, SHA-1, SHA-256, SHA-512)
     */
    private HashUtil(HashType type) {
        this.type = type;
    }

    /**
     * <h2>getInstance({@link HashType})</h2>
     * <p>Factory method to create a new {@link HashUtil HashUtil pipe}.</p>
     * <p>Supported algorithms: MD5, SHA-1, SHA-256, SHA-512</p>
     * <h3>Example usage:</h3>
     * <pre>
     *     {@code
     *     HashUtil.Result result = HashUtil.getInstance(HashUtil.HashType.SHA256)
     *     .hash("Hello World!")
     *     .getResult();
     *     }
     * </pre>
     *
     * @param type HashType (MD5, SHA-1, SHA-256, SHA-512)
     * @return HashUtil instance
     */
    public static HashUtil getInstance(final HashType type) {
        return new HashUtil(type);
    }

    /**
     * <h2>hash(byte[])</h2>
     * <p>Hashes a byte array and returns the {@link HashUtil HashUtil pipe}.</p>
     * <h3>Example usage:</h3>
     * <pre>
     *     {@code
     *     byte[] data = "Hello World!".getBytes();
     *     HashUtil.Result result = HashUtil.getInstance(HashUtil.HashType.SHA256)
     *     .hash(data)
     *     .getResult();
     *     }
     * </pre>
     *
     * @param input byte array
     * @return HashUtil instance
     * @throws NoSuchAlgorithmException if the algorithm is not supported
     */
    public HashUtil hash(final byte[] input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance(type.algorithm);
        md.update(input);
        hash = md.digest();
        return this;
    }

    /**
     * <h2>hash(String)</h2>
     * <p>Hashes a string and returns the {@link HashUtil HashUtil pipe}.</p>
     * <h3>Example usage:</h3>
     * <pre>
     *     {@code
     *     HashUtil.Result result = HashUtil.getInstance(HashUtil.HashType.SHA256)
     *     .hash("Hello World!")
     *     .getResult();
     *     }
     * </pre>
     *
     * @param input string
     * @return HashUtil instance
     * @throws NoSuchAlgorithmException if the algorithm is not supported
     */
    public HashUtil hash(final String input) throws NoSuchAlgorithmException {
        return hash(input.getBytes());
    }

    /**
     * <h2>hash(File)</h2>
     * <p>Hashes a file and returns the {@link HashUtil HashUtil pipe}.</p>
     * <h3>Example usage:</h3>
     * <pre>
     *     {@code
     *     HashUtil.Result result = HashUtil.getInstance(HashUtil.HashType.SHA256)
     *     .hash(new File("./file.txt")
     *     .getResult();
     *     }
     * </pre>
     *
     * @param file file
     * @return HashUtil instance
     * @throws IOException if an I/O error occurs
     * @throws NoSuchAlgorithmException if the algorithm is not supported
     */
    public HashUtil hash(final File file) throws IOException, NoSuchAlgorithmException {
        return hash(Files.readAllBytes(file.toPath()));
    }

    /**
     * <h2>getResult()</h2>
     * <p>Returns the hash result as a {@link Result} object.</p>
     *
     * @return Result object
     */
    public Result getResult() {
        return Result.of(hash);
    }


    /**
     * <h1>Result</h1>
     * <p>Result object to store the hash result as a string and byte array.</p>
     */
    public record Result(String string, byte[] bytes) {
        /**
         * <h1>of(byte[])</h1>
         * <p>Factory method to create a new Result object.</p>
         *
         * @param bytes byte array
         * @return Result object
         */
        public static Result of(final byte[] bytes) {
            final StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return new Result(sb.toString(), bytes);
        }

        @Override
        public String toString() {
            return string;
        }
    }

    /**
     * <h1>HashType</h1>
     * <p>Supported hash algorithms: MD5, SHA-1, SHA-256, SHA-512</p>
     */
    public enum HashType {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA512("SHA-512");

        private final String algorithm;

        HashType(final String algorithm) {
            this.algorithm = algorithm;
        }
    }
}