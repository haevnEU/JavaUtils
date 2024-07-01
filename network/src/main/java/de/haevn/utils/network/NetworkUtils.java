package de.haevn.utils.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.stream.Stream;

/**
 * <h1>NetworkUtils</h1>
 * <br>
 * <p>Utility class for network related operations.</p>
 *
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class NetworkUtils {
    private NetworkUtils() {
    }

    /**
     * <h1>operationFailed(int)</h1>
     * <br>
     * <p>Checks if the given code is a valid HTTP status code.</p>
     * <p>A valid code must be between 100 and 599.</p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.operationFailed(404)){
     *    System.out.println("Operation failed!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is valid, false otherwise.
     */
    public static boolean operationFailed(final int code) {
        return code < 100 || code > 599;
    }

    /**
     * <h1>is2xx(int)</h1>
     * <br>
     * <p>Checks if the given code is a 2xx OK code.</p>
     * <p>2xx codes are successful codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.is2xx(200)){
     *   System.out.println("Success!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is a 2xx code, false otherwise.
     */
    public static boolean is2xx(final int code) {
        return code >= 200 && code < 300;
    }

    /**
     * <h1>isNot2xx(int)</h1>
     * <br>
     * <p>Checks if the given code is not a 2xx OK code.</p>
     * <p>2xx codes are successful codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.isNot2xx(404)){
     *   System.out.println("Operation failed!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is not a 2xx code, false otherwise.
     */
    public static boolean isNot2xx(final int code) {
        return !is2xx(code);
    }

    /**
     * <h1>is3xx(int)</h1>
     * <br>
     * <p>Checks if the given code is a 3xx redirect code.</p>
     * <p>3xx codes are redirect codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.is3xx(301)){
     *   System.out.println("Redirect!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is a 3xx code, false otherwise.
     */
    public static boolean is3xx(final int code) {
        return code >= 300 && code < 400;
    }

    /**
     * <h1>isNot3xx(int)</h1>
     * <br>
     * <p>Checks if the given code is not a 3xx redirect code.</p>
     * <p>3xx codes are redirect codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.isNot3xx(404)){
     *   System.out.println("Operation failed!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is not a 3xx code, false otherwise.
     */
    public static boolean isNot3xx(final int code) {
        return !is3xx(code);
    }

    /**
     * <h1>is4xx(int)</h1>
     * <br>
     * <p>Checks if the given code is a 4xx client error code.</p>
     * <p>4xx codes are client error codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.is4xx(404)){
     *  System.out.println("Client error!");
     * }
     *  }</pre>
     *
     * @param code The code to check.
     * @return true if the code is a 4xx code, false otherwise.
     */
    public static boolean is4xx(final int code) {
        return code >= 400 && code < 500;
    }

    /**
     * <h1>isNot4xx(int)</h1>
     * <br>
     * <p>Checks if the given code is not a 4xx client error code.</p>
     * <p>4xx codes are client error codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.isNot4xx(200)){
     *   System.out.println("Success!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is not a 4xx code, false otherwise.
     */
    public static boolean isNot4xx(final int code) {
        return !is4xx(code);
    }

    /**
     * <h1>is5xx(int)</h1>
     * <br>
     * <p>Checks if the given code is a 5xx server error code.</p>
     * <p>5xx codes are server error codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.is5xx(500)){
     *   System.out.println("Server error!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is a 5xx code, false otherwise.
     */
    public static boolean is5xx(final int code) {
        return code >= 500 && code < 600;
    }

    /**
     * <h1>isNot5xx(int)</h1>
     * <br>
     * <p>Checks if the given code is not a 5xx server error code.</p>
     * <p>5xx codes are server error codes.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.isNot5xx(200)){
     *   System.out.println("Success!");
     * }
     * }</pre>
     *
     * @param code The code to check.
     * @return True if the code is not a 5xx code, false otherwise.
     */
    public static boolean isNot5xx(final int code) {
        return !is5xx(code);
    }

    /**
     * <h1>isUrl(String)</h1>
     * <br>
     * <p>Checks if the given string is a valid URL.</p>
     * <ul>
     *     Supported schemas:
     *     <li>http://</li>
     *     <li>https://</li>
     *     <li>ftp://</li>
     *     <li>sftp://</li>
     * </ul>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * if(NetworkUtils.isUrl("https://localhost")){
     *  System.out.println("Valid URL!");
     * }
     * }</pre>
     *
     * @param url The string to check.
     * @return True if the string is starts with a valid url schema, false otherwise.
     */
    public static boolean isUrl(final String url) {
        return Stream.of("http", "https", "ftp", "sftp").anyMatch(schema -> url.startsWith(schema + "://"));

    }

    /**
     * <h1>ping(String)</h1>
     * <br>
     * <p>Pings the given url and returns a {@link PingResult} object.</p>
     * <h3>Example:</h3>
     * <pre>{@code
     *  var result = NetworkUtils.ping("https://localhost");
     *  System.out.println(result.printf());
     * }</pre>
     *
     * @param url the url to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final String url) {
        return ping(URI.create(url));
    }

    /**
     * <h1>ping(String, int)</h1>
     * <br>
     * <p>Pings the given url on the given port and returns a {@link PingResult} object.</p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * var result = NetworkUtils.ping("https://localhost", 443);
     * System.out.println(result.printf());
     * }</pre>
     *
     * @param url  the url to ping
     * @param port the port to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final String url, final int port) {
        return ping(URI.create(url), port);
    }

    /**
     * <h1>ping(URI)</h1>
     * <br>
     * <p>Pings the given uri and returns a {@link PingResult} object.</p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * var result = NetworkUtils.ping(URI.create("https://localhost"));
     * System.out.println(result.printf());
     * }</pre>
     *
     * @param uri the uri to ping
     * @return a {@link PingResult} object.
     */
    public static PingResult ping(final URI uri) {
        return ping(uri, 7);
    }

    /**
     * <h1>ping(URI, int)</h1>
     * <br>
     * <p>Pings the given uri on the given port and returns a {@link PingResult} object.</p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * var result = NetworkUtils.ping(URI.create("https://localhost"), 443);
     * System.out.println(result.printf());
     * }</pre>
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
