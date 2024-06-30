package de.haevn.utils.exceptions;

/**
 * <h1>ApplicationException</h1>
 * <br>
 * <p>This class provides a simple exception class that can be used to throw exceptions in the application.</p>
 * <p>It provides an error code that can be used to identify the error.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     try {
 *         throw new ApplicationException("An error occurred");
 *     } catch (ApplicationException e) {
 *         System.out.println("Error: " + e.getMessage());
 *     }
 * }
 * </pre>
 *
 * @author haevn
 * @version 1.0
 * @see RuntimeException
 * @since 1.0
 */
public class ApplicationException extends RuntimeException {
    private final long errorCode;

    /**
     * <h2>ApplicationException({@link Throwable})</h2>
     * <p>Creates a new ApplicationException based on another {@link Throwable}.</p>
     *
     * @param other
     */
    public ApplicationException(final Throwable other) {
        this(other.getMessage(), other);
    }

    /**
     * <h2>ApplicationException({@link String})</h2>
     * <p>Creates a new ApplicationException with the given message.</p>
     *
     * @param message The message of the exception.
     */
    public ApplicationException(final String message) {
        super(message);
        errorCode = ErrorCode.UNKNOWN;
    }

    /**
     * <h2>ApplicationException({@link String}, {@link Throwable})</h2>
     * <p>Creates a new ApplicationException based on another {@link Throwable} with the given message.</p>
     *
     * @param message The message of the exception.
     * @param other   The other {@link Throwable}.
     */
    public ApplicationException(final String message, final Throwable other) {
        this(message, other, ErrorCode.UNKNOWN);
    }

    /**
     * <h2>ApplicationException({@link String}, {@link Throwable}, long)</h2>
     * <p>Creates a new ApplicationException based on another {@link Throwable} with the given message and error code.</p>
     *
     * @param message   The message of the exception.
     * @param other     The other {@link Throwable}.
     * @param errorCode The error code of the exception.
     */
    public ApplicationException(final String message, final Throwable other, final long errorCode) {
        super(message, other);
        this.errorCode = errorCode;
    }

    /**
     * <h2>getErrorCode</h2>
     * <p>Returns the error code of the exception.</p>
     *
     * @return The error code of the exception.
     */
    public long getErrorCode() {
        return errorCode;
    }

}
