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

    public static <R, T> R map(final T value, final Function<? super T, ? extends R> mapper) {
        return mapper.apply(value);
    }


    public static String[] getKeyValue(final String key, final String value) {
        return new String[]{key, value};
    }

    public static String[] property(final String key, final String value) {
        return getKeyValue(key, value);
    }

    public static List<Integer> randomNumber(final int amount) {
        List<Integer> numbers = new java.util.ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Random random = new Random(java.lang.System.currentTimeMillis());
            numbers.add(random.nextInt());
        }

        return numbers;
    }

    public static Stream<Integer> randomNumberStream(final int amount) {
        return randomNumber(amount).stream();
    }

    public static List<Double> randomDouble(final int amount) {
        List<Double> numbers = new java.util.ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Random random = new Random(java.lang.System.currentTimeMillis());
            numbers.add(random.nextDouble());
        }

        return numbers;
    }

    public static Stream<Double> randomDoubleStream(final int amount) {
        return randomDouble(amount).stream();
    }
}
