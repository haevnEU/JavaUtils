package de.haevn.utils.annotations;

import de.haevn.utils.Core;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class AnnotationUtils {

    private AnnotationUtils() {
    }

    public static List<Class<?>> collectBy(final String packageName, Class<? extends Annotation> annotation) {
        return getClasses(packageName).stream().filter(clazz -> clazz.isAnnotationPresent(annotation)).toList();
    }


    private static List<Class<?>> getClasses(final String packageName) {
        final List<Class<?>> classes = new ArrayList<>();
        final String path = packageName.replace('.', '/');

        final URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        if (null == resource) return classes;

        final File directory = new File(resource.getFile());
        if (!directory.exists()) return classes;

        final File[] files = directory.listFiles();
        if (null == files) return classes;


        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(getClasses(packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                final String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException ignored) {}
            }
        }


        return classes;
    }
}
