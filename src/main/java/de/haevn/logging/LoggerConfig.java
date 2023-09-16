package de.haevn.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Configuration for the logger.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 *
 */
public class LoggerConfig {
    private PrintStream output = System.out;
    private Level level = Level.ALL;

    /**
     * Get the current minimum log level.
     *
     * @return The minimum log level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the output stream.
     * @param logStream The output stream.
     */
    public void setOutput(PrintStream logStream) {
        this.output = logStream;
    }

    /**
     * Set the output stream.
     * @param logFile The output stream.
     */
    public void setOutput(String logFile) throws FileNotFoundException {
        this.output = new PrintStream(logFile);
    }

    /**
     * Set the output stream.
     * @param logFile The output stream.
     */
    public void setOutput(File logFile) throws FileNotFoundException {
        this.output = new PrintStream(logFile);
    }

    /**
     * Set the log level.
     *
     * @param level The minimum log level.
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Get the current output stream.
     * @return The output stream.
     */
    public PrintStream getOutput() {
        return output;
    }
}
