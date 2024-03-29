package de.haevn.utils.logging;

import de.haevn.utils.debug.MethodTools;
import de.haevn.utils.io.FileIO;
import de.haevn.utils.io.SerializationUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Haevn
 * @version 1.1
 * @since 1.0
 */
public final class Logger {
    private final String name;
    private static final LoggerHandler HANDLER = LoggerHandler.getInstance();
    private final LoggerConfig config;
    private final List<LogEntry> logEntries = new ArrayList<>();
    private final Thread shutdownHook = new Thread(this::flush);

    public <T>Logger(){
        this(null, new LoggerConfig());
    }

    public <T>Logger(Class<?> cl){
        this(cl, new LoggerConfig());
    }

    /**
     * Creates a new Logger with the given configuration
     *
     * @param config The configuration to use
     */
    public <T>Logger(Class<?> cl, LoggerConfig config) {
        this.name = (null == cl) ? "Logger" : cl.getSimpleName();
        this.config = config;
        if(null == this.config.getFileOutput()){
            try {
                final var logFile = new File(FileIO.getRootPathWithSeparator() + "logs" + File.separatorChar + this.name + ".log");
                if(!logFile.exists()){
                    logFile.getParentFile().mkdirs();
                    logFile.createNewFile();
                }
                this.config.setFileOutput(new PrintStream(new FileOutputStream(logFile, true)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HANDLER.addLogger(this);
        activateShutdownHook();
    }

    /**
     * Creates a new EntryBuilder for the given log level
     *
     * @param level The log level to use
     * @return The EntryBuilder
     */
    public EntryBuilder at(Level level) {
        return new EntryBuilder(level).forEnclosingMethod(4);
    }

    /**
     * Creates a new EntryBuilder for the DEBUG log level
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atDebug() {
        return at(Level.DEBUG);
    }

    /**
     * Creates a new EntryBuilder for the INFO log level
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atInfo() {
        return at(Level.INFO);
    }

    /**
     * Creates a new EntryBuilder for the WARNING log level
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atWarning() {
        return at(Level.WARNING);
    }

    /**
     * Creates a new EntryBuilder for the ERROR log level
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atError() {
        return at(Level.ERROR);
    }

    /**
     * Creates a new EntryBuilder for the FATAL log level
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atFatal() {
        return at(Level.FATAL);
    }

    /**
     * Creates a new EntryBuilder for the UNKNOWN log level
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atUnknown() {
        return at(Level.UNKNOWN);
    }

    /**
     * Returns a list of previous logged entries
     *
     * @return The list of log entries
     */
    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    /**
     * Returns a list of previous logged entries with the given log level
     *
     * @param level The log level to use
     * @return The list of log entries
     */
    public List<LogEntry> getLogEntries(int level) {
        return logEntries.stream().filter(entry -> (entry.getLevel().value & level) == level).toList();
    }

    /**
     * Clears the log entries
     */
    public void clearLogEntries() {
        logEntries.clear();
    }

    /**
     * Flushes the log entries to a given output
     */
    public void flush() {
        synchronized (logEntries) {

            logEntries.forEach(entry -> {
                final PrintStream consoleOutput = config.getConsoleOutput();
                final PrintStream fileOutput = config.getFileOutput();

                final Consumer<PrintStream> consumer = stream -> {
                    if (null == stream) {
                        return;
                    }
                    SerializationUtils.disablePretty();
                    SerializationUtils.exportJson(SanitizedLogEntry.getFromLogEntry(entry)).ifPresent(stream::println);
                    SerializationUtils.enablePretty();
                };

                consumer.accept(consoleOutput);
                consumer.accept(fileOutput);
            });
            logEntries.clear();
        }
    }

    /**
     * Activates the shutdown hook
     *
     * @hidden This method is preview method and should not be used in production
     */
    public Logger activateShutdownHook() {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        return this;
    }

    /**
     * Deactivates the shutdown hook
     *
     * @hidden This method is preview method and should not be used in production
     */
    public Logger deactivateShutdownHook() {
        Runtime.getRuntime().removeShutdownHook(shutdownHook);
        return this;
    }


    /**
     * Builder class for log entries
     */
    public final class EntryBuilder {
        private final LogEntry entry = new LogEntry();

        /**
         * Creates a new EntryBuilder for the given log level
         *
         * @param level The log level to use
         */
        EntryBuilder(Level level) {
            entry.setLevel(level);
        }

        /**
         * Sets the helper to the current method
         *
         * @return The EntryBuilder
         */
        public EntryBuilder forEnclosingMethod() {
            MethodTools.getMethod(2).ifPresent(entry::setHelper);
            return this;
        }

        /**
         * Sets the helper to the current method
         *
         * @return The EntryBuilder
         */
        private EntryBuilder forEnclosingMethod(int skip) {
            MethodTools.getMethod(skip).ifPresent(entry::setHelper);
            return this;
        }

        /**
         * Appends a throwable to the log entry
         *
         * @param throwable The throwable to append
         * @return The EntryBuilder
         */
        public EntryBuilder withException(Throwable throwable) {
            entry.setThrowable(throwable);
            return this;
        }

        public EntryBuilder withThreadName(){
            entry.setThreadName(Thread.currentThread().getName());
            return this;
        }

        /**
         * Sets the message of the log entry
         *
         * @param message The message to set
         * @return The EntryBuilder
         */
        public EntryBuilder withMessage(String message) {
            entry.setMessage(message);
            return this;
        }

        /**
         * Sets the message of the log entry
         *
         * @param message The message to set
         * @param args    The arguments to use
         * @return The EntryBuilder
         */
        public EntryBuilder withMessage(String message, Object... args) {
            entry.setMessage(String.format(message, args));
            return this;
        }

        public EntryBuilder withObject(Object obj){
            entry.setObj(obj);
            return this;
        }

        /**
         * Adds the log entry to the log entries list if the log level is high enough
         */
        public void log() {
            if (config.getLevel().ordinal() >= entry.getLevel().ordinal()) {
                entry.setTimestamp(System.currentTimeMillis());
                logEntries.add(entry);
            }

            if (config.isAutoFlush() || config.getLogSize() <= logEntries.size()) {
                flush();
            }
        }

        /**
         * Does nothing and discards the log entry
         */
        public void noop() {
            // Do nothing
        }
    }
}
