package de.haevn.utils.exceptions;

import de.haevn.utils.MetaMethodAccessor;

/**
 * Indicates that the method is not yet implemented.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class NotYetImplementedException extends Error {
    private String message;

    public NotYetImplementedException() {
        MetaMethodAccessor.getMethod(2).ifPresent(this::initMessage);
    }

    public NotYetImplementedException(final String message) {
        this.message = message;
    }


    private void initMessage(final MetaMethodAccessor helper) {
        this.message = "Method \"" + helper.toString() + "\" is not yet implemented.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
