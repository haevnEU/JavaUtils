package de.haevn.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * A simple class for string information.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class StringUtils {
    private StringUtils() {
    }

    /**
     * Copy text to clipboard.
     *
     * @param text the text
     */
    public static void copyText(final String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }


    /**
     * Pads the string from the left with whitespace.
     *
     * @param string the string
     * @param length the length
     * @return the padded string
     */
    public static String fitStringLeft(final String string, final int length) {
        return String.format("%" + length + "." + length + "s", string.substring(0, length < string.length() ? length : string.length()));
    }

    /**
     * Pads the string from the right with whitespace.
     *
     * @param string the string
     * @param length the length
     * @return the padded string
     */
    public static String fitString(final String string, final int length) {
        return String.format("%-" + length + "." + length + "s", string.substring(0, Math.min(length, string.length())));
    }

    /**
     * Trim string to string.
     *
     * @param input  the input
     * @param length the length
     * @return the trimmed string
     */
    public static String trimStringTo(final String input, final int length) {
        return input.length() > length ? input.substring(0, length) : input;
    }

    /**
     * Split secure string.
     *
     * @param input     the input
     * @param delimiter the delimiter
     * @return the string
     */
    public static String splitSecure(final String input, final int index, final char delimiter) {
        return splitSecure(input, delimiter, index, "");
    }

    /**
     * Split secure string.
     *
     * @param input        the input
     * @param delimiter    the delimiter
     * @param defaultValue the default value
     * @return the string
     */
    public static String splitSecure(final String input, final char delimiter, final int index, final String defaultValue) {

        if (input == null || input.isEmpty()) {
            return defaultValue;
        }
        if (input.contains(String.valueOf(delimiter))) {
            return input.split(String.valueOf(delimiter))[index];
        }
        return input;
    }

    public static String str2Hex(final String in){
        char[] chars = in.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }
        return hex.toString();
    }
}
