package de.haevn.utils;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * A simple class for core information.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class Core {
    private static String appVersion = "";

    private Core() {
    }

    public static void copyText(final String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }

    public static <R, T> R map(final T value, final Function<? super T, ? extends R> mapper) {
        return mapper.apply(value);
    }

    public static <T> T mapRecord(final T value, final Function<? super T, ? extends T> mapper) {
        return mapper.apply(value);
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

    public static void executeSecure(final Runnable run) {
        try {
            run.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVersion(final String version) {
        appVersion = version;
    }

    public static String getAppVersion() {
        return appVersion;
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

    public static String[] getKeyValue(final String key, final String value) {
        return new String[]{key, value};
    }

    public static List<Thread> getRunningThreads() {
        final var threads = Thread.getAllStackTraces().keySet();
        return new ArrayList<>(threads);
    }
}
