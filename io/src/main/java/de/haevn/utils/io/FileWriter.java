package de.haevn.utils.io;

import de.haevn.annotations.LauncherUtils;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class FileWriter {
    final Logger logger = new Logger(FileWriter.class);
    private final File rootFile;

    private FileWriter(final File rootFile) {
        this.rootFile = rootFile;
    }

    /**
     * Returns a FileReader instance for the given directory
     *
     * @param rootFile the directory to read from
     * @return a FileReader instance
     */
    public static FileWriter getFileWriter(final String rootFile) {
        return new FileWriter(new File(rootFile));
    }

    /**
     * Returns a FileReader instance for the application directory
     *
     * @return a FileReader instance
     */
    public static FileWriter getApplicationFileWriter() {
        return new FileWriter(new File(LauncherUtils.getLauncher().getRootPath()));
    }

    /**
     * Writes the given content to the file with the given name
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
     * Writes the given content to the file with the given name
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
     * Writes the given content as base64 to the file with the given name
     * @param fileName the name of the file to write to
     * @param data the content to write
     */
    public void writeBase64(final String fileName, final byte[] data) {
        Base64.Encoder base64 = Base64.getEncoder();
        write(fileName, base64.encode(data));
    }


    /**
     * Writes the given content as base64 to the file with the given name
     * @param fileName the name of the file to write to
     * @param data the content to write
     */
    public void writeBase64String(final String fileName, final String data) {
        writeBase64(fileName, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Appends the given content to the file with the given name
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
     * Appends the given content to the file with the given name
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
