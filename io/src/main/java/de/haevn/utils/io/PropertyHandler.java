package de.haevn.utils.io;

import de.haevn.utils.logging.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <h1>PropertyHandler</h1>
 * <p>The PropertyHandler class provides methods to read and write properties files</p>
 * <p>It provides methods to read and write properties as string, boolean, int, long or double</p>
 * <p>The PropertyHandler can be accessed via two static methods:</p>
 * <ul>
 *     <li>getInstance(String name): Returns a PropertyHandler instance for the given property file</li>
 *     <li>getAllHandler(): Returns a list of all PropertyHandler instances</li>
 * </ul>
 * <p>A static method reloadAll() is provided to reload all property files</p>
 * <h2>Example 1:</h2>
 * <pre>{@code
 * PropertyHandler propertyHandler = PropertyHandler.getInstance("Dummy");
 * String value = propertyHandler.get("key");
 * }</pre>
 * <h2>Example 2:</h2>
 * <pre>{@code
 * PropertyHandler propertyHandler = PropertyHandler.getInstance("Dummy");
 * propertyHandler.set("key", "value");
 * propertyHandler.store();
 * }</pre>
 */
public final class PropertyHandler {
    private static final Logger LOGGER = new Logger(PropertyHandler.class);
    private static final String EXTENSION = ".property";
    private static final Map<String, PropertyHandler> STRING_PROPERTY_HANDLER_HASH_MAP = new HashMap<>();
    private final Properties properties;
    private final String name;

    private final File configFile;

    /**
     * <h2>PropertyHandler</h2>
     * <p>Private constructor to create a PropertyHandler instance</p>
     *
     * @param propertyName the name of the property file
     */
    private PropertyHandler(final String propertyName) {
        properties = new Properties();
        this.name = propertyName;

        configFile = new File(FileUtils.getRootPathWithSeparator() + "config", name + EXTENSION);
        load();
    }

    /**
     * <h2>getInstance(String)</h2>
     * <p>If a PropertyHandler instance for the given property file already exists, it will be returned,
     * otherwise a new PropertyHandler instance will be created</p>
     * <p>If a new PropertyHandler instance is created, it will be stored in a map for later access</p>
     *
     * @param name the name of the property file
     * @return a PropertyHandler instance
     */
    public static PropertyHandler getInstance(final String name) {
        if (STRING_PROPERTY_HANDLER_HASH_MAP.containsKey(name.toUpperCase())) {
            return STRING_PROPERTY_HANDLER_HASH_MAP.get(name.toUpperCase());
        }

        final PropertyHandler handler = new PropertyHandler(name);
        STRING_PROPERTY_HANDLER_HASH_MAP.put(name.toUpperCase(), handler);
        return handler;
    }

    /**
     * <h2>getAllHandler()</h2>
     * <p>Returns a list of all PropertyHandler instances</p>
     *
     * @return a list of all PropertyHandler instances
     */
    public static List<String> getAllHandler() {
        return STRING_PROPERTY_HANDLER_HASH_MAP.keySet().stream().toList();
    }

    /**
     * <h2>reloadAll()</h2>
     * <p>Reloads all property files</p>
     */
    public static void reloadAll() {
        STRING_PROPERTY_HANDLER_HASH_MAP.values().forEach(PropertyHandler::reload);
    }

    /**
     * <h2>reload()</h2>
     * <p>Reloads the property file</p>
     */
    public void reload() {
        load();
    }

