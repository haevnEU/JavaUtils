package de.haevn.utils.logging;

/**
 * Defines the log levels.
 * <ul>
 *     Following levels are defined:
 *     <li>DEBUG</li>
 *     <li>INFO</li>
 *     <li>WARNING</li>
 *     <li>ERROR</li>
 *     <li>FATAL</li>
 *     <li>UNKNOWN</li>
 *     <li>ALL</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public enum Level {
    DEBUG(0b00000001),
    INFO(0b00000010),
    WARNING(0b00000100),
    ERROR(0b00001000),
    FATAL(0b00001000),
    UNKNOWN(0b00010000),
    ALL(0b11111111);


    public final int value;

    Level(int value) {
        this.value = value;
    }
}
