package de.haevn.utils.io;

import de.haevn.annotations.LauncherUtils;

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

/**
 * <h1>FileUtils</h1>
 * <p>The FileUtils class provides methods to work with files and directories</p>
 * <p>It provides methods to get information about files, create files and directories, open files with the default application and get the root path of the application</p>
 * <p>The FileUtils can be accessed via static methods</p>
 *
 * <h2>Example 1:</h2>
 * <pre>{@code
 * String fileName = FileUtils.getFileName("path/to/file.txt");
 * }</pre>
 *
 * <h2>Example 2:</h2>
 * <pre>{@code
 * FileUtils.createFileIfNotExists("path/to/file.txt");
 * }</pre>
 *
 * @author Haevn
 * @version 1.0
 * @since 2.1
 */
public class FileUtils {
    private FileUtils() {
    }

    /**
     * <h2>getFileName(String)</h2>
     * <p>Returns the file name of the given path</p>
     *
     * @param path Path to the file
     * @return File name
     */
    public static String getFileName(final String path) {
        return path.substring(path.lastIndexOf("\\") + 1);
    }

    /**
     * <h2>getFileNameWithoutExtension(String)</h2>
     * <p>Returns the file name without extension of the given path</p>
     *
     * @param path Path to the file
     * @return File name without extension
     */
    public static String getFileNameWithoutExtension(final String path) {
        return getFileName(path).substring(0, getFileName(path).lastIndexOf("."));
    }

    /**
     * <h2>getExtension(String)</h2>
     * <p>Returns the extension of the given path</p>
     *
     * @param path Path to the file
     * @return Extension
     */
    public static String getExtension(final String path) {
        return getFileName(path).substring(getFileName(path).lastIndexOf(".") + 1);
    }

    /**
     * <h2>getDirectory(String)</h2>
     * <p>Returns the directory of the given path</p>
     *
     * @param path Path to the file
     * @return Directory
     */
    public static String getDirectory(final String path) {
        return path.substring(0, path.lastIndexOf("\\"));
    }

    /**
     * <h2>getUserHome()</h2>
     * <p>Returns the user home directory</p>
     *
     * @return User home directory
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * <h2>getUserHomeWithSeparator()</h2>
     * <p>Returns the user home directory with separator</p>
     *
     * @return User home directory with separator
     */
    public static String getUserHomeWithSeparator() {
        return getUserHome() + File.separator;
    }

    /**
     * <h2>getInfo(String)</h2>
     * <p>Returns the file {@link BasicFileAttributes attributes} of the given file</p>
     *
     * @param file Path to the file
     * @return Basic file attributes
     */
    public static BasicFileAttributes getInfo(final String file) {
        return getInfo(new File(file));
    }

    /**
     * <h2>getInfo([{@link File})</h2>
     * <p>Returns the file {@link BasicFileAttributes attributes} of the given file</p>
     *
     * @param file File
     * @return Basic file attributes
     */
    public static BasicFileAttributes getInfo(final File file) {
        return getInfo(file.toPath());
    }

    /**
     * <h2>getInfo({@link Path})</h2>
     * <p>Returns the file {@link BasicFileAttributes attributes} of the given file</p>
     *
     * @param file Path
     * @return Basic file attributes
     */
    public static BasicFileAttributes getInfo(final Path file) {
        try {
            return Files.readAttributes(file, BasicFileAttributes.class);
        } catch (IOException e) {
            return emptyAttribute;
        }
    }

    /**
     * <h2>convertTime({@link FileTime})</h2>
     * <p>Converts a {@link FileTime} to a {@link LocalDateTime}</p>
     *
     * @param time File time
     * @return Local date time
     */
    public static LocalDateTime convertTime(final FileTime time) {
        return LocalDateTime.ofInstant(time.toInstant(), java.time.ZoneId.systemDefault());
    }

    /**
     * <h2>createFileIfNotExists(String)</h2>
     * <p>Creates a file if it does not exist</p>
     *
     * @param file Path to the file
     */
    public static void createFileIfNotExists(final String file) {
        createFileIfNotExists(new File(file));
    }

    /**
     * <h2>createFileIfNotExists({@link Path})</h2>
     * <p>Creates a file if it does not exist</p>
     *
     * @param file Path to the file
     */
    public static void createFileIfNotExists(final Path file) {
        createFileIfNotExists(file.toFile());
    }

    /**
     * <h2>createFileIfNotExists({@link File})</h2>
     * <p>Creates a file if it does not exist</p>
     *
     * @param file File
     */
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

    /**
     * <h2>createDirectoryIfNotExists(String)</h2>
     * <p>Creates a directory if it does not exist</p>
     *
     * @param directory Path to the directory
     */
    public static void createDirectoryIfNotExists(final File directory) {
        if (directory.exists()) {
            return;
        }
        directory.mkdirs();
    }

    /**
     * <h2>getRootPath()</h2>
     * <p>Returns the root path of the application</p>
     *
     * @return Root path
     */
    public static String getRootPath() {
        return LauncherUtils.getLauncher().getRootPath();
    }

    /**
     * <h2>getRootPathWithSeparator()</h2>
     * <p>Returns the root path of the application with separator</p>
     *
     * @return Root path with separator
     */
    public static String getRootPathWithSeparator() {
        return getRootPath() + File.separator;
    }

    /**
     * <h2>openDefaultApplication(String)</h2>
     * <p>Opens the file with the default application</p>
     *
     * @param file Path to the file
     */
    public static void openDefaultApplication(final File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ignored) {
        }
    }

    /**
     * <h2>openDefaultApplication(String)</h2>
     * <p>Opens the file with the default application</p>
     *
     * @param path Path to the file
     */
    public static URL getURI(final String path) {
        return getURL(new File(path));
    }

    /**
     * <h2>getURL({@link File})</h2>
     * <p>Returns the URL of the given file</p>
     *
     * @param file File
     * @return URL
     */
    public static URL getURL(final File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }


    /**
     * <h2>emptyAttribute</h2>
     * <p>Empty file attributes</p>
     * <p>Used if an error occurs while reading the file attributes</p>
     * <p>It is used by several methods in the FileUtils class to prevent null pointer exceptions</p>
     */
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
