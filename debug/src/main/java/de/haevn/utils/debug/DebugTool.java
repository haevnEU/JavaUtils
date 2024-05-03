package de.haevn.utils.debug;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
public @interface DebugTool {
    String name();
    String description() default "";
    String[] authors() default {};
}