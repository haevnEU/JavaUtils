package de.haevn.utils.network.webhook.discord;

/**
 * <h1>EmbedImage</h1>
 * <p>This class represents an image in an embed</p>
 *
 * @param url    the url of the image
 * @param height the height of the image
 * @param width  the width of the image
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public record EmbedImage(String url, int height, int width) {
}
