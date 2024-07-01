package de.haevn.utils.enumeration;

/**
 * <h1>MicroTimeUnits</h1>
 * <br>
 * <br> A simple enum for micro time units.
 * <br> The base unit is a microsecond.
 * <br> The enum provides the following units:
 * <ul>
 *     <li>MICROSECONDS</li>
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
public enum MicroTimeUnits {

    MICROSECONDS(1),
    MILLISECONDS(MICROSECONDS.getValue() * 1000),
    SECONDS(MILLISECONDS.getValue() * 1000),
    MINUTES(SECONDS.getValue() * 60),
    HOURS(MINUTES.getValue() * 60),
    DAYS(HOURS.getValue() * 24),
    WEEKS(DAYS.getValue() * 7),
    MONTHS(DAYS.getValue() * 30),
    YEARS(DAYS.getValue() * 365);


    private final long value;

    MicroTimeUnits(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
