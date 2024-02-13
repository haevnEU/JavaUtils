package de.haevn.utils;


import de.haevn.utils.debug.ThreadTools;

import java.util.List;
import java.util.function.Function;

/**
 * A simple class for core information.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 * @deprecated This class is deprecated and will be removed in version 1.1, use {@link System} {@link StringUtils} {@Link RecordUtils} and {@link ThreadTools} instead
 */
@Deprecated(forRemoval = true, since = "1.2")
public class Core {

    private Core() {
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static void copyText(final String text) {
        StringUtils.copyText(text);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static <R, T> R map(final T value, final Function<? super T, ? extends R> mapper) {
        return System.map(value, mapper);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static <T> T mapRecord(final T value, final Function<? super T, ? extends T> mapper) {
        return RecordUtils.mapRecord(value, mapper);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static void sleepSecond(final long seconds) {
        TimeUtils.sleepSecond(seconds);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static void sleepMillis(final long millis) {
        TimeUtils.sleepMillis(millis);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String getCurrentTime() {
        return TimeUtils.getCurrentTime();
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String getCurrentDate() {
        return TimeUtils.getCurrentDate();
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String getCurrentDateAndTime() {
        return TimeUtils.getCurrentDateAndTime();
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String getCurrentDateWithFormat(final String format) {
        return TimeUtils.getCurrentDateWithFormat(format);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String[] getKeyValue(final String key, final String value) {
        return System.property(key, value);
    }


    @Deprecated(forRemoval = true, since = "1.1")
    public static List<Thread> getRunningThreads() {
        return ThreadTools.getThreads();
    }


    @Deprecated(forRemoval = true, since = "1.1")
    public static String fitStringLeft(final String string, final int length) {
        return StringUtils.fitStringLeft(string, length);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String fitString(final String string, final int length) {
        return StringUtils.fitString(string, length);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String trimStringTo(final String input, final int length) {
        return StringUtils.trimStringTo(input, length);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String splitSecure(final String input, final char delimiter) {
        return StringUtils.splitSecure(input, 0, delimiter);
    }

    @Deprecated(forRemoval = true, since = "1.1")
    public static String splitSecure(final String input, final char delimiter, final String defaultValue) {
        return StringUtils.splitSecure(input, delimiter, 0, defaultValue);
    }
}
