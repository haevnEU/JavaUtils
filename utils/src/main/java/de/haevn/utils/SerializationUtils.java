package de.haevn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Optional;

/**
 * <h1>SerializationUtils</h1>
 * <p>SerializationUtils provides utility methods for serialization and deserialization of objects.</p>
 *
 * @author Haevn
 * @version 1.0
 * @since 1.1
 * @deprecated This class is deprecated and will be removed in the next version.
 */
public final class SerializationUtils {

    /**
     * The JSON mapper with default configuration.
     */
    private static final SerializationUtilsV2 jsonMapper = SerializationUtilsV2.json();
    /**
     * The XML mapper with default configuration.
     */
    private static final SerializationUtilsV2 xmlMapper = SerializationUtilsV2.xml();

    private SerializationUtils() {
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Regular
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * <h2>parseXml(String, {@link Class})</h2>
     * <p>Parses the given XML string to the given class.</p>
     * <p>It uses the default XML mapper.</p>
     *
     * @param xml  The XML string.
     * @param type The class to parse the XML to.
     * @param <T>  The type of the object.
     * @return The parsed object.
     * @throws JsonProcessingException If the parsing fails.
     */
    public static <T> T parseXml(final String xml, final Class<T> type) throws JsonProcessingException {
        return xmlMapper.parse(xml, type);
    }

    /**
     * <h2>parseXml(String, {@link TypeReference})</h2>
     * <p>Parses the given XML string to the given type reference.</p>
     * <p>It uses the default XML mapper.</p>
     *
     * @param xml  The XML string.
     * @param type The type reference to parse the XML to.
     * @param <T>  The type of the object.
     * @return The parsed object.
     * @throws JsonProcessingException If the parsing fails.
     */
    public static <T> T parseXml(final String xml, final TypeReference<T> type) throws JsonProcessingException {
        return xmlMapper.parse(xml, type);
    }

    /**
     * <h2>parseJson(String, {@link Class})</h2>
     * <p>Parses the given JSON string to the given class.</p>
     * <p>It uses the default JSON mapper.</p>
     *
     * @param json The JSON string.
     * @param type The class to parse the JSON to.
     * @param <T>  The type of the object.
     * @return The parsed object.
     * @throws JsonProcessingException If the parsing fails.
     */
    public static <T> T parseJson(final String json, final Class<T> type) throws JsonProcessingException {
        return jsonMapper.parse(json, type);
    }

    /**
     * <h2>parseJson(String, {@link TypeReference})</h2>
     * <p>Parses the given JSON string to the given type reference.</p>
     * <p>It uses the default JSON mapper.</p>
     *
     * @param json The JSON string.
     * @param type The type reference to parse the JSON to.
     * @param <T>  The type of the object.
     * @return The parsed object.
     * @throws JsonProcessingException If the parsing fails.
     */
    public static <T> T parseJson(final String json, final TypeReference<T> type) throws JsonProcessingException {
        return jsonMapper.parse(json, type);
    }


    public static <T> Optional<T> getElementSecure(final String json, final Class<T> type, final String... keys) {
        return jsonMapper.useSafeModule().getElement(json, type, keys);
    }

    public static <T> T getElement(final String json, final Class<T> type, final String... keys) throws JsonProcessingException {
        try {
            return jsonMapper.getElement(json, type, keys);
        } catch (IOException e) {
            throw new JsonProcessingException(e.getMessage()) {
            };
        }
    }

    public static <T> Optional<T> getElementSecure(final String json, final TypeReference<T> type, final String... keys) {
        return jsonMapper.useSafeModule().getElement(json, type, keys);
    }

    public static <T> T getElement(final String json, final TypeReference<T> type, final String... keys) throws JsonProcessingException {
        try {
            return jsonMapper.getElement(json, type, keys);
        } catch (IOException e) {
            throw new JsonProcessingException(e.getMessage()) {
            };
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    //  Secure parsing
    //----------------------------------------------------------------------------------------------------------------------

    public static <T> Optional<T> parseXmlSecure(final String xml, final Class<T> type) {
        return xmlMapper.useSafeModule().parse(xml, type);
    }

    public static <T> Optional<T> parseXmlSecure(final String xml, final TypeReference<T> type) {
        return xmlMapper.useSafeModule().parse(xml, type);
    }


    public static <T> Optional<T> parseJsonSecure(final String json, final Class<T> type) {
        return jsonMapper.useSafeModule().parse(json, type);
    }

    public static <T> Optional<T> parseJsonSecure(final String json, final TypeReference<T> type) {
        return jsonMapper.useSafeModule().parse(json, type);
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Export
    //----------------------------------------------------------------------------------------------------------------------

    public static Optional<String> exportXml(final Object data) {
        return xmlMapper.useSafeModule().export(data);
    }

    public static Optional<String> exportJson(final Object data) {
        return jsonMapper.useSafeModule().export(data);
    }

//----------------------------------------------------------------------------------------------------------------------

    public static void disablePretty() {
    }

    public static void enablePretty() {
    }

    public static void disableUnknownProperties() {
    }

    public static void enableUnknownProperties() {
    }
}
