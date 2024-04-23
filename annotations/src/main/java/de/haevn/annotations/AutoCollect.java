package de.haevn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * Auto collect annotation.
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoCollect {
    /**
     * The order of the collected class.
     * It can be used to compare with other annotated classes, e.g. when this value is higher it means it's a higher prioritized class.
     * example
     * <pre>
     * AnnotationUtils.collectBy("de.haevn.utils", AutoCollect.class)
     *      .stream()
     *      .sorted(Comparator.comparingInt(clazz -> clazz.getAnnotation(AutoCollect.class).order()))
     *      .toList();
     * </pre>
     * @return The order.
     */
    int order() default 10;

    FeatureType feature() default FeatureType.ENABLED;

    enum FeatureType {

        ENABLED("enabled"),
        DISABLED("disabled"),
        HIDDEN("hidden"),
        PREVIEW("preview"),
        EXPERIMENTAL("experimental"),
        DEPRECATED("deprecated"),
        UNKNOWN("unknown");
        private final String name;

        FeatureType(final String name) {
            this.name = name;
        }

        public boolean has(final FeatureType... features) {
            return Arrays.stream(features).anyMatch(feature -> feature == this);
        }

        public static FeatureType fromString(final String name) {
            return Arrays.stream(FeatureType.values()).filter(featureType -> featureType.name.equalsIgnoreCase(name)).findFirst().orElse(UNKNOWN);
        }

    }
}
