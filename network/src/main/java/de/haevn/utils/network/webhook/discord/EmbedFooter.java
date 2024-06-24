package de.haevn.utils.network.webhook.discord;

public record EmbedFooter(String text, String icon_url, String proxy_icon_url) {
    public EmbedFooter(String text) {
        this(text, null, null);
    }

    public EmbedFooter(String text, String iconUrl) {
        this(text, iconUrl, null);
    }
}
