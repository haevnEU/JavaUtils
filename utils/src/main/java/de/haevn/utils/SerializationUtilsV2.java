package de.haevn.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * <h1>SerializationUtils</h1>
 * <p>This class provides access to serialization and deserialization methods.</p>
 * <p>It uses a pipeline pattern to configure the serialization and deserialization process.</p>
 * <p>Supported formats are</p>
 * <ul>
 *     <li>JSON</li>
 *     <li>XML, accessing elements is not yet supported</li>
 *     <li>Custom</li>
 * </ul>
 * <p>It uses Jackson for JSON and XML serialization.</p>
 * <p>New formats can be added but its advised to create a new issue for it.</p>
 */
public class SerializationUtilsV2 {
    private final ObjectMapper mapper;

    private SerializationUtilsV2(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * <h2>json()</h2>
     * <p>Creates a new instance of SerializationUtilsV2 with a JSON mapper.</p>
     *
     * @return A new instance of SerializationUtilsV2 with a JSON mapper.
     */
    public static SerializationUtilsV2 json() {
        return new SerializationUtilsV2(new JsonMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(SerializationFeature.INDENT_OUTPUT));
    }

    /**
     * <h2>xml()</h2>
     * <p>Creates a new instance of SerializationUtilsV2 with a XML mapper.</p>
     *
     * @return A new instance of SerializationUtilsV2 with a XML mapper.
     */
    public static SerializationUtilsV2 xml() {
        return new SerializationUtilsV2(new XmlMapper()
                .enable(ToXmlGenerator.Feature.WRITE_XML_1_1)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                .enable(SerializationFeature.INDENT_OUTPUT));
    }

    /**
     * <h2>custom({@link ObjectMapper})</h2>
     * <p>Creates a new instance of SerializationUtilsV2 with a custom mapper.</p>
     *
     * @param mapper The custom mapper.
     * @return A new instance of SerializationUtilsV2 with a custom mapper.
     */
    public static SerializationUtilsV2 custom(final ObjectMapper mapper) {
        return new SerializationUtilsV2(mapper);
    }

    /**
     * <h2>enable({@link DeserializationFeature})</h2>
     * <p>Enables the given deserialization feature.</p>
     *
     * @param feature The feature to enable.
     * @return The current instance of SerializationUtilsV2.
     * @see ObjectMapper#enable(DeserializationFeature)
     */
    public SerializationUtilsV2 enable(final SerializationFeature feature) {
        mapper.enable(feature);
        return this;
    }

    /**
     * <h2>disable({@link SerializationFeature})</h2>
     * <p>Disables the given serialization feature.</p>
     *
     * @param feature The feature to disable.
     * @return The current instance of SerializationUtilsV2.
     * @see ObjectMapper#disable(SerializationFeature)
     */
    public SerializationUtilsV2 disable(final SerializationFeature feature) {
        mapper.disable(feature);
        return this;
    }

    /**
     * <h2>configure({@link DeserializationFeature}, boolean)</h2>
     * <p>Configures the given deserialization feature with the given state.</p>
     *
     * @param feature The feature to configure.
     * @param state   The state to set.
     * @return The current instance of SerializationUtilsV2.
     * @see ObjectMapper#configure(DeserializationFeature, boolean)
     */
    public SerializationUtilsV2 configure(final DeserializationFeature feature, final boolean state) {
        mapper.configure(feature, state);
        return this;
    }

    /**
     * <h2>configure({@link SerializationFeature}, boolean)</h2>
     * <p>Configures the given serialization feature with the given state.</p>
     *
     * @param feature The feature to configure.
     * @param state   The state to set.
     * @return The current instance of SerializationUtilsV2.
     * @see ObjectMapper#configure(SerializationFeature, boolean)
     */
    public SerializationUtilsV2 configure(final SerializationFeature feature, final boolean state) {
        mapper.configure(feature, state);
        return this;
    }

    /**
     * <h2>useSafeModule()</h2>
     * <p>Creates a new instance of SafeSerialization.</p>
     * <p>SafeSerialization provides utility methods for safe serialization.</p>
     *
     * @return A new instance of SafeSerialization
     */
    public SafeSerialization useSafeModule() {
        return new SafeSerialization(this);
    }

    //----------------------------------------------------------------------------------------------------------------------
    //    Parsing
    // ----------------------------------------------------------------------------------------------------------------------

    /**
     * <h2>parse(String, {@link Object})</h2>
     * <p>Parses the given content to the given type.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     SerializationUtils.json().parse("{\"name\":\"John\"}", Person.class);
     * }
     * </pre>
     *
     * @param content The content to serialize.
     * @param type    The type of the content.
     * @param <T>     The type of the content.
     * @return The serialized content.
     * @throws JsonProcessingException If the serialization fails.
     */
    public <T> T parse(final String content, final Class<T> type) throws JsonProcessingException {
        return mapper.readValue(content, type);
    }

    /**
     * <h2>parse(String, {@link TypeReference})</h2>
     * <p>Parses the given content to the given type.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *    SerializationUtils.json().parse("{\"name\":\"John\"}", new TypeReference<Person>(){});
     * }
     * </pre>
     *
     * @param content The content to serialize.
     * @param type    The type of the content.
     * @param <T>     The type of the content.
     * @return The serialized content.
     * @throws JsonProcessingException If the serialization fails.
     */
    public <T> T parse(final String content, final TypeReference<T> type) throws JsonProcessingException {
        return mapper.readValue(content, type);
    }

    /**
     * <h2>parse({@link File}, {@link Class})</h2>
     * <p>Parses the given file to the given type.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *    SerializationUtils.json().parse(new File("file.json"), Person.class);
     * }
     * </pre>
     *
     * @param file The file to serialize.
     * @param type The type of the content.
     * @param <T>  The type of the content.
     * @return The serialized content.
     * @throws IOException If the serialization fails.
     */
    public <T> T parse(final File file, final Class<T> type) throws IOException {
        return mapper.readValue(file, type);
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Exporting
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * <h2>export({@link Object})</h2>
     * <p>Exports the given content to a string.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     SerializationUtils.json().export(new Person("John"));
     * }
     * </pre>
     *
     * @param content The content to serialize.
     * @param <T>     The type of the content.
     * @return The serialized content.
     * @throws JsonProcessingException If the serialization fails.
     */
    public <T> String export(final T content) throws JsonProcessingException {
        return mapper.writeValueAsString(content);
    }

    /**
     * <h2>export({@link Object}, {@link File})</h2>
     * <p>Exports the given content to a file.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     SerializationUtils.json().export(new Person("John"), new File("file.json"));
     * }
     * </pre>
     *
     * @param content The content to serialize.
     * @param file    The file to write the content to.
     * @param <T>     The type of the content.
     * @throws IOException If the serialization fails.
     */
    public <T> void export(final T content, final File file) throws IOException {
        mapper.writeValue(file, content);
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Access elements
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * <h2>getElement(String, Class, String...)</h2>
     * <p>Gets an element from a JSON string.</p>
     * <p>Note: The keys are used to access the element.</p>
     * <ul>
     *     <li>Example: getElement("{ "name" : "John" }", String.class, "name") -> "John"</li>
     *     <li>Example: getElement("{ "person" : { "name" : "John" } }", String.class, "person", "name") -> "John"</li>
     * </ul>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     SerializationUtils.json().getElement("{\"name\":\"John\"}", String.class, "name");
     * }
     * </pre>
     *
     * @param doc  The document.
     * @param type The type of the element.
     * @param keys The keys to access the element.
     * @param <T>  The type of the element.
     * @return The element.
     * @throws IOException If the parsing fails.
     */
    public <T> T getElement(final String doc, final Class<T> type, final String... keys) throws IOException {
        JsonNode root = mapper.readTree(doc);
        for (String key : keys) {
            root = root.get(key);
        }
        final JsonParser parser = mapper.treeAsTokens(root);
        return mapper.readValue(parser, type);
    }

    /**
     * <h2>getElement(String, TypeReference, String...)</h2>
     * <p>Gets an element from a JSON string.</p>
     * <p>Note: The keys are used to access the element.</p>
     * <ul>
     *    <li>Example: getElement("{ "name" : "John" }", new TypeReference<String>(){}, "name") -> "John"</li>
     *    <li>Example: getElement("{ "person" : { "name" : "John" } }", new TypeReference<String>(){}, "person", "name") -> "John"</li>
     * </ul>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     SerializationUtils.json().getElement("{\"name\":\"John\"}", new TypeReference<String>(){}, "name");
     * }
     * </pre>
     *
     * @param doc  The document.
     * @param type The type of the element.
     * @param keys The keys to access the element.
     * @param <T>  The type of the element.
     * @return The element.
     * @throws IOException If the parsing fails.
     */
    public <T> T getElement(final String doc, final TypeReference<T> type, final String... keys) throws IOException {
        JsonNode root = mapper.readTree(doc);
        for (String key : keys) {
            root = root.get(key);
        }
        final JsonParser parser = mapper.treeAsTokens(root);
        return mapper.readValue(parser, type);
    }

    //----------------------------------------------------------------------------------------------------------------------
    //  Shortcuts
    //----------------------------------------------------------------------------------------------------------------------

    public SerializationUtilsV2 disablePretty() {
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        return this;
    }

    public SerializationUtilsV2 enablePretty() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return this;
    }

