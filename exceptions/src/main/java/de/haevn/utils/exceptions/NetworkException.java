package de.haevn.utils.exceptions;

import java.net.http.HttpResponse;

/**
 * <h1>NetworkException</h1>
 * <br>
 * <p>This exception is thrown when a network error occurs.</p>
 * <p>It provides information about the status code, the content and the URL that caused the error.</p>
 *
 * @author haevn
 * @version 1.0
 * @see ApplicationException
 * @see RuntimeException
 * @since 1.0
 */
public class NetworkException extends ApplicationException {
    private final String url;
    private final int statusCode;
    private final String content;

    /**
     * <h2>NetworkException({@link Throwable})</h2>
     * <p>Creates a new NetworkException based on another {@link Throwable}.</p>
     * <h3>Example</h3>
     * <pre>
     * {@code
     *     try {
     *         throw new NetworkException(new IOException("An error occurred"));
     *     } catch (NetworkException e) {
     *         System.out.println("Error: " + e.getMessage());
     *     }
     * }
     * </pre>
     *
     * @param other The other {@link Throwable}.
     */
    public NetworkException(final Throwable other) {
        super(other);
        this.url = "";
        this.statusCode = 0;
        this.content = "";
    }

    /**
     * <h2>NetworkException({@link HttpResponse})</h2>
     * <p>Creates a new NetworkException based on a {@link HttpResponse}.</p>
     * <h3>Example</h3>
     * <pre>
     * {@code
     *     var response = HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create("https://example.com")).build(), HttpResponse.BodyHandlers.ofString());
     *     NetworkException exception = new NetworkException(response);
     * }
     * </pre>
     *
     * @param response The response that caused the exception.
     */
    public NetworkException(final HttpResponse<?> response) {
        this(response.statusCode(), response.body().toString(), response.uri().toString());
    }

    /**
     * <h2>NetworkException({@link String}, int, {@link String})</h2>
     * <p>Creates a new NetworkException with the given status code, content and URL.</p>
     * <h3>Example</h3>
     * <pre>
     * {@code
     *     throw new NetworkException(404, "Not found", "https://example.com");
     * }
     * </pre>
     *
     * @param statusCode The status code.
     * @param content    The content.
     * @param url
     */
    public NetworkException(final int statusCode, final String content, final String url) {
        super(String.format("NetworkException was thrown with status (%s) from \"%s\" with content \"%s\"", statusCode, url, content));
        this.url = url;
        this.statusCode = statusCode;
        this.content = content;
    }

    /**
     * <h2>getStatusCode()</h2>
     *
     * @return The status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * <h2>getContent()</h2>
     *
     * @return The content.
     */
    public String getContent() {
        return content;
    }

    /**
     * <h2>getUrl()</h2>
     *
     * @return The URL.
     */
    public String getUrl() {
        return url;
    }
}
