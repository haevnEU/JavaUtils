package de.haevn.utils.exceptions;

import java.net.http.HttpResponse;

/**
 * A simple exception class for network exceptions.
 *
 * @version 1.0
 * @since 1.0
 * @see ApplicationException
 * @see RuntimeException
 * @author haevn
 */
public class NetworkException extends ApplicationException {
    private final String url;
    private final int statusCode;
    private final String content;

    public NetworkException(final Throwable other) {
        super(other);
        this.url = "";
        this.statusCode = 0;
        this.content = "";
    }

    public NetworkException(final HttpResponse<?> response) {
        this(response.statusCode(), response.body().toString(), response.uri().toString());
    }

    public NetworkException(final int statusCode, final String content, final String url) {
        super(String.format("NetworkException was thrown with status (%s) from \"%s\" with content \"%s\"", statusCode, url, content));
        this.url = url;
        this.statusCode = statusCode;
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
