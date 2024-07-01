package de.haevn.utils.enumeration;

/**
 * <h1>MillisecondTimeUnits</h1>
 * <br>
 * <br> A simple enum for millisecond time units.
 * <br> The base unit is a millisecond.
 * <br> The enum provides the following units:
 * <ul>
 *     <li>MILLISECONDS</li>
 *     <li>SECONDS</li>
 *     <li>MINUTES</li>
 *     <li>HOURS</li>
 *     <li>DAYS</li>
 *     <li>WEEKS</li>
 *     <li>MONTHS</li>
 *     <li>YEARS</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
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

    MillisecondTimeUnits(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
