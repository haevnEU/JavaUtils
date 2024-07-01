package de.haevn.utils.logging;

import de.haevn.utils.exceptions.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>SanitizedLogEntry</h1>
 * <p>This class represents a sanitized log entry.</p>
 *
 * @param date      The date of the log entry.
 * @param level     The level of the log entry.
 * @param source    The source of the log entry.
 * @param method    The method of the log entry.
 * @param thread    The thread of the log entry.
 * @param object    The object of the log entry.
 * @param message   The message of the log entry.
 * @param throwable The throwable of the log entry.
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public record SanitizedLogEntry(String date, String level, String source, String method, String thread, Object object,
                                String message, String throwable) {

    /**
     * <h2>getFromLogEntry({@link LogEntry})</h2>
     * <p>This static method converts a {@link LogEntry} to a {@link SanitizedLogEntry}.</p>
     *
     * @param entry The log entry.
     * @return The sanitized log entry.
     */
    public static SanitizedLogEntry getFromLogEntry(LogEntry entry) {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        final Date resultdate = new Date(entry.getTimestamp());
        return new SanitizedLogEntry(sdf.format(resultdate), entry.getLevel().name(), entry.getHelper().getFileName() + ":" + entry.getHelper().getLineNumber(), entry.getHelper().getClassName() + "#" + entry.getHelper().getMethodName(), entry.getThreadName(), entry.getObj(), entry.getMessage(), ExceptionUtils.getStackTrace(entry.getThrowable()));
    }
}
