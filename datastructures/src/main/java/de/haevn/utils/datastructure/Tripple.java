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
    public static final Tripple<?, ?, ?> EMPTY = new Tripple<>(null, null, null);
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

    @Override
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
        Tripple<?, ?, ?> tripple = (Tripple<?, ?, ?>) obj;
        return getFirst().equals(tripple.getFirst()) && getSecond().equals(tripple.getSecond()) && third.equals(tripple.third);
    }
}
