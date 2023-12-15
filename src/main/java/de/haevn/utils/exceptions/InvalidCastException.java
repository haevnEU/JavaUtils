package de.haevn.utils.exceptions;

/**
 * A simple exception class for invalid casts.
 * @version 1.0
 * @since 1.0
 * @see ApplicationException
 * @see RuntimeException
 * @author haevn
 */
public class InvalidCastException extends ApplicationException {
    public InvalidCastException(Class<?> provided, Class<?> required) {
        this(provided, required, "Invalid cast");
    }

    public InvalidCastException(Class<?> provided, Class<?> required, String message) {

        super("Provided class: " + provided.getCanonicalName()
                + "\nProvided class: " + required.getCanonicalName()
                + "\n" + message);
    }

}
