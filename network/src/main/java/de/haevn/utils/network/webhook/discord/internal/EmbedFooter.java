package de.haevn.utils.network.webhook.discord.internal;

import lombok.Builder;

@Builder
public record EmbedFooter(String text, String icon_url, String proxy_icon_url) {
}
