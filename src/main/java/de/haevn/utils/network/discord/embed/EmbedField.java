package de.haevn.utils.network.discord.embed;

import lombok.Builder;
import lombok.Data;

@Builder
public record EmbedField(String name, String value, boolean inline) {}
