package de.haevn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>Launcher</h1>
 * <p>This annotation is used to mark a class a launcher, the launcher contains information about the project.</p>
 * <p>The {@link LauncherUtils} class should be used to access these information.</p>
 * <p>Typically a launcher is the main class of a project.</p>
 * <p>This class is finalized however new features or fields can be added</p>
 * <br>
 * <h3>Attributes:</h3>
 * <ul>
 *     <li>name</li>
 *     <li>version</li>
 *     <li>author</li>
 *     <li>root</li>
 *     <li>icon</li>
 *     <li>description</li>
 *     <li>license</li>
 *     <li>website</li>
 * </ul>
 * <br>
 * <h3>Example:</h3>
 * <pre>
 *     {@code
 *     @Launcher("Example")
 *     public class Example {}
 *     }
 *
 *     {@code
 *     @Launcher(name = "Example", website = "https://example.com")
 *     public class ExampleTwo {}
 *     }
 *</pre>
 * @version 1.0
 * @since 2.1
 * @author haevn
 */
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
