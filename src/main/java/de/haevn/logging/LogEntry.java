package de.haevn.logging;

import de.haevn.utils.MetaMethodAccessor;

/**
 * Represents a log entry.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class LogEntry {
    private Level level = Level.UNKNOWN;
    private MetaMethodAccessor helper = null;

    private String message = "";
    private Throwable throwable = null;
    private long timestamp = 0;

    /**
     * Creates a new log entry.
     *
     * @return A new log entry.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the log level.
     *
     * @param level The log level.
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets the helper.
     *
     * @return The helper.
     */
    public MetaMethodAccessor getHelper() {
        return helper;
    }

    /**
     * Sets the helper.
     *
     * @param helper The helper.
     */
    public void setHelper(MetaMethodAccessor helper) {
        this.helper = helper;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message The message.
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the throwable.
     *
     * @return The throwable.
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * Sets the throwable.
     *
     * @param throwable The throwable.
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * Gets the timestamp.
     *
     * @return The timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp The timestamp.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
