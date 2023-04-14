package de.haevn.logging;

/**
 * Configuration for the logger.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class LoggerConfig {
    private Level level = Level.ALL;

    /**
     * Get the current minimum log level.
     *
     * @return The minimum log level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the log level.
     *
     * @param level The minimum log level.
     */
    public void setLevel(Level level) {
        this.level = level;
    }
}
