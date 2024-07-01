package de.haevn.utils.debug;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <h1>DebugTool</h1>
 * <br>
 * <p>This annotation can be used to mark classes that provide debugging tools.</p>
 * <p>It provides information about the name, description and authors of the tool.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     @DebugTool(name = "MyTool", description = "This is a simple tool", authors = {"haevn"})
 *     public class MyTool {...}
 * }
 * </pre>
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DebugTool {
    String name();

    String description() default "";

    String[] authors() default {};
}
