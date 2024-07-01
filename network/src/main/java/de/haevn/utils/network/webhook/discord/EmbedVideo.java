package de.haevn.utils.network.webhook.discord;

/**
 * <h1>EmbedVideo</h1>
 * <p>This class represents a video in an embed</p>
 *
 * @param url       the url of the video
 * @param proxy_url the proxy url of the video
 * @param height    the height of the video
 * @param width     the width of the video
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public record EmbedVideo(String url, String proxy_url, int height, int width) {
    /**
     * <h1>EmbedVideo</h1>
     * <p>This constructor creates a video where the proxy url is the same as the url</p>
     *
     * @param url    the url of the video
     * @param height the height of the video
     * @param width  the width of the video
     */
    public EmbedVideo(String url, int height, int width) {
        this(url, url, height, width);
    }
}
