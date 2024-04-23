package de.haevn.utils.network.webhook.discord.embed;

import lombok.Builder;

@Builder
public record EmbedImage(String url, String proxy_url, int height, int width) {
}
