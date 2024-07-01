package de.haevn.utils.exceptions;

import java.io.File;
import java.util.List;

/**
 * <h1>FileMergeException</h1>
 * <br>
 * <p>This exception is thrown when an error occurs while merging files.</p>
 * <p>It provides information about the output file and the files that should be merged.</p>
 */
public class FileMergeException extends Exception {

    /**
     * <h2>FileMergeException({@link File}, {@link List})</h2>
     * <p>Creates a new FileMergeException with the given output file and files.</p>
     *
     * @param output The output file.
     * @param files  The files that should be merged.
     */
    public FileMergeException(final File output, List<File> files) {
        super(buildMessage(output, files));
    }

    /**
     * <h2>FileMergeException({@link File}, {@link List}, {@link Throwable})</h2>
     * <p>Creates a new FileMergeException with the given output file, files and cause.</p>
     *
     * @param output The output file.
     * @param files  The files that should be merged.
     * @param cause  The cause of the exception.
     */
    public FileMergeException(final File output, List<File> files, Throwable cause) {
        super(buildMessage(output, files), cause);
    }

    /**
     * <h2>buildMessage({@link File}, {@link List})</h2>
     * <p>This is an internal method to build the message for the exception.</p>
     *
     * @param output The output file.
     * @param files  The files that should be merged.
     * @return The message for the exception.
     */
    private static String buildMessage(final File output, final List<File> files) {
        return String.format("Error while creating file: %s based on %s", output.getName(), files.stream().map(File::getName).reduce((a, b) -> a + ", " + b).orElse(""));
    }
}
