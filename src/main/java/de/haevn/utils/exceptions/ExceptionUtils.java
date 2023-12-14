package de.haevn.utils.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A simple ExceptionUtils class.
 *
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public final class ExceptionUtils {
    private ExceptionUtils() {
    }

    /**
     * Returns the stack trace of the given {@link Throwable} as a string.
     *
     * @param throwable The throwable.
     * @return The stack trace of the given {@link Throwable} as a string.
     */
    public static String getStackTrace(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
