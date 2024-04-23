package de.haevn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.Optional;

public final class SerializationUtils {
    private static final ObjectMapper jsonMapper = new JsonMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(SerializationFeature.INDENT_OUTPUT);

    private static final ObjectMapper xmlMapper = new XmlMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    private SerializationUtils() {
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Regular
    //----------------------------------------------------------------------------------------------------------------------

    public static <T> T parseXml(final String json, final Class<T> type) throws JsonProcessingException {
        return xmlMapper.readValue(json, type);
    }

    public static <T> T parseXml(final String json, final TypeReference<T> type) throws JsonProcessingException {
        return xmlMapper.readValue(json, type);
    }


    public static <T> T parseJson(final String json, final Class<T> type) throws JsonProcessingException {
        return jsonMapper.readValue(json, type);
    }

    public static <T> T parseJson(final String json, final TypeReference<T> type) throws JsonProcessingException {
        return jsonMapper.readValue(json, type);
    }

    public static <T> Optional<T> getElementSecure(final String json, final Class<T> type, final String... keys) {
        try {
            return Optional.of(getElement(json, type, keys));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public static <T> T getElement(final String json, final Class<T> type, final String... keys) throws JsonProcessingException {
        JsonNode root = jsonMapper.readTree(json);
        for (String key : keys) {
            root = root.get(key);
        }

        return jsonMapper.readValue(root.toString(), type);
    }

    public static <T> Optional<T>  getElementSecure(final String json, final TypeReference<T> type, final String... keys) {
        try {
            return Optional.of(getElement(json, type, keys));
        } catch (
                JsonProcessingException e) {
            return Optional.empty();
        }

    }

    public static <T> T getElement(final String json, final TypeReference<T> type, final String... keys) throws JsonProcessingException {
        JsonNode root = jsonMapper.readTree(json);
        for (String key : keys) {
            root = root.get(key);
        }

        return jsonMapper.readValue(root.toString(), type);

    }

    //----------------------------------------------------------------------------------------------------------------------
    //  Secure parsing
    //----------------------------------------------------------------------------------------------------------------------

    public static <T> Optional<T> parseXmlSecure(final String xml, final Class<T> type) {
        try {
            var value = xmlMapper.readValue(xml, type);
            return Optional.of(value);
        } catch (JsonProcessingException ignored) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> parseXmlSecure(final String xml, final TypeReference<T> type) {
        try {
            var value = xmlMapper.readValue(xml, type);
            return Optional.of(value);
        } catch (JsonProcessingException ignored) {
            return Optional.empty();
        }
    }


    public static <T> Optional<T> parseJsonSecure(final String json, final Class<T> type) {
        try {
            return Optional.of(jsonMapper.readValue(json, type));
        } catch (JsonProcessingException ex) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> parseJsonSecure(final String json, final TypeReference<T> type) {
        try {
            return Optional.of(jsonMapper.readValue(json, type));
        } catch (JsonProcessingException ex) {
            return Optional.empty();
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Export
    //----------------------------------------------------------------------------------------------------------------------

    public static Optional<String> exportXml(final Object data) {
        try {
            final String result = xmlMapper.writeValueAsString(data);
            return Optional.of(result);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> exportJson(final Object data) {
        try {
            final String result = jsonMapper.writeValueAsString(data);
            return Optional.of(result);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public static void disablePretty() {
        jsonMapper.disable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.disable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void enablePretty() {
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void disableUnknownProperties() {
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static void enableUnknownProperties() {
        jsonMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        xmlMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
