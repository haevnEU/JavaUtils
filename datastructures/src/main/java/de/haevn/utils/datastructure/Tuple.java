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
public class Tuple<K, V> {
    public static final Tuple<?, ?> EMPTY = new Tuple<>(null, null);
    private K key;
    private V value;

    /**
     * Creates a new tuple.
     *
     * @param key   The first element.
     * @param value The second element.
     */
    public Tuple(final K key,final V value) {
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
    public void setFirst(final K key) {
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
    public void setSecond(final V value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tuple<?, ?> tuple = (Tuple<?, ?>) obj;
        return key.equals(tuple.key) && value.equals(tuple.value);
    }
}
