package de.haevn.utils.exceptions;

import java.io.File;
import java.util.List;

public class FileMergeException extends Exception{
public FileMergeException(final File output, List<File> files) {
        super(buildMessage(output, files));
    }

    public FileMergeException(final File output, List<File> files, Throwable cause) {
        super(buildMessage(output, files), cause);
    }

    private static String buildMessage(final File output, final List<File> files){
        return String.format("Error while creating file: %s based on %s", output.getName(), files.stream().map(File::getName).reduce((a, b) -> a + ", " + b).orElse(""));
    }
}
