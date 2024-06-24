package de.haevn.utils.network.webhook.discord;

public record EmbedThumbnail(String url, String proxy_url, int height, int width) {
    public EmbedThumbnail(String url, int height, int width) {
        this(url, null, height, width);
    }
}
