package de.haevn.utils.logging;

import de.haevn.utils.debug.MethodTools;

/**
 * Represents a log entry.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
final class LogEntry {
    private Level level = Level.UNKNOWN;
    private MethodTools helper = null;

    private String message = "";
    private Throwable throwable = null;
    private long timestamp = 0;
    private String threadName = "";

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
    public MethodTools getHelper() {
        return helper;
    }

    /**
     * Sets the helper.
     *
     * @param helper The helper.
     */
    public void setHelper(MethodTools helper) {
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


    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String name) {
        this.threadName = name;
    }
}
