package de.haevn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Launcher {
    String name();

    String version() default "alpha 1.0";

    String author() default "Unknown";

    String root() default "./";

    String icon() default "";

    String description() default "";

    String license() default "MIT";

    String website() default "";

}
