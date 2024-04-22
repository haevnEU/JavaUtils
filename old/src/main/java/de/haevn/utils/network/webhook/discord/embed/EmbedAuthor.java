package de.haevn.utils.network.webhook.discord.embed;

import lombok.Builder;

@Builder
public record EmbedAuthor(String name, String url, String icon_url, String proxy_icon_url) {
}
