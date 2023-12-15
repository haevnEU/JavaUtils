package de.haevn.utils.network.discord;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.haevn.utils.network.discord.embed.*;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * This class is used inside the {@link DiscordWebhook} class to send embeds to discord webhooks.
 * <br>
 * <b>Example</b>
 * <pre>
 *     {@code
 *     final Embed embed = Embed.title("Title").description("Description").build();
 *     new DiscordWebhook(webhookUrl).send(embed);
 *     }
 * </pre>
 *
 * @version 1.1
 * @since 1.1
 * @author haevn
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public final class Embed {
    private String title;
    private String type;
    private String description;
    private String url;
    private String timestamp;
    private long color;
    private EmbedFooter footer;
    private EmbedImage image;
    private EmbedThumbnail thumbnail;
    private EmbedVideo video;
    private EmbedProvider provider;
    private EmbedAuthor author;
    private List<EmbedField> fields = List.of();

    /**
     * This class overrides the implementation from Lombok to add a timestamp conversion into ISO8601 format
     * This class is used to build an {@link Embed} object
     * @version 1.1
     * @since 1.1
     * @see Embed
     * @author haevn
     */
    public static class EmbedBuilder {

        private String timestamp;
        public EmbedBuilder timestamp(final long timestamp){
            return timestamp(new Date(timestamp));
        }

        public EmbedBuilder timestamp(final Date date){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            this.timestamp = sdf.format(date);
            return this;
        }
    }
}
