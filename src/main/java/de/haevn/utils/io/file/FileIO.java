package de.haevn.utils.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static de.haevn.utils.AppLauncher.LOGGER;

public class FileIO {
    private FileIO() {
    }


    /**
     * Store the content to the file at the given path.
     * if the file does not exist, it will be created.
     *
     * @param path    the path to the file
     * @param content the content to store
     */
    public static boolean store(final File path, final String content) {
        return store(path, content.getBytes());
    }

    /**
     * Store the content to the file at the given path.
     * if the file does not exist, it will be created.
     *
     * @param path    the path to the file
     * @param content the content to store
     */
    public static boolean store(final File file, final byte[] content) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(file.toPath(), content);
            return true;
        } catch (IOException e) {
            LOGGER.atError().withException(e).withMessage("Failed to store content to %s", file).log();
            return false;
        }

    }

    /**
     * Append the content to the file at the given path.
     * if the file does not exist, it will be created.
     *
     * @param path    the path to the file
     * @param content the content to append
     */
    public static void append(final File path, final String content) {
        append(path, content.getBytes());
    }

    /**
     * Append the content to the file at the given path.
     * if the file does not exist, it will be created.
     *
     * @param path    the path to the file
     * @param content the content to append
     */
    public static void append(final File file, final byte[] content) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(file.toPath(), content);
        } catch (IOException e) {
            LOGGER.atError().withException(e).withMessage("Failed to append content to %s", file).log();
        }
    }

    /**
     * Read the content from the file at the given path.
     *
     * @param path the path to the file
     * @return the content of the file or an empty optional if the file does not exist
     */
    public static Optional<String> read(final String path) {
        return read(path);
    }

    /**
     * Read the content from the file at the given path.
     *
     * @param file the file to read
     * @return the content of the file or an empty optional if the file does not exist
     */
    public static Optional<String> read(final File file) {
        try {
            final String data = Files.readString(file.toPath());
            return Optional.of(data);
        } catch (IOException e) {
            LOGGER.atError().withException(e).withMessage("Failed to read content from %s", file.getPath()).log();
            return Optional.empty();
        }
    }

    /**
     * Read the content from the file at the given path.
     *
     * @param path the path to the file
     * @return the content of the file or an empty optional if the file does not exist
     */
    public static Optional<byte[]> readBytes(final String path) {
        return readBytes(new File(path));
    }

    /**
     * Read the content from the file at the given path.
     *
     * @param file the file to read
     * @return the content of the file or an empty optional if the file does not exist
     */
    public static Optional<byte[]> readBytes(final File file) {
        try {
            final byte[] data = Files.readAllBytes(file.toPath());
            return Optional.of(data);
        } catch (IOException e) {
            LOGGER.atError().withException(e).withMessage("Failed to read content from %s", file.getPath()).log();
            return Optional.empty();
        }
    }


    public static void createFileIfNotExists(final String path) {
        final File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.atError().withException(e).withMessage("Failed to create file %s", path).log();
            }
        }
    }
}
