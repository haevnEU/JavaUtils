package de.haevn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * <h1>AutoCollect</h1>
 * <p>This annotation is used to mark classes that should be collected by the {@link de.haevn.annotations.AnnotationUtils}</p>
 * <p>It provides the following attributes:</p>
 * <ul>
 *     <li>order</li>
 *     <li>feature</li>
 * </ul>
 * <br>
 * <p>It also provides a nested enum {@link FeatureType} to define the feature of the class.</p>
 * <p>This class is finalized but new features can be added.</p>
 * <br>
 * <h3>Example:</h3>
 * <pre>
 * {@code
 * @AutoCollect(feature = AutoCollect.FeatureType.ENABLED)
 * public class Example {}
 * }
 * </pre>
 * @version 1.0
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoCollect {

    /**
     * <h2>order</h2>
     * <p>The order of the collected class.</p>
     * <p>Classes with a lower order will be collected first.</p>
     * <br>
     * <p>Default value: 10</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * AnnotationUtils.collectBy("de.haevn.utils", AutoCollect.class)
     *      .stream()
     *      .sorted(Comparator.comparingInt(clazz -> clazz.getAnnotation(AutoCollect.class).order()))
     *      .toList();
     * }
     * </pre>
     * @return The order.
     */
    int order() default 10;

    /**
     * <h2>feature</h2>
     * <p>The feature of the collected class.</p>
     * <p>Classes with a feature of {@link FeatureType#ENABLED} will be collected.</p>
     * <br>
     * <p>Default value: {@link FeatureType#ENABLED}</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * @AutoCollect(feature = AutoCollect.FeatureType.ENABLED)
     * public class Example {}
     * }
     * </pre>
     * @return The feature.
     */
    FeatureType feature() default FeatureType.ENABLED;

    /**
     * <h1>FeatureType</h1>
     * <p>Enum to define the feature of a class</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * @AutoCollect(feature = AutoCollect.FeatureType.ENABLED)
     * public class Example {}
     * }
     * </pre>
     */
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

        /**
         * <h2>has</h2>
         * <p>Check if the given {@link FeatureType} is present in the current {@link FeatureType}</p>
         * <br>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         * @AutoCollect(feature = AutoCollect.FeatureType.ENABLED)
         * public class Example {}
         * FeatureType.ENABLED.has(FeatureType.ENABLED); // Returns true
         * FeatureType.ENABLED.has(FeatureType.DISABLED); // Returns false
         * }
         *
         * @param features The features to check for
         * @return True iff all features are present otherwise false
         */
        public boolean has(final FeatureType... features) {
            return Arrays.stream(features).anyMatch(feature -> feature == this);
        }

        /**
         * <h2>fromString</h2>
         * <p>Get the {@link FeatureType} from a string</p>
         * <br>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         * FeatureType.fromString("enabled"); // Returns FeatureType.ENABLED
         * FeatureType.fromString("disabled"); // Returns FeatureType.DISABLED
         * }
         *
         * @param name The name of the feature
         * @return The associated {@link FeatureType}
         */
        public static FeatureType fromString(final String name) {
            return Arrays.stream(FeatureType.values()).filter(featureType -> featureType.name.equalsIgnoreCase(name)).findFirst().orElse(UNKNOWN);
        }
    }
}
