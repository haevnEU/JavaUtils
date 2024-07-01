package de.haevn.utils.network.webhook.discord;

/**
 * <h1>EmbedField</h1>
 * <p>This class represents a field in an embed</p>
 *
 * @param name   the name of the field
 * @param value  the value of the field
 * @param inline whether the field should be inline
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public record EmbedField(String name, String value, boolean inline) {
    /**
     * <h1>EmbedField</h1>
     * <p>This constructor creates a field that is not inline</p>
     *
     * @param name  the name of the field
     * @param value the value of the field
     */
    public EmbedField(String name, String value) {
        this(name, value, false);
    }
}
