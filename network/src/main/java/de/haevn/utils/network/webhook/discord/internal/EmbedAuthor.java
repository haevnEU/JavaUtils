package de.haevn.utils.network.webhook.discord.internal;

import lombok.Builder;

@Builder
public record EmbedAuthor(String name, String url, String icon_url, String proxy_icon_url) {
}
