package de.haevn.javautils.exceptions;

import de.haevn.javautils.utils.MetaMethodAccessor;

public class NotYetImplementedException extends Exception {
    private String message;

    public NotYetImplementedException() {
        MetaMethodAccessor.getMethod(2).ifPresent(this::initMessage);
    }

    public NotYetImplementedException(String message) {
        this.message = message;
    }


    private void initMessage(MetaMethodAccessor helper) {
        this.message = "Method \"" + helper.toString() + "\" is not yet implemented.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
