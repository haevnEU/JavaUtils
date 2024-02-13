package de.haevn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple  utility class for time interaction.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class TimeUtils {
    private TimeUtils() {
    }


    public static void sleepSecond(final long seconds) {
        sleepMillis(seconds * 1000);
    }

    public static void sleepMillis(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public static String getCurrentTime() {
        return getCurrentDateWithFormat("HH:mm:ss");
    }

    public static String getCurrentDate() {
        return getCurrentDateWithFormat("dd/MM/yyyy");
    }

    public static String getCurrentDateAndTime() {
        return getCurrentDateWithFormat("dd/MM/yyyy HH:mm:ss");
    }

    public static String getCurrentDateWithFormat(final String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

}
