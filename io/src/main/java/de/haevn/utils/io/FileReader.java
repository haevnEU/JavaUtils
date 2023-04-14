package de.haevn.utils.io;

import de.haevn.annotations.LauncherUtils;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.function.Function;

public class FileReader {
    private final Logger logger = new Logger(FileReader.class);
    private final File rootFile;

    private FileReader(final File rootFile) {
        this.rootFile = rootFile;
    }

    /**
     * Returns a FileReader instance for the given directory
     *
     * @param rootFile the directory to read from
     * @return a FileReader instance
     */
    public static FileReader getFileReader(final String rootFile) {
        return new FileReader(new File(rootFile));
    }

    /**
     * Returns a FileReader instance for the application directory
     *
     * @return a FileReader instance
     */
    public static FileReader getApplicationFileReader() {
        return new FileReader(new File(LauncherUtils.getLauncher().getRootPath()));
    }


    /**
     * Reads the content of the file with the given name
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
     * Reads the content of the file with the given name as byte array
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
     * Reads the content of the file with the given name as base64 encoded string
     * @param fileName the name of the file to read
     * @return the content of the file as base64 encoded string
     */
    public byte[] readBase64(final String fileName) {
        Base64.Decoder base64 = Base64.getDecoder();
        return base64.decode(readBytes(fileName));
    }

    /**
     * Reads the content of the file with the given name as base64 encoded string
     * @param fileName the name of the file to read
     * @return the content of the file as base64 encoded string
     */
    public String readBase64String(final String fileName) {
        return new String(readBase64(fileName));
    }

    /**
     * Reads the content of the file with the given name and maps it to an object
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
