package de.haevn.utils;

import java.text.NumberFormat;

/**
 * A simple class for number information.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public final class NumberUtils {
    private NumberUtils() {
    }


    public static NumberFormat getDecimalFormat() {
        return NumberFormat.getInstance();
    }

    public static int hexToInteger(final String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static String numberToRoundText(final double number) {
        return numberToRoundText(number, false);
    }

    public static String numberToRoundText(final double number, final boolean separator) {
        if(separator) {
            return getDecimalFormat().format(Math.round(number));
        }
        return Integer.toString((int) Math.round(number));
    }


    public static Integer stringToInteger(final String string, final int fallbackNumber) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return fallbackNumber;
        }
    }

    public static Integer stringToInteger(final String string) {
        return stringToInteger(string, 0);
    }

    public static Double stringToDouble(final String string, final double fallbackNumber) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return fallbackNumber;
        }
    }

    public static Double stringToDouble(final String string) {
        return stringToDouble(string, 0.0);
    }


    public static String numberToShortValue(final int number) {
        if (number < 1000) return Integer.toString(number);
        if (number < 1000000) return getDecimalFormat().format(number / 1000.0) + "K";
        if (number < 1000000000) return getDecimalFormat().format(number / 1000000.0) + "M";
        return getDecimalFormat().format(number / 1000000000.0) + "B";
    }

    public static String numberToShortValue(final double number) {
        return numberToShortValue((int) number);
    }


    public static String msToString(final double milliseconds) {
        return msToString(Math.round(milliseconds));
    }

    public static String msToString(final long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        String negative = "";
        if (minutes < 0) {
            minutes *= -1;
            negative = "-";
        }
        if (seconds < 0) {
            seconds *= -1;
            negative = "-";
        }
        return negative + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }
}
