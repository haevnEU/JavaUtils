package de.haevn.utils;


import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A simple class for System information.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class System {

    private System() {
    }
    
    public static String[] getKeyValue(final String key, final String value) {
        return new String[]{key, value};
    }

    public static String[] property(final String key, final String value) {
        return getKeyValue(key, value);
    }


}
