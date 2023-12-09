package de.haevn.javautils.logging;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Configuration for the logger.
 *
 * @author haevn
 * @version 1.1
 * @since 1.0
 */
@Getter
@Setter
public class LoggerConfig {
    private PrintStream fileOutput;
    private PrintStream consoleOutput = System.out;
    private Level level = Level.ALL;
    private boolean autoFlush = true;
    private boolean useShutdownHook = true;
    private int logSize = 100;


    public LoggerConfig() {
    }

    /**
     * Set the output stream.
     *
     * @param logStream The output stream.
     */
    public void setConsoleOutput(PrintStream logStream) {
        this.consoleOutput = logStream;
    }

    /**
     * Set the output stream.
     *
     * @param logFile The output stream.
     */
    public void setOutput(String logFile) throws FileNotFoundException {
        setOutput(new File(logFile + System.currentTimeMillis() + ".log"));
    }

    /**
     * Set the output stream.
     *
     * @param logFile The output stream.
     */
    public void setOutput(File logFile) throws FileNotFoundException {
        this.fileOutput = new PrintStream(logFile);
    }
}
