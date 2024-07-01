package de.haevn.utils.io;

import de.haevn.utils.exceptions.ApplicationException;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;

/**
 * <h1>ZipUtils</h1>
 * <p>The ZipUtils class provides methods to zip files and directories</p>
 * <p>It provides a method to zip files and directories</p>
 * <p>The ZipUtils can be accessed via static methods</p>
 *
 * <h2>Example:</h2>
 * <pre>{@code
 * ZipUtils.zip(new File("output.zip"), new File("path/to/file1"), new File("path/to/file2"));
 * }</pre>
 *
 * @author Haevn
 * @version 1.0
 * @since 2.1
 */
public class ZipUtils {

    private ZipUtils() {
    }

    /**
     * <h2>zip({@link File}, {@link File}...)</h2>
     * <p>Zips the given files and directories into the output file</p>
     * <p>It uses the zip4j library to zip the files and directories</p>
     *
     * @param outputFile the output file
     * @param input      the files and directories to zip
     */
    public static void zip(final File outputFile, final File... input) {
        try (final ZipFile zip = new ZipFile(outputFile)) {
            for (File file : input) {
                try {
                    zip.addFolder(file);
                } catch (ZipException ex) {
                    throw new ApplicationException(ex);
                }
            }
        } catch (IOException ignored) {
            // The throw should be ignored
        }
    }
}
