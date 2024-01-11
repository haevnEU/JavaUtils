package de.haevn.utils.exceptions;

/**
 * A simple exception class for application exceptions.
 * @version 1.0
 * @since 1.0
 * @see RuntimeException
 * @author haevn
 */
public class ApplicationException extends RuntimeException {
    private final long errorCode;
    public ApplicationException(final Throwable other) {
        this(other.getMessage(), other);
    }

    public ApplicationException(final String message) {
        super(message);
        errorCode = ErrorCode.UNKNOWN;
    }

    public ApplicationException(final String message, final Throwable other) {
        this(message, other, ErrorCode.UNKNOWN);
    }

    public ApplicationException(final String message, final Throwable other, final long errorCode) {
        super(message, other);
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return errorCode;
    }
    
}
