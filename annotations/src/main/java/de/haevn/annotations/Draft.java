package de.haevn.annotations;

/**
 * <h1>Draft</h1>
 * <p>This annotation can be used to mark a class, method or field as draft.</p>
 * <p>A draft is a piece of code that is not yet finished and should not be used in production.</p>
 * <p>It provides the following attributes:</p>
 * <ul>
 *     <li>{@link Draft#description()} </li>
 *     <li>{@link Draft#todo()}</li>
 * </ul>
 * <br>
 * <h3>Example:</h3>
 * <pre>
 *     {@code
 *     @Draft(description = "This class is not yet finished")
 *     public class Example {}
 *     }
 * </pre>
 * @version 1.0
 * @since 2.1
 * @author haevn
 */
public @interface Draft {
    /**
     * <h2>description</h2>
     * <p>A description of the draft.</p>
     * @return The description of the draft.
     */
    String description() default "";

    /**
     * <h2>todo</h2>
     * <p>A list of things that need to be done.</p>
     * @return A list of things that need to be done.
     */
    String[] todo() default {};
}
