package de.haevn.utils.io.merge;

import de.haevn.utils.logging.Logger;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class PdfMerge extends AbstractFileMerging {
    private static final Logger LOGGER = new Logger(PdfMerge.class);

    private static final PdfMerge INSTANCE = new PdfMerge();

    public static synchronized PdfMerge getInstance() {
        return INSTANCE;
    }

    private PdfMerge() {
    }


    @Override
    public void mergeFiles(final File output, final List<File> input) {
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
            throw new RuntimeException(e);
        }
    }
}