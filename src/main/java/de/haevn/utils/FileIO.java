package de.haevn.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;

import static de.haevn.Main.APP_NAME;

public final class FileIO {

    public static final File DATA_ROOT = new File(FileIO.getRootPathWithSeparator());
    public static final File JSON_DIRECTORY = new File(DATA_ROOT, "json");
    public static final File CONFIG_DIRECTORY = new File(DATA_ROOT, "config");
    public static final File STYLES_DIRECTORY = new File(DATA_ROOT, "styles");

    private FileIO() {
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Meta information
    //----------------------------------------------------------------------------------------------------------------------

    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf("\\") + 1);
    }

    public static String getFileNameWithoutExtension(String path) {
        return getFileName(path).substring(0, getFileName(path).lastIndexOf("."));
    }

    public static String getExtension(String path) {
        return getFileName(path).substring(getFileName(path).lastIndexOf(".") + 1);
    }

    public static String getDirectory(String path) {
        return path.substring(0, path.lastIndexOf("\\"));
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  File access methods
    //----------------------------------------------------------------------------------------------------------------------

    public static BasicFileAttributes getInfo(File file) {
        return getInfo(file.toPath());
    }

    public static BasicFileAttributes getInfo(Path file) {
        try {
            return Files.readAttributes(file, BasicFileAttributes.class);
        } catch (IOException e) {
            return new BasicFileAttributes() {
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
    }

    public static LocalDateTime getCreationTime(File file) {
        return getCreationTime(file.toPath());
    }

    public static LocalDateTime getCreationTime(Path file) {
        return LocalDateTime.ofInstant(getInfo(file).creationTime().toInstant(), java.time.ZoneId.systemDefault());
    }

    public static LocalDateTime getLastModifiedTime(File file) {
        return getLastModifiedTime(file.toPath());
    }

    public static LocalDateTime getLastModifiedTime(Path file) {
        return LocalDateTime.ofInstant(getInfo(file).lastModifiedTime().toInstant(), java.time.ZoneId.systemDefault());
    }


    public static String readFile(String path) {
        try {
            String target = getRootPathWithSeparator() + path;
            var lines = Files.readAllLines(Paths.get(target));
            return String.join("\n", lines);
        } catch (IOException e) {
            return "";
        }
    }

    public static void store(String path, String data) {
        try {
            String target = getRootPathWithSeparator() + path;
            createFileIfNotExists(new File(target));
            Files.write(Paths.get(target), data.getBytes());
        } catch (IOException ignored) {
        }
    }

    public static void append(String filename, String data) {
        append(new File(filename), data);
    }

    public static void append(File file, String data) {
        try {
            createFileIfNotExists(file);
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ignored) {
        }
    }


    public static void createFileIfNotExists(File file) {
        if (file.exists()) {
            return;
        }
        try {
            createDirectoryIfNotExists(file.getParentFile());
            file.createNewFile();
        } catch (IOException ignored) {
        }
    }

    public static void createDirectoryIfNotExists(File directory) {
        if (directory.exists()) {
            return;
        }
        directory.mkdirs();
    }


    //----------------------------------------------------------------------------------------------------------------------
    // Utils methods
    //----------------------------------------------------------------------------------------------------------------------

    public static URL getURI(String path) {
        return getURL(new File(path));
    }

    public static URL getURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static String getRootPath() {
        return System.getProperty("user.home") + File.separator + APP_NAME;
    }

    public static String getRootPathWithSeparator() {
        return getRootPath() + File.separator;
    }

    public static Tuple<Boolean, String> validate() {
        final String root = getRootPathWithSeparator();
        if (!Files.exists(Path.of(root))) return new Tuple<>(false, "Root path does not exist");

        if (!Files.exists(Path.of(root + "json"))) return new Tuple<>(false, "Json path does not exist");

        if (!Files.exists(Path.of(root + "config"))) return new Tuple<>(false, "Configuration path does not exist");
        if (!Files.exists(Path.of(root + "config/config.property")))
            return new Tuple<>(false, "Config file does not exist");
        if (!Files.exists(Path.of(root + "config/urls.property")))
            return new Tuple<>(false, "Urls file does not exist");
        return new Tuple<>(true, "All files exist");
    }

    public static void openDefaultApplication(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ignored) {
        }
    }

    public static void zip(File outputFile, File... input) {
        final ZipFile zip = new ZipFile(outputFile);
        for (File file : input) {
            try {
                zip.addFolder(file);
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }
    }

    public static void unzip(File inputFile, File outputDirectory) {

    }
}
