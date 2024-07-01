package de.haevn.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * <h1>StringUtils</h1>
 * <p>StringUtils provides utility methods for strings.</p>
 * <p>It provides methods to copy text to the clipboard, pad strings from the left or right, trim strings and split strings.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class StringUtils {
    private StringUtils() {
    }

    /**
     * <h2>copyText(String)</h2>
     * <p>Copies the given text to the clipboard.</p>
     *
     * @param text the text
     */
    public static void copyText(final String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }


    /**
     * <h2>fitStringLeft(String, int)</h2>
     * <p>Pads the string from the left with whitespace.</p>
     * <p>It takes the first n characters of the string and pads it with whitespace to the left.</p>
     * <pre>
     * {@code
     *     StringUtils.fitStringLeft("Hel", 5) -> "  Hel"
     *     StringUtils.fitStringLeft("Hello,World", 20) -> "        Hello,World"
     * }
     * </pre>
     *
     * @param string the string
     * @param length the length
     * @return the padded string
     */
    public static String fitStringLeft(final String string, final int length) {
        return String.format("%" + length + "." + length + "s", string.substring(0, length < string.length() ? length : string.length()));
    }

    /**
     * <h2>fitStringRight(String, int)</h2>
     * <p>Pads the string from the right with whitespace.</p>
     * <p>It takes the first n characters of the string and pads it with whitespace to the right.</p>
     * <pre>
     * {@code
     *   StringUtils.fitStringRight("Hel", 5) -> "Hel  "
     *   StringUtils.fitStringRight("Hello,World", 20) -> "Hello,World         "
     * }
     * </pre>
     *
     * @param string the string
     * @param length the length
     * @return the padded string
     */
    public static String fitString(final String string, final int length) {
        return String.format("%-" + length + "." + length + "s", string.substring(0, Math.min(length, string.length())));
    }

    /**
     * <h2>trimStringTo(String, int)</h2>
     * <p>Trims the string to the given length.</p>
     * <p>It takes the first n characters of the string.</p>
     * <pre>
     * {@code
     *  StringUtils.trimStringTo("Hello,World", 5) -> "Hello"
     *  StringUtils.trimStringTo("Hello,World", 20) -> "Hello,World"
     * }
     * </pre>
     *
     * @param input  the input
     * @param length the length
     * @return the trimmed string
     */
    public static String trimStringTo(final String input, final int length) {
        return input.length() > length ? input.substring(0, length) : input;
    }

    /**
     * <h2>splitSecure(String, char)</h2>
     * <p>Splits the string by the given delimiter and returns the element at the given index.</p>
     * <p>If the string cannot be split an empty string is returned.</p>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     StringUtils.splitSecure("Hello,World", ',', 1) -> "World"
     *     StringUtils.splitSecure("Hello,World", ',', 2) -> ""
     * }
     * </pre>
     *
     * @param input     the input
     * @param delimiter the delimiter
     * @return the string, or an empty string if the string cannot be split
     */
    public static String splitSecure(final String input, final int index, final char delimiter) {
        return splitSecure(input, delimiter, index, "");
    }

    /**
     * <h2>splitSecure(String, char, int, String)</h2>
     * <p>Splits the string by the given delimiter and returns the element at the given index.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * StringUtils.splitSecure("Hello,World", ',', 1, "default") -> "World"
     * StringUtils.splitSecure("Hello,World", ',', 2, "default") -> "default"
     * }
     * </pre>
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

    /**
     * <h2>str2Hex(String)</h2>
     * <p>Converts a string to a hexadecimal string.</p>
     * <p>It converts each character to its hexadecimal representation.</p>
     * <pre>
     * {@code
     *    StringUtils.str2Hex("Hello,World") -> "48656c6c6f2c576f726c64"
     * }
     * </pre>
     *
     * @param in the input
     * @return the hexadecimal string
     */
    public static String str2Hex(final String in) {
        char[] chars = in.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString(ch));
            hex.append(Integer.toHexString(ch));
        }
        return hex.toString();
    }
}
