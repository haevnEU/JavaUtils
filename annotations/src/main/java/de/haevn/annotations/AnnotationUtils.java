package de.haevn.annotations;

import de.haevn.utils.enumeration.FeatureType;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Utility class for annotations.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class AnnotationUtils {

    private AnnotationUtils() {
    }

    /**
     * Collects all classes inside the given package which are annotated with the specified annotation.
     *
     * @param packageName The package name.
     * @return A list of classes.
     */
    public static List<Class<?>> collectBy(final String packageName, final Class<? extends Annotation> annotation) {
        return getClasses(packageName).stream().filter(clazz -> clazz.isAnnotationPresent(annotation)).toList();
    }

    /**
     * Collects all classes inside the given package which are annotated with the specified annotation and feature type.
     *
     * @param packageName The package name.
     * @param annotation The annotation.
     * @param FeatureType The feature type.
     * @return A list of classes.
     */
    public static List<Class<?>> collectBy(final String packageName, final Class<? extends Annotation> annotation, final FeatureType ... features) {
        return getClasses(packageName).stream()
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .filter(clazz -> isFeaturePresent(clazz, features))
                .toList();
    }

    private static boolean isFeaturePresent(final Class<?> annotation, final FeatureType ... features) {
        return annotation.getAnnotation(AutoCollect.class).feature().has(features);
    }

    /**
     * Collects all classes in the given package.
     * <ul>
     *     <b>Preconditions</b>:
     *     <li>packageName is not null</li>
     *     <li>packageName is not empty</li>
     *     <li>packageName is not blank</li>
     *
     *     <b>Actions</b>:
     *     <li>Iterate over all files</li>
     *     <li>Check if file is a directory, if yes make a recursive call to this method</li>
     *     <li>Otherwise check if its a class file and execute following steps</li>
     *     <li>Get the class name and try to find it</li>
     *     <li>If the class is found add it to the list</li>
     * </ul>
     * @param packageName The package name.
     * @return A list of classes.
     */
    private static List<Class<?>> getClasses(final String packageName) {
        final List<Class<?>> classes = new ArrayList<>();
        final String path = packageName.replace('.', '/');

        final URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        if (null == resource) return classes;

        final File directory = new File(resource.getFile());
        if (!directory.exists()) return classes;

        final File[] files = directory.listFiles();
        if (null == files) return classes;

        Arrays.stream(files).forEach(file -> {
            if (file.isDirectory()) classes.addAll(getClasses(packageName + "." + file.getName()));
            else if (file.getName().endsWith(".class")) {
                final String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException ignored) { }
            }
        });
        return classes;
    }
}
