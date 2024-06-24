package de.haevn.utils.network.webhook.discord;

public record EmbedFooter(String text, String icon_url, String proxy_icon_url) {
    public EmbedFooter(String text) {
        this(text, null, null);
    }

    public EmbedFooter(String text, String icon_url) {
        this(text, icon_url, null);
    }

    public EmbedFooter(String text, String icon_url, String proxy_icon_url) {
        this.text = text;
        this.icon_url = icon_url;
        this.proxy_icon_url = proxy_icon_url;
    }
}
