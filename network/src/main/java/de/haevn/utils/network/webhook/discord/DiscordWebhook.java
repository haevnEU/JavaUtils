package de.haevn.utils.network.webhook.discord;

import de.haevn.utils.SerializationUtils;
import de.haevn.utils.exceptions.ValidationFailedException;
import de.haevn.utils.logging.Logger;
import de.haevn.utils.network.NetworkInteraction;
import de.haevn.utils.network.NetworkUtils;
import de.haevn.utils.network.webhook.IWebhook;

import java.util.List;

/**
 * This class is used to send embeds to discord webhooks
 * <br>
 * <b>Example</b>
 * <pre>
 * {@code
 * final Embed embed = Embed.title("Title").description("Description").build();
 * new 1DiscordWebhook(webhookUrl).send(embed);
 * }
 * </pre>
 * <a href="https://discord.com/developers/docs/resources/webhook>Discord Webhook resource</a>
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public class DiscordWebhook implements IWebhook<Embed> {
    private static final Logger LOGGER = new Logger(DiscordWebhook.class);
    private final String url;

    public DiscordWebhook(final String url) {
        this.url = url;
    }

    /**
     * Get the size of the embed
     *
     * @param embed the embed
     * @return the size of the embed
     */
    private long sizeOfField(final Embed embed) {
        long titleLength = embed.getTitle() != null ? embed.getTitle().length() : 0;
        long descriptionLength = embed.getDescription() != null ? embed.getDescription().length() : 0;
        long footerLength = embed.getFooter() != null ? embed.getFooter().text().length() : 0;
        long authorLength = embed.getAuthor() != null ? embed.getAuthor().name().length() : 0;
        long fieldsLength = null != embed.getFields() ? embed.getFields().stream().mapToLong(f -> f.name().length() + f.value().length()).sum() : 0;
        return titleLength + descriptionLength + footerLength + authorLength + fieldsLength;
    }

    private void validateMeta(final Embed embed) throws ValidationFailedException {
        if (embed.getThumbnail() != null && embed.getTitle().length() > 256) {
            throw new ValidationFailedException("Title is too long");
        }
        if (embed.getDescription() != null && embed.getDescription().length() > 4096) {
            throw new ValidationFailedException("Description is too long");
        }
    }

    private void validateField(final List<EmbedField> fields) throws ValidationFailedException {
        if (null == fields) {
            return;
        }

        if (fields.size() > 25) {
            throw new ValidationFailedException("Too many fields");
        }

        if (fields.stream().anyMatch(f -> f.name().length() > 256)) {
            throw new ValidationFailedException("Field name is too long");
        }

        if (fields.stream().anyMatch(f -> f.value().length() > 1024)) {
            throw new ValidationFailedException("Field value is too long");
        }
    }

    private void validateFooter(final EmbedFooter footer) throws ValidationFailedException {
        if (null == footer) {
            return;
        }

        if (footer.text().length() > 2048) {
            throw new ValidationFailedException("Footer text is too long");
        }
    }

    private void validateAuthor(final EmbedAuthor author) throws ValidationFailedException {
        if (null == author) {
            return;
        }
        if (author.name().length() > 256) {
            throw new ValidationFailedException("Author name is too long");
        }
    }

    /**
     * Send the embed
     *
     * @param embed the embed
     * @throws ValidationFailedException if the validation failed
     */
    public void send(final Embed embed) throws ValidationFailedException {
        LOGGER.atInfo().withMessage("Sending embed").log();

        validateMeta(embed);
        validateField(embed.getFields());
        validateFooter(embed.getFooter());
        validateAuthor(embed.getAuthor());
        if (sizeOfField(embed) > 6000) {
            throw new ValidationFailedException("Total length is too long");
        }

        SerializationUtils.exportJson(embed).ifPresent(e -> {
            final String data = "{\"embeds\": [" + e + "]}";
            LOGGER.atDebug().withMessage("Sending %s", data).log();
            NetworkInteraction.sendPostRequest(url, data).ifPresent(response -> {
                if (NetworkUtils.is2xx(response.statusCode())) {
                    LOGGER.atInfo().withMessage("Success").log();
                } else {
                    LOGGER.atError().withMessage("Error %s: %s", response.statusCode(), response.body()).log();
                }
            });
        });
    }
}
