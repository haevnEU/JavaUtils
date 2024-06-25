package de.haevn.utils.network.webhook.discord;

public record EmbedAuthor(String name, String url, String icon_url) {
    public EmbedAuthor(String name) {
        this(name, null, null);
    }

    public EmbedAuthor(String name, String url) {
        this(name, url, null);
    }

}
