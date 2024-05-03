package de.haevn.utils.io;

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

public class FileUtils {
    private FileUtils() {
    }


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

    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    public static String getUserHomeWithSeparator() {
        return getUserHome() + File.separator;
    }

    public static BasicFileAttributes getInfo(final String file) {
        return getInfo(new File(file));
    }

    public static BasicFileAttributes getInfo(final File file) {
        return getInfo(file.toPath());
    }

    public static BasicFileAttributes getInfo(final Path file) {
        try {
            return Files.readAttributes(file, BasicFileAttributes.class);
        } catch (IOException e) {
            return emptyAttribute;
        }
    }

    public static LocalDateTime convertTime(final FileTime time) {
        return LocalDateTime.ofInstant(time.toInstant(), java.time.ZoneId.systemDefault());
    }


    public static void createFileIfNotExists(final String file) {
        createFileIfNotExists(new File(file));
    }

    public static void createFileIfNotExists(final Path file) {
        createFileIfNotExists(file.toFile());
    }

    public static void createFileIfNotExists(final File file) {
        if (file.exists()) {
            return;
        }
        try {
            createDirectoryIfNotExists(file.getParentFile());
            file.createNewFile();
        } catch (IOException ignored) {
        }
    }

    public static void createDirectoryIfNotExists(final File directory) {
        if (directory.exists()) {
            return;
        }
        directory.mkdirs();
    }


    public static String getRootPath() {
        return getRootPath(AppLauncher.getAppName());
    }

    public static String getRootPath(final String appName) {
        return System.getProperty("user.home") + File.separator + "haevn" + File.separator + appName;
    }

    public static String getRootPathWithSeparator() {
        return getRootPathWithSeparator(AppLauncher.getAppName());
    }

    public static String getRootPathWithSeparator(final String appName) {
        return getRootPath(appName) + File.separator;
    }

    public static void openDefaultApplication(final File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ignored) {
        }
    }


    public static URL getURI(final String path) {
        return getURL(new File(path));
    }

    public static URL getURL(final File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }


    private static final BasicFileAttributes emptyAttribute = new BasicFileAttributes() {
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
            return "";
        }
    };

}
