package de.haevn.utils;

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
public class Tripple<K, V, T> {
    private K first;
    private V second;
    private T third;

    /**
     * Creates a new tuple.
     *
     * @param first  The first element.
     * @param second The second element.
     * @param third  The third element.
     */
    public Tripple(K first, V second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Returns the first element.
     *
     * @return The first element.
     */
    public K getFirst() {
        return first;
    }

    /**
     * Sets the first element.
     *
     * @param value The new first element.
     */
    public void setFirst(K value) {
        this.first = value;
    }

    /**
     * Returns the second element.
     *
     * @return The second element.
     */
    public V getSecond() {
        return second;
    }

    /**
     * Sets the second element.
     *
     * @param value The new second element.
     */
    public void setSecond(V value) {
        this.second = value;
    }

    /**
     * Returns the third element.
     *
     * @return The third element.
     */
    public T getThird() {
        return third;
    }

    /**
     * Sets the third element.
     *
     * @param value The new third element.
     */
    public void setThird(T value) {
        this.third = value;
    }
}
