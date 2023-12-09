package de.haevn.javautils.exceptions;

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
