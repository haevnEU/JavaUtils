package de.haevn.utils.exceptions;

/**
 * <h1>InvalidCastException</h1>
 * <br>
 * <p>This exception is thrown when an invalid cast is performed.</p>
 *
 * @author haevn
 * @version 1.0
 * @see ApplicationException
 * @see RuntimeException
 * @since 1.0
 */
public class InvalidCastException extends ApplicationException {

    /**
     * <h2>InvalidCastException({@link Class}, {@link Class})</h2>
     * <p>Creates a new InvalidCastException with the provided and required class.</p>
     *
     * @param provided The provided class.
     * @param required The required class.
     */
    public InvalidCastException(final Class<?> provided, final Class<?> required) {
        this(provided, required, "Invalid cast");
    }

    /**
     * <h2>InvalidCastException({@link Class}, {@link Class}, {@link String})</h2>
     * <p>Creates a new InvalidCastException with the provided and required class and a message.</p>
     *
     * @param provided The provided class.
     * @param required The required class.
     * @param message  The message.
     */
    public InvalidCastException(final Class<?> provided, final Class<?> required, final String message) {
        super("Provided class: " + provided.getCanonicalName()
                + "\nProvided class: " + required.getCanonicalName()
                + "\n" + message);
    }

}
