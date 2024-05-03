package de.haevn.utils.io;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;

public class ZipUtils {

    private ZipUtils() {}

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
}
