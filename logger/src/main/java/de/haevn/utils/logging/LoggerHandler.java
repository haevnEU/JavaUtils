package de.haevn.utils.logging;

import java.util.ArrayList;
import java.util.List;

final class LoggerHandler {
    private static final LoggerHandler INSTANCE = new LoggerHandler();
    private final List<Logger> loggers = new ArrayList<>();

    public static synchronized LoggerHandler getInstance() {
        return INSTANCE;
    }

    private LoggerHandler() {
    }

    public void addLogger(final Logger logger) {
        loggers.add(logger);
    }

    public void flushAll() {
        loggers.forEach(Logger::flush);
    }
}
