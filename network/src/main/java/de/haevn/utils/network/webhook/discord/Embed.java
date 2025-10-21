package de.haevn.utils.network.webhook.discord;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * <h1>Embed</h1>
 * <p>This class represents an embed in a discord webhook</p>
 * <br>
 * <b>Example</b>
 * <pre>
 *     {@code
 *     final Embed embed = Embed.title("Title").description("Description").build();
 *     new DiscordWebhook(webhookUrl).send(embed);
 *     }
 * </pre>
 * <p>It utilizes slightly modified builder pattern to create an embed</p>
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
    private EmbedAuthor author;
    private List<EmbedField> fields = new ArrayList<>();


    /**
     * <h2>getTitle()</h2>
     * <p>Gets the title of the embed</p>
     *
     * @return the title of the embed
     */
    public String getTitle() {
        return title;
    }

    /**
     * <h2>setTitle(String)</h2>
     * <p>Sets the title of the embed</p>
     *
     * @param title the title of the embed
     * @return Reference to this object
     */
    public Embed setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * <h2>getType()</h2>
     * <p>Gets the type of the embed</p>
     *
     * @return the type of the embed
     */
    public String getType() {
        return type;
    }

    /**
     * <h2>setType(String)</h2>
     * <p>Sets the type of the embed</p>
     *
     * @param type the type of the embed
     * @return Reference to this object
     */
    public Embed setType(final String type) {
        this.type = type;
        return this;
    }

    /**
     * <h2>getDescription()</h2>
     * <p>Gets the description of the embed</p>
     *
     * @return the description of the embed
     */
    public String getDescription() {
        return description;
    }

    /**
     * <h2>setDescription(String)</h2>
     * <p>Sets the description of the embed</p>
     *
     * @param description the description of the embed
     * @return Reference to this object
     */
    public Embed setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * <h2>getUrl()</h2>
     * <p>Gets the url of the embed</p>
     *
     * @return the url of the embed
     */
    public String getUrl() {
        return url;
    }

    /**
     * <h2>setUrl(String)</h2>
     * <p>Sets the url of the embed</p>
     *
     * @param url the url of the embed
     * @return Reference to this object
     */
    public Embed setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * <h2>getTimestamp()</h2>
     * <p>Gets the timestamp of the embed</p>
     *
     * @return the timestamp of the embed
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * <h2>setTimestamp(long)</h2>
     * <p>Sets the timestamp of the embed</p>
     *
     * @param timestamp the timestamp of the embed
     * @return Reference to this object
     */
    public Embed setTimestamp(final long timestamp) {
        setTimestamp(new Date(timestamp));
        return this;
    }

    /**
     * <h2>setTimestamp({@link Date})</h2>
     * <p>Sets the timestamp of the embed</p>
     *
     * @param date the timestamp of the embed
     * @return Reference to this object
     */
    public Embed setTimestamp(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        this.timestamp = sdf.format(date);
        return this;
    }

    /**
     * <h2>getColor()</h2>
     * <p>Gets the color of the embed</p>
     *
     * @return the color of the embed
     */
    public long getColor() {
        return color;
    }

    /**
     * <h2>setColor(long)</h2>
     * <p>Sets the color of the embed</p>
     *
     * @param color the color of the embed
     * @return Reference to this object
     */
    public Embed setColor(final long color) {
        this.color = color;
        return this;
    }



    /**
     * <h2>setColor({@link DiscordColors})</h2>
     * <p>Sets the color of the embed</p>
     *
     * @param color the color of the embed
     * @return Reference to this object
     */
    public Embed setColor(final DiscordColors color) {
        this.color = color.value;
        return this;
    }

    /**
     * <h2>setColor(String)</h2>
     * <p>Sets the color of the embed</p>
     *
     * @param hexColor the color of the embed
     * @return Reference to this object
     */
    public Embed setColor(final String hexColor) {
        if(hexColor.startsWith("#")) {
            this.color = Long.parseLong(hexColor.substring(1), 16);
            return this;
        }else if(hexColor.startsWith("0x")) {
            this.color = Long.parseLong(hexColor.substring(2), 16);
            return this;
        }
        throw new RuntimeException("Invalid hex color format. Must start with '#' or '0x'");
    }



    /**
     * <h2>setColor(int, int, int)</h2>
     * <p>Sets the color of the embed</p>
     *
     * @param r the red value of the color
     * @param g the green value of the color
     * @param b the blue value of the color
     * @return Reference to this object
     */
    public Embed setColor(final int r, final int g, final int b) {
        this.color = (r << 16) + (g << 8) + b;
        return this;
    }



    /**
     * <h2>getFooter()</h2>
     * <p>Gets the footer of the embed</p>
     *
     * @return the footer of the embed
     */
    public EmbedFooter getFooter() {
        return footer;
    }

    /**
     * <h2>setFooter({@link EmbedFooter})</h2>
     * <p>Sets the footer of the embed</p>
     *
     * @param footer the footer of the embed
     * @return Reference to this object
     */
    public Embed setFooter(final EmbedFooter footer) {
        this.footer = footer;
        return this;
    }

    /**
     * <h2>getImage()</h2>
     * <p>Gets the image of the embed</p>
     *
     * @return the image of the embed
     */
    public EmbedImage getImage() {
        return image;
    }

    /**
     * <h2>setImage({@link EmbedImage})</h2>
     * <p>Sets the image of the embed</p>
     *
     * @param image the image of the embed
     * @return Reference to this object
     */
    public Embed setImage(final EmbedImage image) {
        this.image = image;
        return this;
    }

    /**
     * <h2>getThumbnail()</h2>
     * <p>Gets the thumbnail of the embed</p>
     *
     * @return the thumbnail of the embed
     */
    public EmbedThumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * <h2>setThumbnail({@link EmbedThumbnail})</h2>
     * <p>Sets the thumbnail of the embed</p>
     *
     * @param thumbnail the thumbnail of the embed
     * @return Reference to this object
     */
    public Embed setThumbnail(final EmbedThumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    /**
     * <h2>getVideo()</h2>
     * <p>Gets the video of the embed</p>
     *
     * @return the video of the embed
     */
    public EmbedVideo getVideo() {
        return video;
    }

    /**
     * <h2>setVideo({@link EmbedVideo})</h2>
     * <p>Sets the video of the embed</p>
     *
     * @param video the video of the embed
     * @return Reference to this object
     */
    public Embed setVideo(final EmbedVideo video) {
        this.video = video;
        return this;
    }

    /**
     * <h2>getProvider()</h2>
     * <p>Gets the provider of the embed</p>
     *
     * @return the provider of the embed
     */
    public EmbedProvider getProvider() {
        return provider;
    }

    /**
     * <h2>setProvider({@link EmbedProvider})</h2>
     * <p>Sets the provider of the embed</p>
     *
     * @param provider the provider of the embed
     * @return Reference to this object
     */
    public Embed setProvider(final EmbedProvider provider) {
        this.provider = provider;
        return this;
    }

    /**
     * <h2>getAuthor()</h2>
     * <p>Gets the author of the embed</p>
     *
     * @return the author of the embed
     */
    public EmbedAuthor getAuthor() {
        return author;
    }

    /**
     * <h2>setAuthor({@link EmbedAuthor})</h2>
     * <p>Sets the author of the embed</p>
     *
     * @param author the author of the embed
     * @return Reference to this object
     */
    public Embed setAuthor(final EmbedAuthor author) {
        this.author = author;
        return this;
    }

    /**
     * <h2>addField({@link EmbedField})</h2>
     * <p>Adds a field to the embed</p>
     *
     * @return Reference to this object
     */
    public Embed addField(final EmbedField field) {
        fields.add(field);
        return this;
    }

    /**
     * <h2>getFields()</h2>
     * <p>Gets the fields of the embed</p>
     *
     * @return the fields of the embed
     */
    public List<EmbedField> getFields() {
        return fields;
    }

    /**
     * <h2>setFields({@link List}<{@link EmbedField}>)</h2>
     * <p>Sets the fields of the embed</p>
     *
     * @return Reference to this object
     */
    public Embed setFields(final List<EmbedField> fields) {
        this.fields = fields;
        return this;
    }
}
