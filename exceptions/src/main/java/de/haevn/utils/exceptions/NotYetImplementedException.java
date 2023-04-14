package de.haevn.utils.exceptions;

import de.haevn.utils.debug.MethodTools;

/**
 * Indicates that the method is not yet implemented.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class NotYetImplementedException extends Error {
    private final String message;

    public NotYetImplementedException() {
       final var method = MethodTools.getMethod(2);
       if(method.isPresent()) {
           this.message = "Method \"" + method + "\" is not yet implemented.";
       }else {
           this.message = "Method is not yet implemented.";
       }
    }

    public NotYetImplementedException(final String message) {
        this.message = message;
    }



    @Override
    public String getMessage() {
        return message;
    }
}
