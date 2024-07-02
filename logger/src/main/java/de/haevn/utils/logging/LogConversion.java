package de.haevn.utils.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>LogConversion</h1>
 * <br>
 * <br> This class provides methods to convert log messages to different formats.
 *
 * @version 1.0
 * @since 2.1
 * @author haevn
 */
public class LogConversion {
    private LogConversion() {}

    private static String readLog(final File logFile) {
        try {
            final var lines = Files.readAllLines(logFile.toPath());
            return String.join("\n\t", lines);
        } catch (IOException e) {
            return "";
        }
    }

    private static void writeLog(final File logFile, final String log) {
        try {
            Files.writeString(logFile.toPath(), log);
        } catch (IOException ignored) {}
    }


    public static void convertToCsv(final File logFile, final File csvFile) {
        final var log = readLog(logFile).replace("\n", ",\n");
        writeLog(csvFile, log);
    }

    public static void convertToJson(final File logFile, final File jsonFile) {
        final var log = readLog(logFile).replace("\n", ",\n");
        writeLog(jsonFile, log);
    }
}
