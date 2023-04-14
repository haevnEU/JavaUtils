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

    public void setFileOutput(PrintStream fileOutput) {
        this.fileOutput = fileOutput;
    }

    public PrintStream getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(PrintStream consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public void setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    public boolean isUseShutdownHook() {
        return useShutdownHook;
    }

    public void setUseShutdownHook(boolean useShutdownHook) {
        this.useShutdownHook = useShutdownHook;
    }

    public int getLogSize() {
        return logSize;
    }

    public void setLogSize(int logSize) {
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
    public void setOutput(String logFile) throws FileNotFoundException {
        setOutput(new File(logFile + System.currentTimeMillis() + ".log"));
    }

    /**
     * Set the output stream.
     *
     * @param logFile The output stream.
     */
    public void setOutput(File logFile) throws FileNotFoundException {
        this.fileOutput = new PrintStream(new FileOutputStream(logFile, true));
    }
}
