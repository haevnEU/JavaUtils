package de.haevn.utils.network.webhook.discord;

public record EmbedVideo(String url, String proxy_url, int height, int width) {
    public EmbedVideo(String url, int height, int width) {
        this(url, null, height, width);
    }
}
