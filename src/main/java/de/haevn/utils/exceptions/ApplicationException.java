package de.haevn.utils.exceptions;

/**
 * A simple exception class for application exceptions.
 * @version 1.0
 * @since 1.0
 * @see RuntimeException
 * @author haevn
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(Throwable other) {
        this(other.getMessage(), other);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable other) {
        super(message, other);
    }
}
