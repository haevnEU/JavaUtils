package de.haevn.utils.debug;


import de.haevn.annotations.AnnotationUtils;

import java.util.ArrayList;
import java.util.List;

public final class DebugToolCollection {
    private final List<Class<?>> debugTools = new ArrayList<>();

    public DebugToolCollection(final String rootPackage) {
        debugTools.addAll(AnnotationUtils.collectBy(rootPackage, DebugTool.class));
    }

    public List<Class<?>> getDebugTools() {
        return debugTools;
    }

    public List<String> getDebugToolNames() {
        return debugTools.stream().map(clazz -> clazz.getAnnotation(DebugTool.class).name()).toList();
    }

    public List<Class<?>> getDebugTools(final String name) {
        return debugTools.stream().filter(clazz -> clazz.getAnnotation(DebugTool.class).name().equals(name)).toList();
    }
}
