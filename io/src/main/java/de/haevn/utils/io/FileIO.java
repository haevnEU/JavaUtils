package de.haevn.utils.io;

import de.haevn.utils.AppLauncher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static de.haevn.utils.io.FileUtils.createFileIfNotExists;
import static de.haevn.utils.io.FileUtils.getRootPathWithSeparator;

public class FileIO {
    private FileIO() {}

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
        } catch (IOException ignored) {}
    }

}
