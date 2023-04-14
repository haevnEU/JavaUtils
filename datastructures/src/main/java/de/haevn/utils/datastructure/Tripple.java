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
public class Tripple<K, V, T> extends Tuple<K, V>{
    private T third;

    /**
     * Creates a new tuple.
     *
     * @param first  The first element.
     * @param second The second element.
     * @param third  The third element.
     */
    public Tripple(final K first, final V second, final T third) {
        super(first, second);
        this.third = third;
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
    public void setThird(final T value) {
        this.third = value;
    }
}
