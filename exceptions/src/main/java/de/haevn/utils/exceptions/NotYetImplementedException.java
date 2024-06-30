package de.haevn.utils.exceptions;

import de.haevn.utils.debug.MethodTools;

/**
 * <h1>NotYetImplementedException</h1>
 * <br>
 * <p>This exception is thrown when a method is not yet implemented.</p>
 * <p>The method can be used to identify the method that is not yet implemented.</p>
 * <p>The application can recover instead of crashing.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     void doSomething() {
 *         throw new NotYetImplementedException();
 *     }
 * }
 * </pre>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class NotYetImplementedException extends Error {
    private final String message;

    /**
     * <h2>NotYetImplementedException()</h2>
     */
    public NotYetImplementedException() {
        final var method = MethodTools.getMethod(2);
        if (method.isPresent()) {
            this.message = "Method \"" + method + "\" is not yet implemented.";
        } else {
            this.message = "Method is not yet implemented.";
        }
    }

    /**
     * <h2>NotYetImplementedException({@link String})</h2>
     * <p>Creates a new NotYetImplementedException with the given message.</p>
     *
     * @param message The message of the exception.
     */
    public NotYetImplementedException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
