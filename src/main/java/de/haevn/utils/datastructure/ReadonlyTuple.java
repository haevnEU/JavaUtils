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
    public ReadonlyTuple(K key, V value) {
        super(key, value);
    }

    /**
     * Does nothing.
     *
     * @param key ignored
     */
    public void setFirst(K key) {
    }

    /**
     * Does nothing.
     *
     * @param value ignored
     */
    public void setSecond(V value) {
    }
}
