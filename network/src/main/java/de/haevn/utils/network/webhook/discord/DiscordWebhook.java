package de.haevn.utils.network.webhook.discord;

import de.haevn.utils.SerializationUtilsV2;
import de.haevn.utils.exceptions.ValidationFailedException;
import de.haevn.utils.logging.Logger;
import de.haevn.utils.network.NetworkInteraction;
import de.haevn.utils.network.NetworkUtils;
import de.haevn.utils.network.webhook.IWebhook;

import java.util.List;

/**
 * <h1>DiscordWebhook</h1>
 * <p>This class allows you to send data to a discord server using a webhook</p>
 * <br>
 * <b>Example</b>
 * <pre>
 * {@code
 * final Embed embed = Embed.title("Title").description("Description").build();
 * new DiscordWebhook(webhookUrl).send(embed);
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

    /**
     * <h1>DiscordWebhook(String)</h1>
     * <p>Creates a new DiscordWebhook</p>
     * <br>
     * <p>Follow these steps to create a webhook</p>
     * <ol>
     *     <li>Open the server where you want to add the webhook</li>
     *     <li>Click on the channel where you want to send the webhook</li>
     *     <li>Click on the settings icon</li>
     *     <li>Click on integrations</li>
     *     <li>When a webhook is already created click on view</li>
     *     <li>Click on create webhook</li>
     *     <li>Enter the name of the webhook</li>
     *     <li>Click on copy webhook url</li>
     *     <li>Click on save</li>
     *     <li>Use the url to create a new DiscordWebhook</li>
     * </ol>
     *
     * @param url the url of the webhook
     */
    public DiscordWebhook(final String url) {
        this.url = url;
    }

    /**
     * <h1>sizeOfField(Embed)</h1>
     * <p>This is an internal method to calculate the size of the embed</p>
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

    /**
     * <h1>validateMeta(Embed)</h1>
     * <p>This is an internal method to validate the meta data of the embed</p>
     *
     * @param embed the embed
     * @throws ValidationFailedException if the validation failed
     */
    private void validateMeta(final Embed embed) throws ValidationFailedException {
        if (embed.getThumbnail() != null && embed.getTitle().length() > 256) {
            throw new ValidationFailedException("Title is too long");
        }
        if (embed.getDescription() != null && embed.getDescription().length() > 4096) {
            throw new ValidationFailedException("Description is too long");
        }
    }

    /**
     * <h1>validateField(List)</h1>
     * <p>This is an internal method to validate the fields of the embed</p>
     *
     * @param fields the fields
     * @throws ValidationFailedException if the validation failed
     */
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

    /**
     * <h1>validateFooter(EmbedFooter)</h1>
     * <p>This is an internal method to validate the footer of the embed</p>
     *
     * @param footer the footer
     * @throws ValidationFailedException if the validation failed
     */
    private void validateFooter(final EmbedFooter footer) throws ValidationFailedException {
        if (null == footer) {
            return;
        }

        if (footer.text().length() > 2048) {
            throw new ValidationFailedException("Footer text is too long");
        }
    }

    /**
     * <h1>validateAuthor(EmbedAuthor)</h1>
     * <p>This is an internal method to validate the author of the embed</p>
     *
     * @param author the author
     * @throws ValidationFailedException if the validation failed
     */
    private void validateAuthor(final EmbedAuthor author) throws ValidationFailedException {
        if (null == author) {
            return;
        }
        if (author.name().length() > 256) {
            throw new ValidationFailedException("Author name is too long");
        }
    }

    /**
     * <h1>send(Embed)</h1>
     * <p>This method sends the embed to the webhook</p>
     *
     * <h3>Example</h3>
     * <pre>{@code
     * final Embed embed = Embed.title("Title").description("Description").build();
     * new DiscordWebhook(webhookUrl).send(embed);
     * }
     * </pre>
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

        SerializationUtilsV2.json().useSafeModule().export(embed).ifPresent(e -> {
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
