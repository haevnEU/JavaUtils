package de.haevn.utils.io.file;

import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Optional;


public class FileIO {
    private static final Logger LOGGER = new Logger(FileIO.class);

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
     * @param file    the path to the file
     * @param content the content to store
     */
    public static boolean store(final File file, final byte[] content) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
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
     * @param file    the path to the file
     * @param content the content to append
     */
    public static void append(final File file, final byte[] content) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Files.write(file.toPath(), content, StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.atError().withException(e).withMessage("Failed to append content to %s", file).log();
        }
    }

    public static void appendLn(final File file, final String content) {
        append(file, (System.lineSeparator() + content).getBytes());
    }

    public static void appendLn(final File file, final byte[] content) {
        append(file, (System.lineSeparator()).getBytes());
        append(file, content);
    }


    /**
     * Read the content from the file at the given path.
     *
     * @param path the path to the file
     * @return the content of the file or an empty optional if the file does not exist
     */
    public static Optional<String> read(final String path) {
        return read(new File(path));
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

    public static void delete(final File file) {
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            LOGGER.atError().withException(e).withMessage("Failed to delete file %s", file.getPath()).log();
        }
    }


    public static void createFileIfNotExists(final String path) {
        createFileIfNotExists(new File(path));
    }

    public static void createFileIfNotExists(final File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.atError().withException(e).withMessage("Failed to create file %s", file.getAbsolutePath()).log();
            }
        }
    }
}
