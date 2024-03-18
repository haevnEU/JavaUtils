package de.haevn.utils.io.merge;

import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * This class provides methods to merge basic files. Extends {@link AbstractFileMerging}
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public final class SimpleFileMerging extends AbstractFileMerging {
    private static final Logger LOGGER = new Logger(SimpleFileMerging.class);

    private static final SimpleFileMerging INSTANCE = new SimpleFileMerging();

    public static synchronized SimpleFileMerging getInstance() {
        return INSTANCE;
    }

    private SimpleFileMerging() {
    }


    public void mergeFiles(final File output, final List<File> input) {
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
            throw new RuntimeException(e);
        }
    }
}
