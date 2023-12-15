package de.haevn.utils.network.discord.embed;

import lombok.Builder;
import lombok.Data;

@Builder
public record EmbedFooter(String text, String icon_url, String proxy_icon_url) {}
