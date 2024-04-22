package de.haevn.utils.enumeration;

/**
 * A simple enum for nano time units.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public enum NanoTimeUnits {
    NANOSECONDS(1),
    MICROSECONDS(NANOSECONDS.getValue() * 1000),
    MILLISECONDS(MICROSECONDS.getValue() * 1000),
    SECONDS(MILLISECONDS.getValue() * 1000),
    MINUTES(SECONDS.getValue() * 60),
    HOURS(MINUTES.getValue() * 60),
    DAYS(HOURS.getValue() * 24),
    WEEKS(DAYS.getValue() * 7),
    MONTHS(DAYS.getValue() * 30),
    YEARS(DAYS.getValue() * 365);

    private final long value;

    NanoTimeUnits(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
