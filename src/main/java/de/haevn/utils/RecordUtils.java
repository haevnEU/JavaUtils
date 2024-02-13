package de.haevn.utils;

import java.util.function.Function;

/**
 * A simple class for record operation.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class RecordUtils {
    private RecordUtils() {

    }

    /**
     * Converts the record into another record/class.
     * It will call the {@link Function#apply} method with the given value.
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
