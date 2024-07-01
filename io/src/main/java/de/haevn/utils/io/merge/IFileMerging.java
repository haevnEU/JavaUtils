package de.haevn.utils.io.merge;


import de.haevn.utils.exceptions.FileMergeException;

import java.io.File;
import java.util.List;

/**
 * <h1>IFileMerging</h1>
 * <br>
 * <p>This interface provides a method signatures for merging files.</p>
 * <br>
 * <p>It provides the following methods:</p>
 * <ul>
 *     <li>{@link #mergeFiles(File, List)}: Merges the given files into the output file.</li>
 *     <li>{@link #mergeFiles(File, String...)}: Merges the given files into the output file.</li>
 *     <li>{@link #mergeFiles(File, File...)}: Merges the given files into the output file.</li>
 * </ul>
 * <br>
 * <p>It is recommended to extend the {@link AbstractFileMerging} class when implementing a file merging class.</p>
 * <br>
 */
public interface IFileMerging {


    /**
     * <h2>mergeFiles({@link File}, {@link List})</h2>
     * <br>
     * <p>Merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final IFileMerging fileMerging = ...;
     * fileMerging.mergeFiles(new File("output.txt"), List.of(new File("input1.txt"), new File("input2.txt")));
     * }</pre>
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    void mergeFiles(final File output, final List<File> input) throws FileMergeException;

    /**
     * <h2>mergeFiles({@link File}, String...)</h2>
     * <br>
     * <p>This method merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final IFileMerging fileMerging = ...;
     * fileMerging.mergeFiles(new File("output.txt"), "input1.txt", "input2.txt");
     * }</pre>
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    void mergeFiles(final File output, final String... input) throws FileMergeException;

    /**
     * <h2>mergeFiles({@link File}, {@link File}...)</h2>
     * <br>
     * <p>This method merges the given files into the output file.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * final IFileMerging fileMerging = ...;
     * fileMerging.mergeFiles(new File("output.txt"), new File("input1.txt"), new File("input2.txt"));
     * }</pre>
     *
     * @param output the output file
     * @param input  the input files
     * @throws FileMergeException if an error occurs while merging the files
     */
    void mergeFiles(final File output, final File... input) throws FileMergeException;

}