package de.haevn.annotations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h1>AnnotationUtils</h1>
 * <p>This class provides utilities to handle {@link Annotation}</p>
 * <p>Currently implemented utilites are</p>
 * <ul>
 *     <li>Testing if given {@link AutoCollect.FeatureType FeatureType} is present</li>
 *     <li>Searching for a specific {@link Annotation}</li>
 *     <li>Quick access to retrieve a {@link Launcher}</li>
 * </ul>
 * <p>New features will be added continously.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class AnnotationUtils {

    private AnnotationUtils() {
    }

    /**
     * <h2>isFeaturePresent</h2>
     * <p>Check if given {@link AutoCollect.FeatureType FeatureType} is present in the {@link AutoCollect} annotation</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * @AutoCollect(feature = AutoCollect.FeatureType.ENABLED)
     * public class Example {}
     * isFeaturePresent(Example.class, AutoCollect.FeatureType.ENABLED); // Returns true
     * isFeaturePresent(Example.class, AutoCollect.FeatureType.DISABLED); // Returns false
     * }
     * </pre>
     *
     * @param annotation The annotation to check
     * @param features   The features to check for
     * @return True if all features are present, false otherwise
     */
    public static boolean isFeaturePresent(final Class<?> annotation, final AutoCollect.FeatureType... features) {
        return annotation.getAnnotation(AutoCollect.class).feature().has(features);
    }

    /**
     * <h2>findLauncher</h2>
     * <p>Searches a package for all {@link Launcher} annotated classes.</p>
     * <br>
     * <p>Internally the {@link AnnotationUtils#findAnnotation(String, Class) findAnnotation(String, Class)} is called
     * with {@link Launcher} as argument</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     @Launcher
     *     public class Example {}
     *     findLauncher("de.haevn.annotations"); // Returns a list with Example.class
     * }
     * </pre>
     *
     * @param packageName The package to search in
     * @return A list of all found {@link Launcher}
     */
    public static List<Launcher> findLauncher(final String packageName) {
        return findAnnotation(packageName, Launcher.class).stream()
                .map(Launcher.class::cast)
                .toList();
    }

    /**
     * <h2>findAnnotation</h2>
     * <p>Searches a package for all classes annotated with a specific {@link Annotation}</p>
     * <br>
     * <ul>
     *
     * <li>First it request an inputstream from the {@link  ClassLoader#getSystemClassLoader() SystemClassLoader}</li>
     * <li>If the {@link InputStream} is null the operation yields an empty list</li>
     * <li>Then every file is read and filtered for files ending with <i>.class</i></li>
     * <li>Next the class is requested using the {@link AnnotationUtils#getClass(String, String) getClass(String, String)} method</li>
     * <li>After the conversion all null elements are discarded</li>
     * <li>Elements not annotated with the given annotation are also discarded</li>
     * <li>Finally the annotation is casted to the given annotation type and collected in a list</li>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     @AutoCollect
     *     public class Example {}
     *     findAnnotation("de.haevn.annotations", AutoCollect.class); // Returns a list with Example.class
     *     }
     * </pre>
     *
     * @param packageName The package to search in
     * @param annotation  The annotation to search for
     * @return A list of all found {@link Annotation}
     */
    public static List<? extends Annotation> findAnnotation(final String packageName, final Class<? extends Annotation> annotation) {
        final InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        if (stream == null) return new ArrayList<>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .filter(Objects::nonNull)
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .map(clazz -> clazz.getAnnotation(annotation))
                .toList();
    }

    /**
     * <h2>getClass</h2>
     * <p><b>THIS IS AN INTERNAL METHOD</b></p>
     * <p>This method wraps the {@link Class#forName(String)}, it will return a null instead of throwing an {@link ClassNotFoundException}</p>
     * <br>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     getClass("Example.class", "de.haevn); // Returns Example.class
     *     }
     * </pre>
     *
     * @param className   The name of the class
     * @param packageName The package name
     * @return The class or null if not found
     */
    private static Class<?> getClass(final String className, final String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException ignored) {
        }
        return null;
    }


}