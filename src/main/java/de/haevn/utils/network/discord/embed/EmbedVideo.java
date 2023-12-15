package de.haevn.utils.network.discord.embed;

import lombok.Builder;
import lombok.Data;

@Builder
public record EmbedVideo(String url, String proxy_url, int height, int width) {
}
