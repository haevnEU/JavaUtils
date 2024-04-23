package de.haevn.utils.exceptions;

import de.haevn.utils.debug.MethodTools;

/**
 * Indicates that the method is not yet implemented.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class MethodNotAllowedError extends Error {
    private final String message;

    public MethodNotAllowedError() {
        final var method = MethodTools.getMethod(2);
        if (method.isPresent()) {
            this.message = "Method \"" + method + "\" is not allowed.";
        } else {
            this.message = "Method is not yet allowed.";
        }
    }

    public MethodNotAllowedError(final String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
