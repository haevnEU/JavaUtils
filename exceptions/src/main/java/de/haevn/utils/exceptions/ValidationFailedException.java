package de.haevn.utils.exceptions;

/**
 * A simple exception class for validation exceptions.
 * @version 1.1
 * @since 1.1
 * @author haevn
 */
public class ValidationFailedException extends Exception {
    public ValidationFailedException(final String message) {
        super(message);
    }
}
