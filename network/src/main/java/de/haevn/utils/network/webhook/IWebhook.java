package de.haevn.utils.network.webhook;

import de.haevn.utils.exceptions.ValidationFailedException;

/**
 * <h1>IWebhook</h1>
 * <p>This interface is used to abstract the sending of data to a webhook</p>
 *
 * @param <T> the type of the data to send
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public interface IWebhook<T> {

    /**
     * <h2>send(T)</h2>
     * <p>This method sends the data to the webhook</p>
     *
     * @param data the data to send
     * @throws ValidationFailedException if the validation of the data fails
     */
    void send(final T data) throws ValidationFailedException;

    /**
     * <h2>sendWithoutException(T)</h2>
     * <p>This method sends the data to the webhook without throwing an exception</p>
     *
     * @param data the data to send
     * @return true iff the data was sent successfully, false otherwise
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
