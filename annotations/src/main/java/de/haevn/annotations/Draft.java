package de.haevn.annotations;

/**
 * This annotation can be used to mark a class, method or field as draft.<br>
 * A draft is a piece of code that is not yet finished and should not be used in production.<br>
 * <ul>
 *     <li>{@link Draft#description()} should contain information about the current state of the draft and what needs to be done</li>
 *     <li>{@link Draft#todo()} should contain a list of tasks that need to be done before the draft can be used in production</li>
 * </ul>
 */
public @interface Draft {
    String description() default "";
    String[] todo() default {};
}
