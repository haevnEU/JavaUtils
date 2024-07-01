package de.haevn.utils.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <h1>ExceptionUtils</h1>
 * <br>
 * <p>This class provides utility methods for exceptions.</p>
 *
 * <p>New features will be added in the future.</p>
 *
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public final class ExceptionUtils {
    private ExceptionUtils() {
    }

    /**
     * <h2>getStackTrace({@link Throwable})</h2>
     * <p>Returns the stack trace of the given {@link Throwable} as a string.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     try {
     *         throw new ApplicationException("An error occurred");
     *     } catch (ApplicationException e) {
     *         System.out.println("Error: " + e.getMessage());
     *         System.out.println(ExceptionUtils.getStackTrace(e));
     *     }
     * }
     * </pre>
     *
     * @param throwable The throwable.
     * @return The stack trace of the given {@link Throwable} as a string.
     */
    public static String getStackTrace(final Throwable throwable) {
        if (null == throwable) {
            return "";
        }
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
