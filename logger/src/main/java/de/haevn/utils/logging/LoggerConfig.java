package de.haevn.utils.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * <h1>LoggerConfig</h1>
 * <p>This class represents the configuration of the logger.</p>
 *
 * @author haevn
 * @version 1.1
 * @since 1.0
 */
public final class LoggerConfig {

    private PrintStream fileOutput;
    private PrintStream consoleOutput = System.out;
    private Level level = Level.ALL;
    private boolean autoFlush = true;
    private boolean useShutdownHook = true;
    private int logSize = 100;


    /**
     * <h2>getFileOutput()</h2>
     * <p>Gets the file output stream.</p>
     *
     * @return The file output stream.
     */
    public PrintStream getFileOutput() {
        return fileOutput;
    }

    /**
     * <h2>setFileOutput({@link PrintStream})</h2>
     * <p>Sets the file output stream.</p>
     *
     * @param fileOutput The file output stream.
     */
    public void setFileOutput(final PrintStream fileOutput) {
        this.fileOutput = fileOutput;
    }

    /**
     * <h2>getConsoleOutput()</h2>
     * <p>Gets the console output stream.</p>
     *
     * @return The console output stream.
     */
    public PrintStream getConsoleOutput() {
        return consoleOutput;
    }

    /**
     * <h2>setConsoleOutput({@link PrintStream})</h2>
     * <p>Sets the console output stream.</p>
     *
     * @param consoleOutput The console output stream.
     */
    public void setConsoleOutput(final PrintStream consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    /**
     * <h2>getLevel()</h2>
     * <p>Gets the level of the logger.</p>
     *
     * @return The level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * <h2>setLevel({@link Level})</h2>
     * <p>Sets the level of the logger.</p>
     *
     * @param level The level.
     */
    public void setLevel(final Level level) {
        this.level = level;
    }

    /**
     * <h2>isAutoFlush()</h2>
     * <p>Checks if the logger is set to auto flush.</p>
     *
     * @return True iff the logger is set to auto flush.
     */
    public boolean isAutoFlush() {
        return autoFlush;
    }

    /**
     * <h2>setAutoFlush(boolean)</h2>
     * <p>Sets the logger to auto flush.</p>
     *
     * @param autoFlush The auto flush.
     */
    public void setAutoFlush(final boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    /**
     * <h2>isUseShutdownHook()</h2>
     * <p>Checks if the logger uses a shutdown hook.</p>
     *
     * @return True iff the logger uses a shutdown hook.
     */
    public boolean isUseShutdownHook() {
        return useShutdownHook;
    }

    /**
     * <h2>setUseShutdownHook(boolean)</h2>
     * <p>Sets the logger to use a shutdown hook.</p>
     *
     * @param useShutdownHook The use shutdown hook.
     */
    public void setUseShutdownHook(final boolean useShutdownHook) {
        this.useShutdownHook = useShutdownHook;
    }

    /**
     * <h2>getLogSize()</h2>
     * <p>Gets the log size.</p>
     *
     * @return The log size.
     */
    public int getLogSize() {
        return logSize;
    }

    /**
     * <h2>setLogSize(int)</h2>
     * <p>Sets the log size.</p>
     *
     * @param logSize The log size.
     */
    public void setLogSize(final int logSize) {
        this.logSize = logSize;
    }

    /**
     * <h2>setOutput(String)</h2>
     * <p>Sets the output stream to a file.</p>
     *
     * @param logFile The output stream.
     */
    public void setOutput(final String logFile) throws FileNotFoundException {
        setOutput(new File(logFile + System.currentTimeMillis() + ".log"));
    }

    /**
     * <h2>setOutput({@link File})</h2>
     * <p>Sets the output stream to a file.</p>
     *
     * @param logFile The output stream.
     */
    public void setOutput(final File logFile) throws FileNotFoundException {
        this.fileOutput = new PrintStream(new FileOutputStream(logFile, true));
    }
}
