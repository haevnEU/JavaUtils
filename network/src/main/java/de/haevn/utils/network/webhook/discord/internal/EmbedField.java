package de.haevn.utils.network.webhook.discord.internal;

import lombok.Builder;

@Builder
public record EmbedField(String name, String value, boolean inline) {
}
