package de.haevn.utils.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Configuration for the logger.
 *
 * @author haevn
 * @version 1.1
 * @since 1.0
 */
public final class LoggerConfig {
    public PrintStream getFileOutput() {
        return fileOutput;
    }

    public void setFileOutput(final PrintStream fileOutput) {
        this.fileOutput = fileOutput;
    }

    public PrintStream getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(final PrintStream consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public void setAutoFlush(final boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    public boolean isUseShutdownHook() {
        return useShutdownHook;
    }

    public void setUseShutdownHook(final boolean useShutdownHook) {
        this.useShutdownHook = useShutdownHook;
    }

    public int getLogSize() {
        return logSize;
    }

    public void setLogSize(final int logSize) {
        this.logSize = logSize;
    }

    private PrintStream fileOutput;
    private PrintStream consoleOutput = System.out;
    private Level level = Level.ALL;
    private boolean autoFlush = true;
    private boolean useShutdownHook = true;
    private int logSize = 100;


    /**
     * Set the output stream.
     *
     * @param logFile The output stream.
     */
    public void setOutput(final String logFile) throws FileNotFoundException {
        setOutput(new File(logFile + System.currentTimeMillis() + ".log"));
    }

    /**
     * Set the output stream.
     *
     * @param logFile The output stream.
     */
    public void setOutput(final File logFile) throws FileNotFoundException {
        this.fileOutput = new PrintStream(new FileOutputStream(logFile, true));
    }
}
