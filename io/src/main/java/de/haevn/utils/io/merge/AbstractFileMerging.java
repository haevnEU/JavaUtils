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
 * <h1>AbstractFileMerging</h1>
 * <br>
 * <p>This abstract class implements {@link IFileMerging} and implements some methods of the interface.</p>
 * <br>
 * <p>It provides the following methods:</p>
 * <ul>
 *     <li>{@link #mergeFiles(File, String...)}: Merges the given files into the output file.</li>
 *     <li>{@link #mergeFiles(File, File...)}: Merges the given files into the output file.</li>
 *     <li>{@link #readFile(File)}: Reads a file into memory.</li>
 *     <li>{@link #storeFile(File, byte[])}: Stores the given bytes into the given file.</li>
 *     <li>{@link #storeFile(File, byte[], boolean)}: Stores the given bytes into the given file.</li>
 * </ul>
 * <br>
 * <p>It also provides a logger for logging messages.</p>
 * <br>
 * <p>It is recommended to extend this class when implementing a file merging class.</p>
 */
public abstract class AbstractFileMerging implements IFileMerging {
    private static final Logger LOGGER = new Logger(AbstractFileMerging.class);

    /**
     * <h2>mergeFiles({@link File}, String...)</h2>
     * <br>
     * <p>Merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     *  final FileMerging fileMerging = new FileMerging();
     *  fileMerging.mergeFiles(new File("output.txt"), "input1.txt", "input2.txt");
     * }</pre>
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    public void mergeFiles(final File output, final String... input) throws FileMergeException {
        mergeFiles(output, Arrays.stream(input).map(File::new).toList());
    }

    /**
     * <h2>mergeFiles({@link File}, {@link File}...)</h2>
     * <br>
     * <p>Merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final FileMerging fileMerging = new FileMerging();
     * fileMerging.mergeFiles(new File("output.txt"), new File("input1.txt"), new File("input2.txt"));
     * }</pre>
     * <br>
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    public void mergeFiles(final File output, final File... input) throws FileMergeException {
        mergeFiles(output, List.of(input));
    }


    /**
     * <h2>readFile({@link File})</h2>
     * <br>
     * <p>This method reads the content of the given file into a byte array and returns it.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final FileMerging fileMerging = new FileMerging();
     * final byte[] bytes = fileMerging.readFile(new File("input.txt"));
     * }</pre>
     * <br>
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
     * <h2>storeFile({@link File}, byte[])</h2>
     * <br>
     * <p>This method stores the given bytes into the given file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final FileMerging fileMerging = new FileMerging();
     * final byte[] data = {...}
     * fileMerging.storeFile(new File("output.txt"), data);
     * }</pre>
     *
     * @param file  the file to store the bytes in
     * @param bytes the bytes to store
     * @throws IOException if an error occurs while storing the bytes
     */
    protected void storeFile(final File file, final byte[] bytes) throws IOException {
        storeFile(file, bytes, false);
    }

    /**
     * <h2>storeFile({@link File}, byte[], boolean)</h2>
     * <br>
     * <p>This method stores the given bytes into the given file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final FileMerging fileMerging = new FileMerging();
     * final byte[] data = {...}
     * fileMerging.storeFile(new File("output.txt"), data, true);
     * }</pre>
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