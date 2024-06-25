package de.haevn.utils.io.merge;

import de.haevn.utils.exceptions.FileMergeException;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;


/**
 * This class provides methods to merge files.
 */
public abstract class AbstractFileMerging implements IFileMerging {
    private static final Logger LOGGER = new Logger(AbstractFileMerging.class);

    /**
     * Merges the given files into the output file.
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    public void mergeFiles(final File output, final String... input) throws FileMergeException {
        mergeFiles(output, Arrays.stream(input).map(File::new).toList());
    }

    /**
     * Merges the given files into the output file.
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    public void mergeFiles(final File output, final File... input) throws FileMergeException {
        mergeFiles(output, List.of(input));
    }


    /**
     * Reads a file into memory
     *
     * @param file the file to read
     * @return the content of the file as a byte array
     */
    protected byte[] readFile(final File file) {
        try (final FileInputStream fis = new FileInputStream(file)) {
            final byte[] bytes = new byte[(int) file.length()];
            final int bytesRead = fis.read(bytes);
            return bytesRead > 0 ? bytes : new byte[0];
        } catch (IOException e) {
            LOGGER.atWarning().forEnclosingMethod().withMessage("Error while reading file").withException(e).log();
            return new byte[0];
        }
    }

    /**
     * Stores the given bytes into the given file
     *
     * @param file  the file to store the bytes in
     * @param bytes the bytes to store
     * @throws IOException if an error occurs while storing the bytes
     */
    protected void storeFile(final File file, final byte[] bytes) throws IOException {
        storeFile(file, bytes, false);
    }

    /**
     * Stores the given bytes into the given file
     *
     * @param file          the file to store the bytes in
     * @param bytes         the bytes to store
     * @param deleteOnExist if the file should be deleted if it already exists
     * @throws IOException if an error occurs while storing the bytes
     */
    protected void storeFile(final File file, final byte[] bytes, final boolean deleteOnExist) throws IOException {
        if (file.exists() && deleteOnExist) {
            Files.delete(file.toPath());
        }
        try (final FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }
    }


}