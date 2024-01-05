package de.haevn.utils.enumeration;

import java.util.Arrays;

public enum FeatureType {

    ENABLED("enabled"),
    DISABLED("disabled"),
    HIDDEN("hidden"),
    PREVIEW("preview"),
    EXPERIMENTAL("experimental"),
    DEPRECATED("deprecated");

    private final String name;
    FeatureType(final String name) {
        this.name = name;
    }

    public boolean has(final FeatureType ... features) {
        return Arrays.stream(features).anyMatch(feature -> feature == this);
    }
}
