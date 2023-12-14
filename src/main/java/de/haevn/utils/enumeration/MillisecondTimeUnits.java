package de.haevn.utils.enumeration;

public enum MillisecondTimeUnits {
    MILLISECONDS(1),
    SECONDS(MILLISECONDS.getValue() * 1000),
    MINUTES(SECONDS.getValue() * 60),
    HOURS(MINUTES.getValue() * 60),
    DAYS(HOURS.getValue() * 24),
    WEEKS(DAYS.getValue() * 7),
    MONTHS(DAYS.getValue() * 30),
    YEARS(DAYS.getValue() * 365);

    private final long value;

    private MillisecondTimeUnits(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
