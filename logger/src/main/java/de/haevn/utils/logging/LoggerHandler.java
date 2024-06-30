package de.haevn.utils.logging;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>LoggerHandler</h1>
 * <p>This class handles all loggers.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
final class LoggerHandler {
    private static final LoggerHandler INSTANCE = new LoggerHandler();
    private final List<Logger> loggers = new ArrayList<>();

    /**
     * <h2>getInstance()</h2>
     * <p>Gets the instance of the logger handler.</p>
     *
     * @return The instance.
     */
    public static synchronized LoggerHandler getInstance() {
        return INSTANCE;
    }

    /**
     * <h2>LoggerHandler()</h2>
     * <p>This is the private constructor of the logger handler.</p>
     */
    private LoggerHandler() {
    }

    /**
     * <h2>addLogger({@link Logger})</h2>
     * <p>This method adds a logger to the logger handler.</p>
     *
     * @param logger The logger.
     */
    public void addLogger(final Logger logger) {
        loggers.add(logger);
    }

    /**
     * <h2>flushAll()</h2>
     * <p>This method flushes all loggers.</p>
     */
    public void flushAll() {
        loggers.forEach(Logger::flush);
    }
}
