package de.haevn.utils.datastructure;

/**
 * A simple Tripple class.
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @param <T> The type of the third element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class ReadonlyTripple<K, V, T> extends Tripple<K, V, T> {

    /**
     * Creates a new tuple.
     *
     * @param first  The first element.
     * @param second The second element.
     * @param third  The third element.
     */
    public ReadonlyTripple(K first, V second, T third) {
        super(first, second, third);
    }

    /**
     * Does nothing
     *
     * @param value ignored
     */
    @Override
    public void setFirst(K value) {
        // This method ensures read-only access
    }

    /**
     * Does nothing
     *
     * @param value ignored
     */
    @Override
    public void setSecond(V value) {
        // This method ensures read-only access
    }

    /**
     * Does nothing
     *
     * @param value ignored
     */
    @Override
    public void setThird(T value) {
        // This method ensures read-only access
    }
}
