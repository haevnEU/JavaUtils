package de.haevn.utils.io;

import de.haevn.annotations.LauncherUtils;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * <h1>FileWriter</h1>
 * <p>The FileWriter class provides methods to write files to the file system</p>
 * <p>It provides methods to write files as string, byte array or base64 encoded string</p>
 * <p>The FileWriter can be accessed via two static methods:</p>
 * <ul>
 *     <li>getFileWriter(String rootFile): Returns a FileWriter instance for the given directory</li>
 *     <li>getApplicationFileWriter(): Returns a FileWriter instance for the application directory</li>
 * </ul>
 * <h2>Example 1:</h2>
 * <pre>{@code
 * FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
 * fileWriter.write("file.txt", "Hello World");
 * }</pre>
 * <h2>Example 2:</h2>
 * <pre>{@code
 * FileWriter fileWriter = FileWriter.getApplicationFileWriter();
 * fileWriter.write("file.txt", "Hello World");
 * }</pre>
 *
 * @author Haevn
 * @version 1.0
 * @since 2.1
 */
public class FileWriter {
    final Logger logger = new Logger(FileWriter.class);
    private final File rootFile;

    /**
     * <h2>FileWriter</h2>
     * <p>Private constructor to create a FileWriter instance</p>
     *
     * @param rootFile
     */
    private FileWriter(final File rootFile) {
        this.rootFile = rootFile;
    }

    /**
     * <h2>getFileWriter(String)</h2>
     * <p>Returns a FileReader instance for the given directory</p>
     *
     * @param rootFile the directory to read from
     * @return a FileReader instance
     */
    public static FileWriter getFileWriter(final String rootFile) {
        return new FileWriter(new File(rootFile));
    }

    /**
     * <h2>getApplicationFileWriter()</h2>
     * <p>Returns a FileWriter instance for the application directory specified by {@link LauncherUtils#getLauncher()#rootFile}</p>
     *
     * @return a FileWriter instance
     */
    public static FileWriter getApplicationFileWriter() {
        return new FileWriter(new File(LauncherUtils.getLauncher().getRootPath()));
    }

    /**
     * <h2>write(String, String)</h2>
     * <p>Writes the given content to the file with the given name</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
     * fileWriter.write("file.txt", "Hello World");
     * }</pre>
     *
     * @param fileName the name of the file to write to
     * @param content  the content to write
     */
    public void write(final String fileName, final String content) {
        try {
            Files.writeString(new File(rootFile, fileName).toPath(), content);
            logger.atInfo().withMessage("Wrote content to file %s", fileName).log();
        } catch (IOException e) {
            logger.atError()
                    .withMessage("Error writing to file %s", fileName)
                    .withException(e)
                    .withObject(content)
                    .log();
        }
    }

    /**
     * <h2>write(String, byte[])</h2>
     * <p>Writes the given content to the file with the given name</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
     * final byte[] data = {...}
     * fileWriter.write("file.txt", data);
     * }</pre>
     *
     * @param fileName the name of the file to write to
     * @param content  the content to write
     */
    public void write(final String fileName, final byte[] content) {
        try {
            Files.write(new File(rootFile, fileName).toPath(), content);
            logger.atInfo().withMessage("Wrote content to file %s", fileName).log();
        } catch (IOException e) {
            logger.atError()
                    .withMessage("Error writing to file %s", fileName)
                    .withException(e)
                    .withObject(content)
                    .log();
        }
    }

    /**
     * <h2>writeBase64(String, byte[])</h2>
     * <p>Writes the given content as base64 to the file with the given name</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
     * final byte[] data = {...}
     * fileWriter.writeBase64("file.txt", data);
     * }</pre>
     *
     * @param fileName the name of the file to write to
     * @param data     the content to write
     */
    public void writeBase64(final String fileName, final byte[] data) {
        Base64.Encoder base64 = Base64.getEncoder();
        write(fileName, base64.encode(data));
    }


    /**
     * <h2>writeBase64String(String, String)</h2>
     * <p>Writes the given content as base64 to the file with the given name</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
     * fileWriter.writeBase64String("file.txt", "Hello World");
     * }</pre>
     *
     * @param fileName the name of the file to write to
     * @param data     the content to write
     */
    public void writeBase64String(final String fileName, final String data) {
        writeBase64(fileName, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * <h2>append(String, String)</h2>
     * <p>Appends the given content to the file with the given name</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
     * fileWriter.append("file.txt", "Hello World");
     * }</pre>
     *
     * @param fileName the name of the file to append to
     * @param content  the content to append
     */
    public void append(final String fileName, final String content) {
        try {
            Files.writeString(new File(rootFile, fileName).toPath(), content, StandardOpenOption.APPEND);
            logger.atInfo().withMessage("Appended content to file %s", fileName).log();
        } catch (IOException e) {
            logger.atError()
                    .withMessage("Error appending to file %s", fileName)
                    .withException(e)
                    .withObject(content)
                    .log();
        }
    }

    /**
     * <h2>append(String, byte[])</h2>
     * <p>Appends the given content to the file with the given name</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileWriter fileWriter = FileWriter.getFileWriter("path/to/directory");
     * final byte[] data = {...}
     * fileWriter.append("file.txt", data);
     * }</pre>
     *
     * @param fileName the name of the file to append to
     * @param content  the content to append
     */
    public void append(final String fileName, final byte[] content) {
        try {
            Files.write(new File(rootFile, fileName).toPath(), content, StandardOpenOption.APPEND);
            logger.atInfo().withMessage("Appended content to file %s", fileName).log();
        } catch (IOException e) {
            logger.atError()
                    .withMessage("Error appending to file %s", fileName)
                    .withException(e)
                    .withObject(content)
                    .log();
        }
    }

}
