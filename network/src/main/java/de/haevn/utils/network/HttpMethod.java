package de.haevn.utils.network;

/**
 * <h1>HttpMethod</h1>
 * <br>
 * <p>The type of the HTTP method of a request.</p>
 * <p>Supported methods are:</p>
 * <ul>
 *     <li>NONE</li>
 *     <li>GET</li>
 *     <li>POST</li>
 *     <li>PUT</li>
 *     <li>DELETE</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods">HTTP Methods</a>
 * @since 1.0
 */
public enum HttpMethod {
    NONE("NONE"),
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
