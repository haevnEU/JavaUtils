package de.haevn.utils.network.webhook.discord;

public record EmbedField(String name, String value, boolean inline) {
    public EmbedField(String name, String value) {
        this(name, value, false);
    }

    public EmbedField(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }
}
