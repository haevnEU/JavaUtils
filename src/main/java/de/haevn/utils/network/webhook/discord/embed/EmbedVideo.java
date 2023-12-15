package de.haevn.utils.network.webhook.discord.embed;

import lombok.Builder;

@Builder
public record EmbedVideo(String url, String proxy_url, int height, int width) {
}
