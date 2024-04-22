package de.haevn.utils.network.webhook;

import de.haevn.utils.exceptions.ValidationFailedException;

/**
 * Represents a webhook
 *
 * @param <T> the type of the data to send
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public interface IWebhook<T> {

    /**
     * Sends the given Type to the webhook
     *
     * @param data the data to send
     * @throws ValidationFailedException if the validation of the data fails
     */
    void send(final T data) throws ValidationFailedException;

    /**
     * Send the data without throwing an exception
     * <br>
     * It wraps the {@link #send(T)} method
     *
     * @param data the data to send
     * @return true if the embed was sent successfully, false otherwise
     */
    default boolean sendWithoutException(final T data) {
        try {
            send(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
