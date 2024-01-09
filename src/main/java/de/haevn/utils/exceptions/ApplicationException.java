package de.haevn.utils.exceptions;

/**
 * A simple exception class for application exceptions.
 * @version 1.0
 * @since 1.0
 * @see RuntimeException
 * @author haevn
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(final Throwable other) {
        this(other.getMessage(), other);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final String message, final Throwable other) {
        super(message, other);
    }
}
