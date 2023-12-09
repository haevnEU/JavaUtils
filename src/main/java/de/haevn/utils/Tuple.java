package de.haevn.utils;

/**
 * A simple tuple class.
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class Tuple<K, V> {

    private K key;
    private V value;

    /**
     * Creates a new tuple.
     *
     * @param key   The first element.
     * @param value The second element.
     */
    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the first element.
     *
     * @return The first element.
     */
    public K getFirst() {
        return key;
    }

    /**
     * Sets the first element.
     *
     * @param key The new first element.
     */
    public void setFirst(K key) {
        this.key = key;
    }

    /**
     * Returns the second element.
     *
     * @return The second element.
     */
    public V getSecond() {
        return value;
    }

    /**
     * Sets the second element.
     *
     * @param value The new second element.
     */
    public void setSecond(V value) {
        this.value = value;
    }
}
