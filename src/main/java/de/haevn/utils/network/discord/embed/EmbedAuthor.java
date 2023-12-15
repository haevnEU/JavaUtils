package de.haevn.utils.network.discord.embed;

import lombok.Builder;
import lombok.Data;

@Builder
public record EmbedAuthor(String name, String url, String icon_url, String proxy_icon_url){}
