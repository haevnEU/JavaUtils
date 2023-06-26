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

    public static <T> T parseXml(String json, Class<T> type) throws JsonProcessingException {
        return xmlMapper.readValue(json, type);
    }

    public static <T> T parseXml(String json, TypeReference<T> type) throws JsonProcessingException {
        return xmlMapper.readValue(json, type);
    }


    public static <T> T parseJson(String json, Class<T> type) throws JsonProcessingException {
        return jsonMapper.readValue(json, type);
    }

    public static <T> T parseJson(String json, TypeReference<T> type) throws JsonProcessingException {
        return jsonMapper.readValue(json, type);
    }

    public static <T> T getElement(String json, Class<T> type, String ... keys) throws JsonProcessingException {
        JsonNode root = jsonMapper.readTree(json);
        for (String key : keys) {
            root = root.get(key);
        }

        return jsonMapper.readValue(root.toString(), type);

    }

    public static <T> T getElement(String json, TypeReference<T> type, String ... keys) throws JsonProcessingException {
        JsonNode root = jsonMapper.readTree(json);
        for (String key : keys) {
            root = root.get(key);
        }

        return jsonMapper.readValue(root.toString(), type);

    }

    //----------------------------------------------------------------------------------------------------------------------
    //  Secure parsing
    //----------------------------------------------------------------------------------------------------------------------

    public static <T> Optional<T> parseXmlSecure(String xml, Class<T> type) {
        try {
            var value = xmlMapper.readValue(xml, type);
            return Optional.of(value);
        } catch (JsonProcessingException ignored) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> parseXmlSecure(String xml, TypeReference<T> type) {
        try {
            var value = xmlMapper.readValue(xml, type);
            return Optional.of(value);
        } catch (JsonProcessingException ignored) {
            return Optional.empty();
        }
    }


    public static <T> Optional<T> parseJsonSecure(String json, Class<T> type) {
        try {
            return Optional.of(jsonMapper.readValue(json, type));
        } catch (JsonProcessingException ex) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> parseJsonSecure(String json, TypeReference<T> type) {
        try {
            return Optional.of(jsonMapper.readValue(json, type));
        } catch (JsonProcessingException ex) {
            return Optional.empty();
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Export
    //----------------------------------------------------------------------------------------------------------------------

    public static Optional<String> exportXml(Object data) {
        try {
            final String result = xmlMapper.writeValueAsString(data);
            return Optional.of(result);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> exportJson(Object data) {
        try {
            final String result = jsonMapper.writeValueAsString(data);
            return Optional.of(result);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
