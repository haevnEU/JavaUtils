package de.haevn.utils.network.webhook.discord;

/**
 * <h1>EmbedFooter</h1>
 * <p>This class represents a footer in an embed</p>
 *
 * @param text     the text of the footer
 * @param icon_url the icon url of the footer
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public record EmbedFooter(String text, String icon_url) {

    /**
     * <h2>EmbedFooter</h2>
     * <p>This constructor creates a footer without an icon</p>
     *
     * @param text the text of the footer
     */
    public EmbedFooter(String text) {
        this(text, null);
    }
}
