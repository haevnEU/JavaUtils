package de.haevn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>TimeUtils</h1>
 * <p>TimeUtils provides utility methods for time interaction.</p>
 * <p>It provides methods to sleep for a given amount of time, get the current time and date.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class TimeUtils {
    private TimeUtils() {
    }

    /**
     * <h2>sleepSecond(long)</h2>
     * <p>Sleeps for a given amount of seconds.</p>
     *
     * @param seconds Amount of seconds to sleep.
     */
    public static void sleepSecond(final long seconds) {
        sleepMillis(seconds * 1000);
    }

    /**
     * <h2>sleepMillis(long)</h2>
     * <p>Sleeps for a given amount of milliseconds.</p>
     *
     * @param millis Amount of milliseconds to sleep.
     */
    public static void sleepMillis(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * <h2>getCurrentTime()</h2>
     * <p>Gets the current time in the format HH:mm:ss.</p>
     *
     * @return Current time.
     */
    public static String getCurrentTime() {
        return getCurrentDateWithFormat("HH:mm:ss");
    }

    /**
     * <h2>getCurrentDate()</h2>
     * <p>Gets the current date in the format dd/MM/yyyy.</p>
     *
     * @return Current date.
     */
    public static String getCurrentDate() {
        return getCurrentDateWithFormat("dd/MM/yyyy");
    }

    /**
     * <h2>getCurrentDateAndTime()</h2>
     * <p>Gets the current date and time in the format dd/MM/yyyy HH:mm:ss.</p>
     *
     * @return Current date and time.
     */
    public static String getCurrentDateAndTime() {
        return getCurrentDateWithFormat("dd/MM/yyyy HH:mm:ss");
    }

    /**
     * <h2>getCurrentDateWithFormat(String)</h2>
     * <p>Gets the current date with a given format.</p>
     *
     * @param format Format of the date.
     * @return Current date with the given format.
     */
    public static String getCurrentDateWithFormat(final String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
}
