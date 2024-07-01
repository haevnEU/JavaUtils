package de.haevn.utils.io.merge;

import de.haevn.utils.exceptions.FileMergeException;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * <h1>SimpleFileMerging</h1>
 * <br>
 * <p>This class extends {@link AbstractFileMerging} and provides methods to merge files.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public final class SimpleFileMerging extends AbstractFileMerging {
    private static final Logger LOGGER = new Logger(SimpleFileMerging.class);


    /**
     * <h2>mergeFiles({@link File}, {@link List})</h2>
     * <br>
     * <p>Merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final SimpleFileMerging simpleFileMerging = new SimpleFileMerging();
     * simpleFileMerging.mergeFiles(new File("output.txt"), List.of(new File("input1.txt"), new File("input2.txt")));
     * }</pre>
     * <br>
     * <p>When the input is empty, the method will log a message and return.</p>
     * <p>Next it will create a new {@link FileOutputStream} and write the content of each input file to the output file.</p>
     * <p>If an {@link IOException} occurs, the method will log an error message and throw a {@link FileMergeException}.</p>
     *
     * @param output the output file
     * @param input  the input files
     */
    @Override
    public void mergeFiles(final File output, final List<File> input) throws FileMergeException {
        if (input.isEmpty()) {
            LOGGER.atInfo().forEnclosingMethod().withMessage("No files to merge").log();
            return;
        }

        try (final FileOutputStream fos = new FileOutputStream(output)) {
            for (final File file : input) {
                fos.write(readFile(file));
            }
        } catch (IOException e) {
            LOGGER.atError().forEnclosingMethod().withException(e).withMessage("Error while merging files").log();
            throw new FileMergeException(output, input);
        }
    }
}
