package de.haevn.utils.exceptions;

import de.haevn.utils.debug.MethodTools;

/**
 * <h1>MethodNotAllowedError</h1>
 * <br>
 * <p>This error is thrown when a method is not allowed.</p>
 * <p>It provides a message that can be used to identify the error.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class MethodNotAllowedError extends Error {
    private final String message;

    /**
     * <h2>MethodNotAllowedError()</h2>
     * <p>Creates a new MethodNotAllowedError with a default message.</p>
     * <p>The default message is "Method is not yet allowed."</p>
     * <p>The method that caused the error is determined by the {@link MethodTools} class.</p>
     * <p>If the method cannot be determined, the message is "Method is not yet allowed."</p>
     */
    public MethodNotAllowedError() {
        final var method = MethodTools.getMethod(2);
        if (method.isPresent()) {
            this.message = "Method \"" + method + "\" is not allowed.";
        } else {
            this.message = "Method is not yet allowed.";
        }
    }

    /**
     * <h2>MethodNotAllowedError({@link String})</h2>
     * <p>Creates a new MethodNotAllowedError with the given message.</p>
     *
     * @param message The message of the error.
     */
    public MethodNotAllowedError(final String message) {
        this.message = message;
    }

    /**
     * <h2>getMessage()</h2>
     * <p>Returns the message of the error.</p>
     *
     * @return The message of the error.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
