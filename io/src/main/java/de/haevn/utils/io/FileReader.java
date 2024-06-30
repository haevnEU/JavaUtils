package de.haevn.utils.io;

import de.haevn.annotations.LauncherUtils;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.function.Function;

/**
 * <h1>FileReader</h1>
 * <p>The FileReader class provides methods to read files from the file system</p>
 * <p>It provides methods to read files as string, byte array, base64 encoded string or to map the content to an object</p>
 * <p>The FileReader can be accessed via two static methods:</p>
 * <ul>
 *     <li>getFileReader(String rootFile): Returns a FileReader instance for the given directory</li>
 *     <li>getApplicationFileReader(): Returns a FileReader instance for the application directory</li>
 * </ul>
 *
 * <h2>Example 1:</h2>
 * <pre>{@code
 * FileReader fileReader = FileReader.getFileReader("path/to/directory");
 * String content = fileReader.read("file.txt");
 * }</pre>
 *
 * <h2>Example 2:</h2>
 * <pre>{@code
 * FileReader fileReader = FileReader.getApplicationFileReader();
 * String content = fileReader.read("file.txt");
 * }</pre>
 *
 * @author Haevn
 * @version 1.0
 * @since 2.1
 */
public class FileReader {
    private final Logger logger = new Logger(FileReader.class);
    private final File rootFile;

    /**
     * <h2>FileReader</h2>
     * <p>Private constructor to create a FileReader instance</p>
     *
     * @param rootFile the directory to read from
     */
    private FileReader(final File rootFile) {
        this.rootFile = rootFile;
    }

    /**
     * <h2>getFileReader(String)</h2>
     * <p>Returns a FileReader instance for the given directory</p>
     *
     * @param rootFile the directory to read from
     * @return a FileReader instance
     */
    public static FileReader getFileReader(final String rootFile) {
        return new FileReader(new File(rootFile));
    }

    /**
     * <h2>getApplicationFileReader()</h2>
     * <p>Returns a FileReader instance for the application directory specified by {@link LauncherUtils#getLauncher()#rootFile}</p>
     *
     * @return a FileReader instance
     */
    public static FileReader getApplicationFileReader() {
        return new FileReader(new File(LauncherUtils.getLauncher().getRootPath()));
    }


    /**
     * <h2>read(String)</h2>
     * <p>Reads the content of the file with the given name</p>
     * <p>It returns the content of the file as string</p>
     * <p>If an error occurs, an error message is logged and an empty string is returned</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileReader fileReader = FileReader.getFileReader("path/to/directory");
     * String content = fileReader.read("file.txt");
     * }</pre>
     *
     * @param fileName the name of the file to read
     * @return the content of the file
     */
    public String read(final String fileName) {
        try {
            return Files.readString(new File(rootFile, fileName).toPath());
        } catch (IOException e) {
            logger.atError()
                    .withMessage("Error reading file %s", fileName)
                    .withException(e)
                    .log();
            return "";
        }
    }

    /**
     * <h2>readBytes(String)</h2>
     * <p>Reads the content of the file with the given name as byte array</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileReader fileReader = FileReader.getFileReader("path/to/directory");
     * byte[] content = fileReader.readBytes("file.txt");
     * }</pre>
     * <p>If an error occurs, an error message is logged and an empty byte array is returned</p>
     *
     * @param fileName the name of the file to read
     * @return the content of the file as byte array
     */
    public byte[] readBytes(final String fileName) {
        try {
            return Files.readAllBytes(new File(rootFile, fileName).toPath());
        } catch (IOException e) {
            logger.atError()
                    .withMessage("Error reading file %s", fileName)
                    .withException(e)
                    .log();
            return new byte[0];
        }
    }


    /**
     * <h2>readBase64(String)</h2>
     * <p>Reads the content of the file with the given name as base64 encoded string</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileReader fileReader = FileReader.getFileReader("path/to/directory");
     * byte[] content = fileReader.readBase64("file.txt");
     * }</pre>
     * <p>If an error occurs, an error message is logged and an empty byte array is returned</p>
     *
     * @param fileName the name of the file to read
     * @return the content of the file as base64 encoded string
     */
    public byte[] readBase64(final String fileName) {
        Base64.Decoder base64 = Base64.getDecoder();
        return base64.decode(readBytes(fileName));
    }

    /**
     * <h2>readBase64String(String)</h2>
     * <p>Reads the content of the file with the given name as base64 encoded string</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileReader fileReader = FileReader.getFileReader("path/to/directory");
     * String content = fileReader.readBase64String("file.txt");
     * }</pre>
     * <p>If an error occurs, an error message is logged and an empty string is returned</p>
     *
     * @param fileName the name of the file to read
     * @return the content of the file as base64 encoded string
     */
    public String readBase64String(final String fileName) {
        return new String(readBase64(fileName));
    }

    /**
     * <h2>readObject(String, {@link Function})</h2>
     * <p>Reads the content of the file with the given name and maps it to an object using the given function</p>
     * <p>Example:</p>
     * <pre>{@code
     * final FileReader fileReader = FileReader.getFileReader("path/to/directory");
     * MyObject object = fileReader.readObject("file.txt", MyObject::new);
     * }</pre>
     * <p>If an error occurs, an error message is logged and null is returned</p>
     *
     * @param fileName the name of the file to read
     * @param mapper   the function to map the content to an object
     * @param <T>      the type of the object
     * @return the object
     */
    public <T> T readObject(final String fileName, Function<String, T> mapper) {
        return mapper.apply(read(fileName));
    }
}
