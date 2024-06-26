package de.haevn.utils.network;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static de.haevn.utils.network.NetworkUtils.isUrl;

/**
 * <h1>NetworkInteraction</h1>
 * <br>
 *
 * <h2>Congiguration file</h2>
 * <p>The configuration file must contain the following properties:</p>
 * <ul>
 *     <li>timeout - The timeout in seconds for the requests</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public final class NetworkInteraction {

    private static int timeout = 10;

    private NetworkInteraction() {
    }

    /**
     * <h1>init({@link File}</h1>
     * <br>
     * <p>Initializes the Network configuration using the given configuration.</p>
     * <p>When the configuration cannot be found or parsed the default values are used.</p>
     * <h2>Example:</h2>
     * <pre>{@code
     * NetworkInteraction.init(new File("config.properties"));
     * }</pre>
     *
     * @param config Configuration file
     */
    public static void init(final File config) {
        Properties properties = new Properties();
        try (final FileInputStream fs = new FileInputStream(config)) {
            properties.load(fs);
            timeout = Integer.parseInt(properties.getProperty("timeout", "10"));
        } catch (IOException ignored) {
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Download
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * Downloads data from the given URL and returns it inside the response.
     *
     * @param url The URL to download the file from
     * @return An {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static Optional<HttpResponse<String>> sendGetRequest(final String url) {
        return sendRequest(HttpMethod.GET, ContentType.JSON, url, HttpRequest.BodyPublishers.noBody());
    }

    /**
     * Sends an asynchronous request with the given method, content type and string as body.
     *
     * @param url The URL to send the request to
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendGetRequestAsync(final String url) {
        return CompletableFuture.supplyAsync(() -> sendGetRequest(url));
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Post
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * Sends a request with the given method, content type and string as body.
     *
     * @param url     The URL to send the request to
     * @param message The message to send as body
     * @return An {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static Optional<HttpResponse<String>> sendPostRequest(final String url, final String message) {
        return sendRequest(HttpMethod.POST, ContentType.JSON, url, message);
    }

    /**
     * Sends an asynchronous request with the given method, content type and string as body.
     *
     * @param url     The URL to send the request to
     * @param message The message to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendPostRequestAsync(final String url, final String message) {
        return sendRequestAsync(HttpMethod.POST, ContentType.JSON, url, message);
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Put
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * Sends a request with the given method, content type and string as body.
     *
     * @param url     The URL to send the request to
     * @param message The message to send as body
     * @return An {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static Optional<HttpResponse<String>> sendPutRequest(final String url, final String message) {
        return sendRequest(HttpMethod.PUT, ContentType.JSON, url, message);
    }

    /**
     * Sends an asynchronous request with the given method, content type and string as body.
     *
     * @param url     The URL to send the request to
     * @param message The message to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendPutRequestAsync(final String url, final String message) {
        return sendRequestAsync(HttpMethod.PUT, ContentType.JSON, url, message);
    }


    //----------------------------------------------------------------------------------------------------------------------
    //
    //----------------------------------------------------------------------------------------------------------------------


    /**
     * Sends a request with the given method, content type and string as body.
     *
     * @param url     The URL to send the request to
     * @param message The message to send as body
     * @return An {@link Optional} with the response if the request was successful, otherwise an empty optional
     */
    public static Optional<HttpResponse<String>> sendDeleteRequest(final String url, final String message) {
        return sendRequest(HttpMethod.DELETE, ContentType.JSON, url, message);
    }

    /**
     * Sends an asynchronous request with the given method, content type and string as body.
     *
     * @param url     The URL to send the request to
     * @param message The message to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, String)
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendDeleteRequestAsync(final String url, final String message) {
        return sendRequestAsync(HttpMethod.DELETE, ContentType.JSON, url, message);
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Generic request methods
    //----------------------------------------------------------------------------------------------------------------------

    // Send from string


    /**
     * Sends an asynchronous request with the given method, content type and string as body.
     *
     * @param method      The HTTP method to use
     * @param contentType The content type of the body
     * @param url         The URL to send the request to
     * @param message     The message to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, String)
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendRequestAsync(final HttpMethod method, final ContentType contentType, final String url, final String message) {
        return CompletableFuture.supplyAsync(() -> sendRequest(method, contentType, url, message));
    }

    /**
     * Sends a request with the given method, content type and string as body.
     *
     * @param method      The HTTP method to use
     * @param contentType The content type of the body
     * @param url         The URL to send the request to
     * @param message     The message to send as body
     * @return An optional containing the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    private static Optional<HttpResponse<String>> sendRequest(final HttpMethod method, final ContentType contentType, final String url, final String message) {
        return sendRequest(method, contentType, url, HttpRequest.BodyPublishers.ofString(message));
    }


    // Send from bytes

    /**
     * Sends an asynchronous request with the given method, content type and an array of bytes as body.
     *
     * @param method      The HTTP method to use
     * @param contentType The content type of the body
     * @param url         The URL to send the request to
     * @param bytes       The bytes to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, byte[])
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendRequestAsync(final HttpMethod method, final ContentType contentType, final String url, final byte[] bytes) {
        return CompletableFuture.supplyAsync(() -> sendRequest(method, contentType, url, bytes));
    }

    /**
     * Sends a request with the given method, content type and file as body.
     *
     * @param method      The HTTP method to use
     * @param contentType The content type of the body
     * @param url         The URL to send the request to
     * @param bytes       The bytes to send as body
     * @return An optional containing the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    public static Optional<HttpResponse<String>> sendRequest(final HttpMethod method, final ContentType contentType, final String url, final byte[] bytes) {
        return sendRequest(method, contentType, url, HttpRequest.BodyPublishers.ofByteArray(bytes));
    }


    // Send from file

    /**
     * Sends an asynchronous request with the given method, content type and file as body.
     *
     * @param method      The HTTP method to use
     * @param contentType The content type of the body
     * @param url         The URL to send the request to
     * @param file        The file to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     * @see NetworkInteraction#sendRequest(HttpMethod, ContentType, String, Path)
     * @see #sendRequest(HttpMethod method, ContentType contentType, String url, Path file)
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendRequestAsync(final HttpMethod method, final ContentType contentType, final String url, final Path file) {
        return CompletableFuture.supplyAsync(() -> sendRequest(method, contentType, url, file));
    }

    /**
     * Sends a request with the given method, content type and file as body.
     *
     * @param method      The HTTP method to use
     * @param contentType The content type of the body
     * @param url         The URL to send the request to
     * @param file        The file to send as body
     * @return An optional containing the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    public static Optional<HttpResponse<String>> sendRequest(final HttpMethod method, final ContentType contentType, final String url, final Path file) {
        try {
            return sendRequest(method, contentType, url, HttpRequest.BodyPublishers.ofFile(file));
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }


    // Send from input stream

    /**
     * Sends a request with the given method, content type and file as body.
     *
     * @param method         The HTTP method to use
     * @param contentType    The content type of the body
     * @param url            The URL to send the request to
     * @param streamSupplier A supplier for the input stream to send as body
     * @return An {@link CompletableFuture} containing and {@link Optional} with the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, Supplier)
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    public static CompletableFuture<Optional<HttpResponse<String>>> sendRequestAsync(final HttpMethod method, final ContentType contentType, final String url, final Supplier<? extends InputStream> streamSupplier) {
        return CompletableFuture.supplyAsync(() -> sendRequest(method, contentType, url, streamSupplier));
    }

    /**
     * Sends a request with the given method, content type and file as body.
     *
     * @param method         The HTTP method to use
     * @param contentType    The content type of the body
     * @param url            The URL to send the request to
     * @param streamSupplier A supplier for the input stream to send as body
     * @return An optional containing the response if the request was successful, otherwise an empty optional
     * @see #sendRequest(HttpMethod, ContentType, String, HttpRequest.BodyPublisher)
     */
    public static Optional<HttpResponse<String>> sendRequest(final HttpMethod method, final ContentType contentType, final String url, final Supplier<? extends InputStream> streamSupplier) {
        return sendRequest(method, contentType, url, HttpRequest.BodyPublishers.ofInputStream(streamSupplier));
    }


    // Actual sending method

    /**
     * Sends a request with the given method, content type and body publisher.
     *
     * @param method        The HTTP {@link HttpMethod} to use
     * @param contentType   The {@link ContentType} of the body
     * @param url           The URL to send the request to
     * @param bodyPublisher The {@link HttpRequest.BodyPublisher} to use
     * @return An optional containing the response if the request was successful, otherwise an empty optional
     */
    private static Optional<HttpResponse<String>> sendRequest(final HttpMethod method, final ContentType contentType, final String url, final HttpRequest.BodyPublisher bodyPublisher) {
        try {

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .method(method.getValue(), bodyPublisher)
                    .setHeader("Content-Type", contentType.getValue())
                    .timeout(java.time.Duration.ofSeconds(timeout))
                    .build();
            var result = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(result);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return Optional.empty();
        }
    }


    /**
     * Opens the given URL in the default browser.
     *
     * @param url The URL to open
     */
    public static void openWebsite(final String url) {
        if (isUrl(url)) {
            try {
                Desktop.getDesktop().browse(URI.create(url));
            } catch (IOException ignored) {
            }
        }
    }

}
