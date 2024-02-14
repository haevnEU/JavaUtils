package de.haevn.utils;


import java.awt.*;

/**
 * A simple class for color information.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public final class ColorUtils {
    private ColorUtils() {
    }

    public static long colorToInt(final long r, final long g, final long b, final long a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static long colorToInt(final long r, final long g, final long b) {
        return colorToInt(r, g, b, 255);
    }

    public static long colorToInt(final double r, final double g, final double b, final double a) {
        return colorToInt((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
    }

    public static long colorToInt(final double r, final double g, final double b) {
        return colorToInt(r, g, b, 1);
    }

    public static long colorToInt(String hex) {
        if(hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        return Integer.parseInt(hex, 16);
    }

    public static long colorToInt(final String hex, final long a) {
        return colorToInt(hex) | (a << 24);
    }

    public static long colorToInt(final String hex, final double a) {
        return colorToInt(hex, (int) (a * 255));
    }

    public static Color intToColor(final long color) {
        return new Color((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF);
    }

    public static Color intToColor(final String hex) {
        return intToColor(colorToInt(hex));
    }

    public static Color intToColor(final String hex, final long a) {
        return intToColor(colorToInt(hex, a));
    }

    public static Color intToColor(final String hex, final double a) {
        return intToColor(hex, (int) (a * 255));
    }

    public static String colorToHex(final long color) {
        return String.format("%06X", color & 0xFFFFFF);
    }

    public static String colorToHex(final Color color) {
        return colorToHex(colorToInt(color.getRed(), color.getGreen(), color.getBlue()));
    }

    public static String colorToHex(final String hex) {
        return colorToHex(colorToInt(hex));
    }

    public static String colorToHex(final String hex, final long a) {
        return colorToHex(colorToInt(hex, a));
    }

    public static String colorToHex(final String hex, final double a) {
        return colorToHex(hex, (int) (a * 255));
    }

    public static String colorToHex(final long r, final long g, final long b, final long a) {
        return colorToHex(colorToInt(r, g, b, a));
    }

    public static String colorToHex(final long r, final long g, final long b) {
        return colorToHex(colorToInt(r, g, b));
    }

    public static String colorToHex(final double r, final double g, final double b, final double a) {
        return colorToHex(colorToInt(r, g, b, a));
    }

    public static String colorToHex(final double r, final double g, final double b) {
        return colorToHex(colorToInt(r, g, b));
    }
}
