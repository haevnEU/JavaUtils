package de.haevn.utils.io.file;

import de.haevn.utils.AppLauncher;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

import static de.haevn.utils.AppLauncher.LOGGER;

/**
 * This class provides utility methods for file operations.
 *
 * @author haevn
 * @version 1.0
 * @since 1.2
 */
public class FileUtils {
    private FileUtils() {
    }

    public static <T> T convert(final String path, Function<String, T> conversion) {
        return conversion.apply(path);
    }

    public static <T> T convert(final File path, Function<File, T> conversion) {
        return conversion.apply(path);
    }

    public static Optional<URL> getURL(final String file) {
        return getURL(new File(file));
    }

    public static Optional<URL> getURL(final File file) {
        try {
            final var result = file.toURI().toURL();
            return Optional.of(result);
        } catch (MalformedURLException e) {
            LOGGER.atDebug().withMessage("Failed to convert %s to URL", file.getName()).log();
            return Optional.empty();
        }
    }

    public static String getApplicationRoot() {
        return System.getProperty("user.home") + File.separator + "haevn" + File.separator + AppLauncher.getAppName();
    }

    public static String getApplicationRootWithSeparator() {
        return getApplicationRoot() + File.separator;
    }


    public static void openDefaultApplication(final File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ignored) {
            LOGGER.atError().withMessage("Failed to open %s", file.getName()).log();
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Meta information
    //----------------------------------------------------------------------------------------------------------------------

    public static String getFileName(final String path) {
        return path.substring(path.lastIndexOf("\\") + 1);
    }

    public static String getFileNameWithoutExtension(final String path) {
        return getFileName(path).substring(0, getFileName(path).lastIndexOf("."));
    }

    public static String getExtension(final String path) {
        return getFileName(path).substring(getFileName(path).lastIndexOf(".") + 1);
    }

    public static String getDirectory(final String path) {
        return path.substring(0, path.lastIndexOf("\\"));
    }

    public static String getUserHome(){
        return java.lang.System.getProperty("user.home");
    }

    public static String getUserHomeWithSeparator(){
        return getUserHome() + File.separator;
    }

    public static BasicFileAttributes getInfo(final File file) {
        return getInfo(file.toPath());
    }

    public static BasicFileAttributes getInfo(final Path file) {
        try {
            return Files.readAttributes(file, BasicFileAttributes.class);
        } catch (IOException e) {
            return new SimpleFileAttributes();
        }
    }

    public static LocalDateTime getCreationTime(final File file) {
        return getCreationTime(file.toPath());
    }

    public static LocalDateTime getCreationTime(final Path file) {
        return LocalDateTime.ofInstant(getInfo(file).creationTime().toInstant(), java.time.ZoneId.systemDefault());
    }

    public static LocalDateTime getLastModifiedTime(final File file) {
        return getLastModifiedTime(file.toPath());
    }

    public static LocalDateTime getLastModifiedTime(final Path file) {
        return LocalDateTime.ofInstant(getInfo(file).lastModifiedTime().toInstant(), java.time.ZoneId.systemDefault());
    }



    public static class SimpleFileAttributes implements BasicFileAttributes {

        @Override
        public FileTime lastModifiedTime() {
            return FileTime.fromMillis(0);
        }

        @Override
        public FileTime lastAccessTime() {
            return FileTime.fromMillis(0);
        }

        @Override
        public FileTime creationTime() {
            return FileTime.fromMillis(0);
        }

        @Override
        public boolean isRegularFile() {
            return false;
        }

        @Override
        public boolean isDirectory() {
            return false;
        }

        @Override
        public boolean isSymbolicLink() {
            return false;
        }

        @Override
        public boolean isOther() {
            return false;
        }

        @Override
        public long size() {
            return 0;
        }

        @Override
        public Object fileKey() {
            return new Object();
        }
    }
}
