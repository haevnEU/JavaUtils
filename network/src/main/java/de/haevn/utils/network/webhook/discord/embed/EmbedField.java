package de.haevn.utils.network.webhook.discord.embed;

import lombok.Builder;

@Builder
public record EmbedField(String name, String value, boolean inline) {
}