    public SerializationUtilsV2 disableUnknownProperties() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return this;
    }

    public SerializationUtilsV2 enableUnknownProperties() {
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return this;
    }


    /**
     * <h1>SafeSerialization</h1>
     * <p>SaveSerialization provides utility methods for safe serialization.</p>
     * <p>This class wraps the serialization methods and catches all exceptions.</p>
     */
    public static final class SafeSerialization {
        private final SerializationUtilsV2 base;

        /**
         * <h2>SafeSerialization({@link SerializationUtils})</h2>
         * <p>Creates a new instance of SafeSerialization.</p>
         *
         * @param base The base instance.
         */
        private SafeSerialization(final SerializationUtilsV2 base) {
            this.base = base;
        }

        /**
         * <h2>parse(String, {@link Object})</h2>
         * <p>Parses the given content to the given type.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SerializationUtils.json().useSafeModule().parse("{\"name\":\"John\"}", Person.class);
         * }
         * </pre>
         *
         * @param content The content to serialize.
         * @param type    The type of the content.
         * @param <T>     The type of the content.
         * @return The serialized content.
         */
        public <T> Optional<T> parse(final String content, final Class<T> type) {
            try {
                T value = base.parse(content, type);
                return Optional.of(value);
            } catch (JsonProcessingException e) {
                return Optional.empty();
            }
        }

        /**
         * <h2>parse(String, {@link TypeReference})</h2>
         * <p>Parses the given content to the given type.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *    SerializationUtils.json().useSafeModule().parse("{\"name\":\"John\"}", new TypeReference<Person>(){});
         * }
         * </pre>
         *
         * @param content The content to serialize.
         * @param type    The type of the content.
         * @param <T>     The type of the content.
         * @return The serialized content.
         */
        public <T> Optional<T> parse(final String content, final TypeReference<T> type) {
            try {
                T value = base.parse(content, type);
                return Optional.of(value);
            } catch (JsonProcessingException e) {
                return Optional.empty();
            }
        }

        /**
         * <h2>parse({@link File}, {@link Class})</h2>
         * <p>Parses the given file to the given type.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *    SerializationUtils.json().useSafeModule().parse(new File("file.json"), Person.class);
         * }
         * </pre>
         *
         * @param file The file to serialize.
         * @param type The type of the content.
         * @param <T>  The type of the content.
         * @return The serialized content.
         */
        public <T> Optional<T> parse(final File file, final Class<T> type) {
            try {
                final var val = base.parse(file, type);
                return Optional.of(val);
            } catch (IOException e) {
                return Optional.empty();
            }
        }


        //----------------------------------------------------------------------------------------------------------------------
        //  Exporting
        //----------------------------------------------------------------------------------------------------------------------

        /**
         * <h2>export({@link Object})</h2>
         * <p>Exports the given content to a string.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SerializationUtils.json().useSafeModule().export(new Person("John"));
         * }
         * </pre>
         *
         * @param content The content to serialize.
         * @param <T>     The type of the content.
         * @return The serialized content.
         */
        public <T> Optional<String> export(final T content) {
            try {
                return Optional.of(base.export(content));
            } catch (JsonProcessingException e) {
                return Optional.empty();
            }
        }

        /**
         * <h2>export({@link Object}, {@link File})</h2>
         * <p>Exports the given content to a file.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SerializationUtils.json().useSafeModule().export(new Person("John"), new File("file.json"));
         * }
         * </pre>
         *
         * @param content The content to serialize.
         * @param file    The file to write the content to.
         * @param <T>     The type of the content.
         */
        public <T> void export(final T content, final File file) {
            try {
                base.export(content, file);
            } catch (IOException ignored) {
            }
        }


        //----------------------------------------------------------------------------------------------------------------------
        //   Access elements
        //----------------------------------------------------------------------------------------------------------------------

        /**
         * <h2>getElement(String, Class, String...)</h2>
         * <p>Gets an element from a JSON string.</p>
         * <p>Note: The keys are used to access the element.</p>
         * <ul>
         *     <li>Example: getElement("{ "name" : "John" }", String.class, "name") -> "John"</li>
         *     <li>Example: getElement("{ "person" : { "name" : "John" } }", String.class, "person", "name") -> "John"</li>
         * </ul>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SerializationUtils.json().getElement("{\"name\":\"John\"}", String.class, "name");
         * }
         * </pre>
         *
         * @param doc  The document.
         * @param type The type of the element.
         * @param keys The keys to access the element.
         * @param <T>  The type of the element.
         * @return The element.
         */
        public <T> Optional<T> getElement(final String doc, final Class<T> type, final String... keys) {
            try {
                final T value = base.getElement(doc, type, keys);
                return Optional.of(value);
            } catch (IOException e) {
                return Optional.empty();
            }
        }

        /**
         * <h2>getElement(String, TypeReference, String...)</h2>
         * <p>Gets an element from a JSON string.</p>
         * <p>Note: The keys are used to access the element.</p>
         * <ul>
         *    <li>Example: getElement("{ "name" : "John" }", new TypeReference<String>(){}, "name") -> "John"</li>
         *    <li>Example: getElement("{ "person" : { "name" : "John" } }", new TypeReference<String>(){}, "person", "name") -> "John"</li>
         * </ul>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SerializationUtils.json().getElement("{\"name\":\"John\"}", new TypeReference<String>(){}, "name");
         * }
         * </pre>
         *
         * @param doc  The document.
         * @param type The type of the element.
         * @param keys The keys to access the element.
         * @param <T>  The type of the element.
         * @return The element.
         */
        public <T> Optional<T> getElement(final String doc, final TypeReference<T> type, final String... keys) {
            try {
                final T value = base.getElement(doc, type, keys);
                return Optional.of(value);
            } catch (IOException e) {
                return Optional.empty();
            }
        }
    }
}
