package de.haevn.utils.datastructure;

/**
 * A simple tuple class.
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class ReadonlyTuple<K, V> extends Tuple<K, V> {

    /**
     * Creates a new tuple.
     *
     * @param key   The first element.
     * @param value The second element.
     */
    public ReadonlyTuple(final K key, final V value) {
        super(key, value);
    }

    /**
     * Does nothing.
     *
     * @param key ignored
     */
    @Override
    public void setFirst(final K key) {
        // This method ensures read-only access
    }

    /**
     * Does nothing.
     *
     * @param value ignored
     */
    @Override
    public void setSecond(final V value) {
        // This method ensures read-only access
    }
}
