package de.haevn.utils.network.webhook.discord.internal;

import lombok.Builder;

@Builder
public record EmbedProvider(String name, String url) {
}
