package de.haevn.javautils.network;

/**
 * Supported content types.
 * <ul>
 *     <li>JSON</li>
 *     <li>XML</li>
 *     <li>TEXT</li>
 *     <li>HTML</li>
 *     <li>FORM</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public enum ContentType {
    JSON("application/json"),
    XML("application/xml"),
    TEXT("text/plain"),
    HTML("text/html"),
    FORM("application/x-www-form-urlencoded");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
