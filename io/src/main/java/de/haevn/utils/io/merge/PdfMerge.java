package de.haevn.utils.io.merge;

import de.haevn.utils.exceptions.FileMergeException;
import de.haevn.utils.logging.Logger;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <h1>PdfMerge</h1>
 * <br>
 * <p>This class extends {@link AbstractFileMerging} and provides a method to merge PDF files.</p>
 * <br>
 * <p>It uses the {@link PDFMergerUtility} from the Apache PDFBox library to merge the files.</p>
 *
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class PdfMerge extends AbstractFileMerging {
    private static final Logger LOGGER = new Logger(PdfMerge.class);


    /**
     * <h2>mergeFiles({@link File}, {@link List})</h2>
     * <br>
     * <p>Merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final PdfMerge pdfMerge = new PdfMerge();
     * pdfMerge.mergeFiles(new File("output.pdf"), List.of(new File("input1.pdf"), new File("input2.pdf")));
     * }</pre>
     *
     * <p>When the input is empty, the method will log a message and return.</p>
     * <p>Next it will create a new {@link PDFMergerUtility} and set the destination file to the output file.</p>
     * <p>Then it will add all input files to the merger and merge the documents.</p>
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
        try {
            final PDFMergerUtility merger = new PDFMergerUtility();
            merger.setDestinationFileName(output.getAbsolutePath());
            for (File file : input) {
                merger.addSource(file);
            }

            merger.mergeDocuments(null);
        } catch (IOException e) {
            LOGGER.atError().forEnclosingMethod().withException(e).withMessage("Error while merging files").log();
            throw new FileMergeException(output, input, e);
        }
    }
}