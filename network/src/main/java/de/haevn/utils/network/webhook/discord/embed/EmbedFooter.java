package de.haevn.utils.network.webhook.discord.embed;

import lombok.Builder;

@Builder
public record EmbedFooter(String text, String icon_url, String proxy_icon_url) {
}
