package de.haevn.utils.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.stream.Stream;

/**
 * This class contains some useful methods for network related tasks.
 *
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class NetworkUtils {
    private NetworkUtils() {
    }

    /**
     * Checks if the given code is a valid HTTP status code.
     * A valid code must be between 100 and 599.
     *
     * @param code The code to check.
     * @return True if the code is valid, false otherwise.
     */
    public static boolean operationFailed(final int code) {
        return code < 100 || code > 599;
    }

    /**
     * Checks if the given code is a 2xx OK code.
     *
     * @param code The code to check.
     * @return True if the code is a 2xx code, false otherwise.
     */
    public static boolean is2xx(final int code) {
        return code >= 200 && code < 300;
    }

    /**
     * Checks if the given code is not a 2xx OK code.
     *
     * @param code The code to check.
     * @return True if the code is not a 2xx code, false otherwise.
     * @see #is2xx(int)
     */
    public static boolean isNot2xx(final int code) {
        return !is2xx(code);
    }

    /**
     * Checks if the given code is a 3xx redirect code.
     *
     * @param code The code to check.
     * @return True if the code is a 3xx code, false otherwise.
     */
    public static boolean is3xx(final int code) {
        return code >= 300 && code < 400;
    }

    /**
     * Checks if the given code is not a 3xx redirect code.
     *
     * @param code The code to check.
     * @return True if the code is not a 3xx code, false otherwise.
     * @see #is3xx(int)
     */
    public static boolean isNot3xx(final int code) {
        return !is3xx(code);
    }

    /**
     * Checks if the given code is a 4xx client error code.
     *
     * @param code The code to check.
     * @return True if the code is a 4xx code, false otherwise.
     */
    public static boolean is4xx(final int code) {
        return code >= 400 && code < 500;
    }

    /**
     * Checks if the given code is not a 4xx client error code.
     *
     * @param code The code to check.
     * @return True if the code is not a 4xx code, false otherwise.
     * @see #is4xx(int)
     */
    public static boolean isNot4xx(final int code) {
        return !is4xx(code);
    }

    /**
     * Checks if the given code is a 5xx server error code.
     *
     * @param code The code to check.
     * @return True if the code is a 5xx code, false otherwise.
     */
    public static boolean is5xx(final int code) {
        return code >= 500 && code < 600;
    }

    /**
     * Checks if the given code is not a 5xx server error code.
     *
     * @param code The code to check.
     * @return True if the code is not a 5xx code, false otherwise.
     * @see #is5xx(int)
     */
    public static boolean isNot5xx(final int code) {
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
    public static boolean isUrl(final String url) {
        return Stream.of("http", "https", "ftp", "sftp").anyMatch(schema -> url.startsWith(schema + "://"));

    }

    /**
     * Pings the given url and returns a {@link PingResult} object.
     *
     * @param url the url to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final String url) {
        return ping(URI.create(url));
    }

    /**
     * Pings the given url and returns a {@link PingResult} object.
     *
     * @param url  the url to ping
     * @param port the port to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final String url, final int port) {
        return ping(URI.create(url), port);
    }

    /**
     * Pings the given url and returns a {@link PingResult} object.
     *
     * @param uri the uri to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final URI uri) {
        return ping(uri, 7);
    }

    /**
     * Pings the given url and returns a {@link PingResult} object.
     *
     * @param uri  the uri to ping
     * @param port the port to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final URI uri, final int port) {
        final InetAddress address = new InetSocketAddress(uri.getHost(), port).getAddress();
        boolean reachable = false;
        long ttl = Long.MAX_VALUE;
        try {
            final long start = System.currentTimeMillis();
            reachable = address.isReachable(1000);
            ttl = System.currentTimeMillis() - start;
            return new PingResult(uri.getHost(), address.getHostAddress(), ttl, reachable);
        } catch (Exception ignored) {
            return new PingResult(uri.getHost(), "", ttl, reachable);
        }

    }

}
