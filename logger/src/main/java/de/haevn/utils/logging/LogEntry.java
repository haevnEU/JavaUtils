package de.haevn.utils.logging;

import de.haevn.utils.debug.MethodTools;

/**
 * <h1>LogEntry</h1>
 * <p>This class represents a log entry.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public final class LogEntry {
    private Level level = Level.UNKNOWN;
    private MethodTools helper = null;

    private String message = "";
    private Throwable throwable = null;
    private long timestamp = 0;
    private String threadName = "";
    private Object obj;


    /**
     * <h2>getLevel</h2>
     * <p>Creates a new log entry.</p>
     *
     * @return The log level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * <h2>setLevel()</h2>
     * <p>Sets the log level.</p>
     *
     * @param level The level.
     */
    public void setLevel(final Level level) {
        this.level = level;
    }

    /**
     * <h2>getHelper()</h2>
     * <p>Gets the {@link MethodTools method helper}.</p>
     *
     * @return The helper.
     */
    public MethodTools getHelper() {
        return helper;
    }

    /**
     * <h2>setHelper({@link MethodTools})</h2>
     * <p>Sets the {@link MethodTools method helper}.</p>
     *
     * @param helper The helper.
     */
    public void setHelper(final MethodTools helper) {
        this.helper = helper;
    }


    /**
     * <h2>getMessage()</h2>
     * <p>Gets the message.</p>
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * <h2>setMessage(String)</h2>
     * <p>Sets the message.</p>
     *
     * @param message The message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }


    /**
     * <h2>getThrowable()</h2>
     * <p>Gets the throwable.</p>
     *
     * @return The throwable.
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * <h2>setThrowable(Throwable)</h2>
     * <p>Sets the throwable.</p>
     *
     * @param throwable The throwable.
     */
    public void setThrowable(final Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * <h2>getTimestamp()</h2>
     * <p>Gets the timestamp.</p>
     *
     * @return The timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * <h2>setTimestamp(long)</h2>
     * <p>Sets the timestamp.</p>
     *
     * @param timestamp The timestamp.
     */
    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * <h2>getObj()</h2>
     * <p>Gets the object.</p>
     *
     * @return The object.
     */
    public Object getObj() {
        return obj;
    }

    /**
     * <h2>setObj(Object)</h2>
     * <p>Sets the object.</p>
     *
     * @param obj The object.
     */
    public void setObj(final Object obj) {
        this.obj = obj;
    }

    /**
     * <h2>getThreadName()</h2>
     * <p>Gets the thread name.</p>
     *
     * @return The thread name.
     */
    public String getThreadName() {
        return threadName;
    }

    /**
     * <h2>setThreadName(String)</h2>
     * <p>Sets the thread name.</p>
     *
     * @param name The thread name.
     */
    public void setThreadName(final String name) {
        this.threadName = name;
    }
}
