package de.haevn.annotations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for annotations.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class AnnotationUtils {

    private AnnotationUtils() {
    }

    private static boolean isFeaturePresent(final Class<?> annotation, final AutoCollect.FeatureType... features) {
        return annotation.getAnnotation(AutoCollect.class).feature().has(features);
    }

    public static List<Launcher> findLauncher(final String packageName) {
        return findAnnotation(packageName, Launcher.class).stream()
                .map(Launcher.class::cast)
                .toList();
    }

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

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException ignored) {
        }
        return null;
    }


}