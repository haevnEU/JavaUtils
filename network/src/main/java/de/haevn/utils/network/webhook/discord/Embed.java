package de.haevn.utils.network.webhook.discord;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * This class is used inside the {@link DiscordWebhook DiscordWebhook} class to send embeds to discord webhooks.
 * <br>
 * <b>Example</b>
 * <pre>
 *     {@code
 *     final Embed embed = Embed.title("Title").description("Description").build();
 *     new DiscordWebhook(webhookUrl).send(embed);
 *     }
 * </pre>
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        setTimestamp(new Date(timestamp));
    }

    public void setTimestamp(final Date date) {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        this.timestamp = sdf.format(date);
    }


    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public EmbedFooter getFooter() {
        return footer;
    }

    public void setFooter(EmbedFooter footer) {
        this.footer = footer;
    }

    public EmbedImage getImage() {
        return image;
    }

    public void setImage(EmbedImage image) {
        this.image = image;
    }

    public EmbedThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(EmbedThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public EmbedVideo getVideo() {
        return video;
    }

    public void setVideo(EmbedVideo video) {
        this.video = video;
    }

    public EmbedProvider getProvider() {
        return provider;
    }

    public void setProvider(EmbedProvider provider) {
        this.provider = provider;
    }

    public EmbedAuthor getAuthor() {
        return author;
    }

    public void setAuthor(final EmbedAuthor author) {
        this.author = author;
    }

    public Embed addField(final EmbedField field) {
        fields.add(field);
        return this;
    }

    public List<EmbedField> getFields() {
        return fields;
    }

    public void setFields(List<EmbedField> fields) {
        this.fields = fields;
    }

    private EmbedAuthor author;
    private List<EmbedField> fields = new ArrayList<>();

}
