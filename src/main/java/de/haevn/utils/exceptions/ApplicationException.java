package de.haevn.utils.exceptions;

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
