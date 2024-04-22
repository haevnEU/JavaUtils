package de.haevn.utils.io;

import de.haevn.utils.AppLauncher;
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
import java.util.List;


public final class FileIO {

    private FileIO() {
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
        return System.getProperty("user.home");
    }

    public static String getUserHomeWithSeparator(){
        return getUserHome() + File.separator;
    }

    //----------------------------------------------------------------------------------------------------------------------
    //  File access methods
    //----------------------------------------------------------------------------------------------------------------------

    public static BasicFileAttributes getInfo(final File file) {
        return getInfo(file.toPath());
    }

    public static BasicFileAttributes getInfo(final Path file) {
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


    public static String readFile(final String path){
        return readFile(AppLauncher.getAppName(), path);
    }

    public static String readFile(final File file){
        return readFile(AppLauncher.getAppName(), file);
    }

    public static String readFile(final String appName, final File path){
        return readFile(appName, path.getPath());
    }
    public static String readFile(final String appName, final String path) {
        try {
            if(appName.isBlank()){
                return "";
            }
            String target = getRootPathWithSeparator(appName) + path;
            var lines = Files.readAllLines(Paths.get(target));
            return String.join("\n", lines);
        } catch (IOException e) {
            return "";
        }
    }

    public static void store(final String path, final String data) {
        store(AppLauncher.getAppName(), path, data);
    }

    public static void store(final String appName, final String path, final String data) {
        try {
            if(appName.isBlank()){
                return;
            }
            String target = getRootPathWithSeparator(appName) + path;
            createFileIfNotExists(new File(target));
            Files.write(Paths.get(target), data.getBytes());
        } catch (IOException ignored) {
        }
    }


    public static void append(final String filename, final String data) {
        append(new File(filename), data);
    }

    public static void append(final File file, final String data) {
        try {
            createFileIfNotExists(file);
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ignored) {
        }
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


    //----------------------------------------------------------------------------------------------------------------------
    // Utils methods
    //----------------------------------------------------------------------------------------------------------------------

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

    public static void zip(final File outputFile, final File... input) {
        try(final ZipFile zip = new ZipFile(outputFile)) {
            for (File file : input) {
                try {
                    zip.addFolder(file);
                } catch (ZipException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException ignored){
            // The throw should be ignored
        }
    }

    public static void construct(final List<String> directories){
        construct(AppLauncher.getAppName(), directories);
    }

    public static void construct(final String appName, final List<String> directories){
        createDirectoryIfNotExists(new File(getRootPathWithSeparator(appName)));
        for (String directory : directories) {
            createDirectoryIfNotExists(new File(getRootPathWithSeparator(appName) + directory));
        }
    }
}
