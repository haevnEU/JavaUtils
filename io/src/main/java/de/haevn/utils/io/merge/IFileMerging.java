package de.haevn.utils.io.merge;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is a skeleton class for file merging.
 */
public interface IFileMerging {


    /**
     * Merges the given files into the output file.
     *
     * @param output the output file
     * @param input  the input files
     * @throws IOException if an error occurs while merging the files
     */
    void mergeFiles(final File output, final List<File> input);

    /**
     * Merges the given files into the output file.
     *
     * @param output the output file
     * @param input  the input files
     * @throws IOException if an error occurs while merging the files
     */
    void mergeFiles(File output, String... input);

    /**
     * Merges the given files into the output file.
     *
     * @param output the output file
     * @param input  the input files
     * @throws IOException if an error occurs while merging the files
     */
    void mergeFiles(File output, File... input);

}