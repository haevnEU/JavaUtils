package de.haevn.utils.io.file;

import java.io.File;

public class AppFileIO {
    private AppFileIO() {
    }

    public static void store(final String path, final String content) {
        store(path, content.getBytes());
    }

    public static void store(final String path, final byte[] content) {
        final String appRoot = FileUtils.getApplicationRootWithSeparator() + path;
        FileIO.store(new File(appRoot), content);
    }

    public static void append(final String path, final String content) {
        append(path, content.getBytes());
    }

    public static void append(final String path, final byte[] content) {
        final String appRoot = FileUtils.getApplicationRootWithSeparator() + path;
        FileIO.append(new File(appRoot), content);
    }

    public static String read(final String path) {
        final String appRoot = FileUtils.getApplicationRootWithSeparator() + path;
        return FileIO.read(appRoot).orElse("");
    }

    public static byte[] readBytes(final String path) {
        final String appRoot = FileUtils.getApplicationRootWithSeparator() + path;
        return FileIO.readBytes(appRoot).orElse(new byte[0]);
    }


}
