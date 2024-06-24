package de.haevn.utils.network.webhook.discord;

public record EmbedField(String name, String value, boolean inline) {
    public EmbedField(String name, String value) {
        this(name, value, false);
    }
}
