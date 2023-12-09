package de.haevn.javautils.network;

import java.util.stream.Stream;

/**
 * This class contains some useful methods for network related tasks.
 *
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class NetworkUtils {

    /**
     * Checks if the given code is a valid HTTP status code.
     * A valid code must be between 100 and 599.
     *
     * @param code The code to check.
     * @return True if the code is valid, false otherwise.
     */
    public static boolean operationFailed(int code) {
        return code < 100 || code > 599;
    }

    /**
     * Checks if the given code is a 2xx OK code.
     *
     * @param code The code to check.
     * @return True if the code is a 2xx code, false otherwise.
     */
    public static boolean is2xx(int code) {
        return code >= 200 && code < 300;
    }

    /**
     * Checks if the given code is not a 2xx OK code.
     *
     * @param code The code to check.
     * @return True if the code is not a 2xx code, false otherwise.
     * @see #is2xx(int)
     */
    public static boolean isNot2xx(int code) {
        return !is2xx(code);
    }

    /**
     * Checks if the given code is a 3xx redirect code.
     *
     * @param code The code to check.
     * @return True if the code is a 3xx code, false otherwise.
     */
    public static boolean is3xx(int code) {
        return code >= 300 && code < 400;
    }

    /**
     * Checks if the given code is not a 3xx redirect code.
     *
     * @param code The code to check.
     * @return True if the code is not a 3xx code, false otherwise.
     * @see #is3xx(int)
     */
    public static boolean isNot3xx(int code) {
        return !is3xx(code);
    }

    /**
     * Checks if the given code is a 4xx client error code.
     *
     * @param code The code to check.
     * @return True if the code is a 4xx code, false otherwise.
     */
    public static boolean is4xx(int code) {
        return code >= 400 && code < 500;
    }

    /**
     * Checks if the given code is not a 4xx client error code.
     *
     * @param code The code to check.
     * @return True if the code is not a 4xx code, false otherwise.
     * @see #is4xx(int)
     */
    public static boolean isNot4xx(int code) {
        return !is4xx(code);
    }

    /**
     * Checks if the given code is a 5xx server error code.
     *
     * @param code The code to check.
     * @return True if the code is a 5xx code, false otherwise.
     */
    public static boolean is5xx(int code) {
        return code >= 500 && code < 600;
    }

    /**
     * Checks if the given code is not a 5xx server error code.
     *
     * @param code The code to check.
     * @return True if the code is not a 5xx code, false otherwise.
     * @see #is5xx(int)
     */
    public static boolean isNot5xx(int code) {
        return !is5xx(code);
    }

    /**
     * Checks if the given string is a valid URL.
     * <ul>
     *     Supported schemas:
     *     <li>http://</li>
     *     <li>https://</li>
     *     <li>ftp://</li>
     *     <li>sftp://</li>
     * </ul>
     *
     * @param url The string to check.
     * @return True if the string is starts with a valid url schema, false otherwise.
     */
    public static boolean isUrl(String url) {
        return Stream.of("http", "https", "ftp", "sftp").anyMatch(schema -> url.startsWith(schema + "://"));

    }
}
