package de.haevn.utils.network.webhook.discord;

/**
 * <h1>EmbedAuthor</h1>
 * <p>This class represents an author in an embed</p>
 *
 * @param name     the name of the author
 * @param url      the url of the author
 * @param icon_url the icon url of the author
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public record EmbedAuthor(String name, String url, String icon_url) {
    /**
     * <h1>EmbedAuthor</h1>
     * <p>This constructor creates an author without an icon and url</p>
     *
     * @param name the name of the author
     */
    public EmbedAuthor(String name) {
        this(name, null, null);
    }

    /**
     * <h1>EmbedAuthor</h1>
     * <p>This constructor creates an author without an icon</p>
     *
     * @param name the name of the author
     * @param url  the url of the author
     */
    public EmbedAuthor(String name, String url) {
        this(name, url, null);
    }

}
