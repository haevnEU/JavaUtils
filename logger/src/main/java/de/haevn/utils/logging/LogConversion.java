package de.haevn.utils.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>LogConversion</h1>
 * <br>
 * <br> This class provides methods to convert log messages to different formats.
 *
 * @author haevn
 * @version 1.0
 * @since 2.1
 */
public class LogConversion {
    /**
     * <h2>JSON_CONVERTER</h2>
     * <p>This converter converts the log messages to a JSON array.</p>
     */
    public static final IConverter JSON_CONVERTER = logger -> {
        final var log = readLog(logger.getConfig().getLogFile()).replace("\n", ",\n\t");
        return "[\n\t" + log + "\n]";
    };

    private LogConversion() {
    }

    /**
     * <h2>readLog({@link File})</h2>
     * <p>This internal method reads the log file and returns the content as a string.</p>
     *
     * @param logFile The log file to read.
     * @return The content of the log file as a string.
     */
    private static String readLog(final File logFile) {
        try {
            final var lines = Files.readAllLines(logFile.toPath());
            return String.join("\n", lines);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * <h2>writeLog({@link Logger}, String)</h2>
     * <p>This internal method writes the log data to a new log file.</p>
     *
     * @param logger The logger to write the log data.
     * @param data   The log data to write.
     */
    private static void writeLog(final Logger logger, final String data) {
        try {
            final var logFile = logger.getConfig().getLogFile();
            final String name = logFile.getName().substring(0, logFile.getName().lastIndexOf("."));
            final File file = new File(logFile.getParent(), name + ".json");
            file.getParentFile().mkdirs();
            if (file.exists()) {
                file.delete();
            }
            Files.writeString(logFile.toPath(), data);
        } catch (IOException ignored) {
        }
    }

    /**
     * <h2>convert({@link Logger}, {@link IConverter})</h2>
     * <p>This method converts the log messages of the logger to a new format using the given converter.</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Logger logger = new Logger(new LoggerConfig());
     *     LogConversion.convert(logger, LogConversion.JSON_CONVERTER);
     * }
     * </pre>
     *
     * @param logger    The logger to convert.
     * @param converter The converter to use.
     */
    public static void convert(final Logger logger, final IConverter converter) {
        final String data = converter.convert(logger);
        writeLog(logger, data);
    }

    /**
     * <h2>IConverter</h2>
     * <p>This interface provides a method to convert log messages to a new format.</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final IConverter converter = logger -> {
     *     final var log = readLog(logger.getConfig().getLogFile()).replace("\n", ",\n\t");
     *     return "[\n\t" + log + "\n]";
     *     };
     * }
     * </pre>
     *
     * @author haevn
     * @version 1.0
     * @since 2.1
     */
    public interface IConverter {
        String convert(final Logger logger);
    }
}
