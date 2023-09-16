package de.haevn.logging;

import de.haevn.utils.MetaMethodAccessor;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class Logger {
    private final LoggerConfig config;
    private final List<LogEntry> logEntries = new ArrayList<>();

    /**
     * Creates a new Logger with default configuration
     */
    public Logger() {
        this(new LoggerConfig());
    }

    /**
     * Creates a new Logger with the given configuration
     *
     * @param config The configuration to use
     */
    public Logger(LoggerConfig config) {
        this.config = config;
    }

    /**
     * Creates a new EntryBuilder for the given log level
     *
     * @param level The log level to use
     * @return The EntryBuilder
     */
    public EntryBuilder at(Level level) {
        return new EntryBuilder(level);
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
        logEntries.forEach(entry -> {
            config.getOutput().println(entry.getTimestamp() + " " + entry.getLevel() + " " + entry.getHelper() + " " + entry.getMessage());
            if (entry.getThrowable() != null) {
                entry.getThrowable().printStackTrace(config.getOutput());
            }
        });
        logEntries.clear();
    }


    /**
     * Builder class for log entries
     */
    public class EntryBuilder {
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
            MetaMethodAccessor.getMethod(2).ifPresent(entry::setHelper);
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

        /**
         * Sets the timestamp of the log entry to the current time
         *
         * @return The EntryBuilder
         */
        public EntryBuilder withCurrentTimestamp() {
            entry.setTimestamp(System.currentTimeMillis());
            return this;
        }

        /**
         * Adds the log entry to the log entries list if the log level is high enough
         */
        public void log() {
            if (config.getLevel().ordinal() >= entry.getLevel().ordinal()) {
                logEntries.add(entry);
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
