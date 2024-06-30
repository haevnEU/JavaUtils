package de.haevn.utils.network.webhook.discord;

/**
 * <h1>EmbedThumbnail</h1>
 * <p>This class represents a video in an embed</p>
 *
 * @param url    the url of the video
 * @param height the height of the video
 * @param width  the width of the video
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public record EmbedThumbnail(String url, int height, int width) {
}
