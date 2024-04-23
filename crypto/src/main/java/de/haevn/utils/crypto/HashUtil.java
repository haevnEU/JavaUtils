package de.haevn.utils.crypto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    private final HashType type;
    private byte[] hash;

    private HashUtil(HashType type) {
        this.type = type;
    }

    public static HashUtil getInstance(final HashType type) {
        return new HashUtil(type);
    }

    public HashUtil hash(final byte[] input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance(type.algorithm);
        md.update(input);
        hash = md.digest();
        return this;
    }

    public HashUtil hash(final String input) throws NoSuchAlgorithmException {
        return hash(input.getBytes());
    }

    public HashUtil hash(final File file) throws IOException, NoSuchAlgorithmException {
        return hash(Files.readAllBytes(file.toPath()));
    }

    public Result getResult() {
        return Result.of(hash);
    }


    public record Result(String string, byte[] bytes) {
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