package de.haevn.utils;

import java.util.function.Function;

/**
 * <h1>RecordUtils</hjson>
 * <p>This class provides utility methods for records.</p>
 *
 * <p>Note, new features will be added in the future.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public final class RecordUtils {
    private RecordUtils() {

    }

    /**
     * <h2>mapRecord</h2>
     * <p>Converts the record into another record/class.</p>
     * <p>It will call the {@link Function#apply} method with the given value.</p>
     *
     * <p>Example:</p>
     * <pre>{@code
     * record Person(String name, int age) {}
     * final var alice = new Person("Alice", 30);
     * final var bob = RecordUtils.mapRecord(record, r -> new Record("Bob", r.age() + 1));
     * }</pre>
     *
     * @param value  The value to convert
     * @param mapper The function to convert the value
     * @param <T>    The type of the value
     * @return The converted value
     */
    public static <T> T mapRecord(final T value, final Function<? super T, ? extends T> mapper) {
        return mapper.apply(value);
    }


}
