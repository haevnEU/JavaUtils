package de.haevn.utils.exceptions;

/**
 * <h1>ValidationFailedException</h1>
 * <br>
 * <p>This exception is thrown when a validation fails.</p>
 * <p>It provides a message that can be used to identify the error.</p>
 *
 * @author haevn
 * @version 1.1
 * @see Exception
 * @since 1.1
 */
public class ValidationFailedException extends Exception {
    public ValidationFailedException(final String message) {
        super(message);
    }
}
