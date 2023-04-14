package de.haevn.exceptions;

public class ApplicationException extends Throwable {
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
