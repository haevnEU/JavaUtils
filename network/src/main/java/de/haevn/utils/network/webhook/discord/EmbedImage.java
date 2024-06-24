package de.haevn.utils.network.webhook.discord;

public record EmbedImage(String url, String proxy_url, int height, int width) {
    public EmbedImage(String url, int height, int width) {
        this(url, null, height, width);
    }
}
