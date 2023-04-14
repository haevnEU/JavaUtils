package de.haevn.utils.io;

import de.haevn.utils.logging.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public final class PropertyHandler {
    private static final Logger LOGGER = new Logger(PropertyHandler.class);
    private static final String EXTENSION = ".property";
    private static final Map<String, PropertyHandler> STRING_PROPERTY_HANDLER_HASH_MAP = new HashMap<>();
    private final Properties properties;
    private final String name;

    private final File configFile;

    private PropertyHandler(final String propertyName) {
        properties = new Properties();
        this.name = propertyName;

        configFile = new File(FileUtils.getRootPathWithSeparator() + "config", name + EXTENSION);
        load();
    }

    public static PropertyHandler getInstance(final String name) {
        if (STRING_PROPERTY_HANDLER_HASH_MAP.containsKey(name.toUpperCase())) {
            return STRING_PROPERTY_HANDLER_HASH_MAP.get(name.toUpperCase());
        }

        final PropertyHandler handler = new PropertyHandler(name);
        STRING_PROPERTY_HANDLER_HASH_MAP.put(name.toUpperCase(), handler);
        return handler;
    }

    public static List<String> getAllHandler() {
        return STRING_PROPERTY_HANDLER_HASH_MAP.keySet().stream().toList();
    }

    public static void reloadAll() {
        STRING_PROPERTY_HANDLER_HASH_MAP.values().forEach(PropertyHandler::reload);
    }

    public void reload() {
        load();
    }

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

    public String get(final String key) {
        return get(key, "");
    }

    public String get(final String key, String defaultValue) {
        if (!properties.containsKey(key)) {
            properties.setProperty(key, defaultValue);
        }
        return properties.getProperty(key, defaultValue);
    }

    public boolean getBoolean(final String key) {
        return Boolean.parseBoolean(get(key, "false"));
    }

    public boolean getBoolean(final String key, boolean defaultValue) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
    }

    public int getInt(final String key) {
        return Integer.parseInt(get(key, "0"));
    }

    public int getInt(final String key, int defaultValue) {
        return Integer.parseInt(get(key, String.valueOf(defaultValue)));
    }

    public long getLong(final String key) {
        return Long.parseLong(get(key, "0"));
    }

    public long getLong(final String key, long defaultValue) {
        return Long.parseLong(get(key, String.valueOf(defaultValue)));
    }

    public double getDouble(final String key) {
        return Double.parseDouble(get(key, "0.0"));
    }

    public double getDouble(final String key, double defaultValue) {
        return Double.parseDouble(get(key, String.valueOf(defaultValue)));
    }

    public List<String> getAllProperties() {
        return properties.keySet().stream().map(Object::toString).toList();
    }


    public void set(final String k, final String value) {
        properties.setProperty(k, value);
        try (final OutputStream os = new FileOutputStream(configFile)) {
            properties.store(os, "Updated " + k + " to " + value);
        } catch (IOException e) {
            LOGGER.atError().forEnclosingMethod()
                    .withException(e)
                    .withMessage("Could not save property file: %s", name).log();
        }
    }

    public void store() {
        FileUtils.createFileIfNotExists(configFile);
        try (final OutputStream os = new FileOutputStream(configFile)) {
            properties.store(os, "Flushed properties");
        } catch (IOException e) {
            LOGGER.atError().forEnclosingMethod().withException(e).withMessage("Could not save property file: %s", name).log();
        }
    }
}
