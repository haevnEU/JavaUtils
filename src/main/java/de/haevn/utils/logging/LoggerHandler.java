package de.haevn.utils.logging;

import java.util.ArrayList;
import java.util.List;

public final class LoggerHandler {
    private static final LoggerHandler INSTANCE = new LoggerHandler();
    private final List<Logger> loggers = new ArrayList<>();

    public synchronized static LoggerHandler getInstance() {
        return INSTANCE;
    }

    private LoggerHandler() {
    }

    void addLogger(Logger logger) {
        loggers.add(logger);
    }

    public void flushAll() {
        loggers.forEach(Logger::flush);
    }
}