    /**
     * <h2>load()</h2>
     * <p>Loads the property file</p>
     */
    public void load() {
        try (final InputStream inputStream = new FileInputStream(configFile)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.atError().forEnclosingMethod()
                    .withException(e)
                    .withMessage("Could not load property file: %s", configFile)
                    .log();
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Getter
    //----------------------------------------------------------------------------------------------------------------------

    /**
     * <h2>get(String)</h2>
     * <p>Returns the value of the given key as string</p>
     *
     * @param key the key to get the value for
     * @return the value of the key as string
     */
    public String get(final String key) {
        return get(key, "");
    }

    /**
     * <h2>get(String, String)</h2>
     * <p>Returns the value of the given key as string</p>
     * <p>If the key does not exist, the default value is returned</p>
     *
     * @param key          the key to get the value for
     * @param defaultValue the default value to return if the key does not exist
     * @return the value of the key as string
     */
    public String get(final String key, String defaultValue) {
        if (!properties.containsKey(key)) {
            properties.setProperty(key, defaultValue);
        }
        return properties.getProperty(key, defaultValue);
    }

    /**
     * <h2>getBoolean(String)</h2>
     * <p>Returns the value of the given key as boolean</p>
     *
     * @param key the key to get the value for
     * @return the value of the key as boolean
     */
    public boolean getBoolean(final String key) {
        return Boolean.parseBoolean(get(key, "false"));
    }

    /**
     * <h2>getBoolean(String, boolean)</h2>
     * <p>Returns the value of the given key as boolean</p>
     * <p>If the key does not exist, the default value is returned</p>
     *
     * @param key          the key to get the value for
     * @param defaultValue the default value to return if the key does not exist
     * @return the value of the key as boolean
     */
    public boolean getBoolean(final String key, boolean defaultValue) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
    }

    /**
     * <h2>getInt(String)</h2>
     * <p>Returns the value of the given key as int</p>
     *
     * @param key the key to get the value for
     * @return the value of the key as int
     */
    public int getInt(final String key) {
        return Integer.parseInt(get(key, "0"));
    }

    /**
     * <h2>getInt(String, int)</h2>
     * <p>Returns the value of the given key as int</p>
     * <p>If the key does not exist, the default value is returned</p>
     *
     * @param key          the key to get the value for
     * @param defaultValue the default value to return if the key does not exist
     * @return the value of the key as int
     */
    public int getInt(final String key, int defaultValue) {
        return Integer.parseInt(get(key, String.valueOf(defaultValue)));
    }

    /**
     * <h2>getLong(String)</h2>
     * <p>Returns the value of the given key as long</p>
     *
     * @param key the key to get the value for
     * @return the value of the key as long
     */
    public long getLong(final String key) {
        return Long.parseLong(get(key, "0"));
    }

    /**
     * <h2>getLong(String, long)</h2>
     * <p>Returns the value of the given key as long</p>
     * <p>If the key does not exist, the default value is returned</p>
     *
     * @param key          the key to get the value for
     * @param defaultValue the default value to return if the key does not exist
     * @return the value of the key as long
     */
    public long getLong(final String key, long defaultValue) {
        return Long.parseLong(get(key, String.valueOf(defaultValue)));
    }

    /**
     * <h2>getDouble(String)</h2>
     * <p>Returns the value of the given key as double</p>
     *
     * @param key the key to get the value for
     * @return the value of the key as double
     */
    public double getDouble(final String key) {
        return Double.parseDouble(get(key, "0.0"));
    }

    /**
     * <h2>getDouble(String, double)</h2>
     * <p>Returns the value of the given key as double</p>
     * <p>If the key does not exist, the default value is returned</p>
     *
     * @param key          the key to get the value for
     * @param defaultValue the default value to return if the key does not exist
     * @return the value of the key as double
     */
    public double getDouble(final String key, double defaultValue) {
        return Double.parseDouble(get(key, String.valueOf(defaultValue)));
    }

    /**
     * <h2>getAllProperties()</h2>
     * <p>Returns a list of all keys in the property file</p>
     *
     * @return a list of all keys in the property file
     */
    public List<String> getAllProperties() {
        return properties.keySet().stream().map(Object::toString).toList();
    }

    /**
     * <h2>set(String, String)</h2>
     * <p>Sets the value of the given key to the given value</p>
     * <p>The property file will be saved</p>
     *
     * @param k     the key to set the value for
     * @param value the value to set
     */
    public void set(final String k, final String value) {
        properties.setProperty(k, value);
        store();
    }

    /**
     * <h2>set(String, boolean)</h2>
     * <p>Sets the value of the given key to the given value</p>
     * <p>The property file will be saved</p>
     *
     * @param k     the key to set the value for
     * @param value the value to set
     */
    public void set(final String k, final boolean value) {
        set(k, String.valueOf(value));
    }

    /**
     * <h2>set(String, int)</h2>
     * <p>Sets the value of the given key to the given value</p>
     * <p>The property file will be saved</p>
     *
     * @param k     the key to set the value for
     * @param value the value to set
     */
    public void set(final String k, final int value) {
        set(k, String.valueOf(value));
    }

    /**
     * <h2>set(String, long)</h2>
     * <p>Sets the value of the given key to the given value</p>
     * <p>The property file will be saved</p>
     *
     * @param k     the key to set the value for
     * @param value the value to set
     */
    public void set(final String k, final long value) {
        set(k, String.valueOf(value));
    }

    /**
     * <h2>set(String, double)</h2>
     * <p>Sets the value of the given key to the given value</p>
     * <p>The property file will be saved</p>
     *
     * @param k     the key to set the value for
     * @param value the value to set
     */
    public void set(final String k, final double value) {
        set(k, String.valueOf(value));
    }

    /**
     * <h2>remove(String)</h2>
     * <p>Removes the given key from the property file</p>
     * <p>The property file will be saved</p>
     *
     * @param k the key to remove
     */
    public void remove(final String k) {
        properties.remove(k);
        store();
    }

    /**
     * <h2>store()</h2>
     * <p>Stores the property file</p>
     * <p>If an error occurs, an error message is logged</p>
     */
    public void store() {
        FileUtils.createFileIfNotExists(configFile);
        try (final OutputStream os = new FileOutputStream(configFile)) {
            properties.store(os, "Flushed properties");
        } catch (IOException e) {
            LOGGER.atError().forEnclosingMethod().withException(e).withMessage("Could not save property file: %s", name).log();
        }
    }
}
