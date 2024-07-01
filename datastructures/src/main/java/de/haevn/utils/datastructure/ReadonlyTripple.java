package de.haevn.utils.datastructure;

/**
 * <h1>ReadonlyTripple</h1>
 * <br>
 * <p>This class provides a read-only version of the {@link Tripple} class.</p>
 * <p>It overrides the setter methods to ensure read-only access.</p>
 *
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final ReadonlyTripple<String, Integer, Boolean> tripple = new ReadonlyTripple<>("Hello", 42, true);
 *     System.out.println(tripple.getFirst());
 *     System.out.println(tripple.getSecond());
 *     System.out.println(tripple.getThird());
 * }
 * </pre>
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @param <T> The type of the third element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class ReadonlyTripple<K, V, T> extends Tripple<K, V, T> {

    /**
     * <h2>ReadonlyTripple(K, V, T)</h2>
     * <p>Creates a new read-only tripple with the given elements.</p>
     * @param first  The first element.
     * @param second The second element.
     * @param third  The third element.
     */
    public ReadonlyTripple(final K first, final V second, final T third) {
        super(first, second, third);
    }

    /**
     * <h2>setFirst(K)</h2>
     * <p>Does nothing</p>
     * @param value ignored
     */
    @Override
    public void setFirst(final K value) {
        // This method ensures read-only access
    }

    /**
     * <h2>setSecond(V)</h2>
     * <p>Does nothing</p>
     * @param value ignored
     */
    @Override
    public void setSecond(final V value) {
        // This method ensures read-only access
    }

    /**
     * <h2>setThird(T)</h2>
     * <p>Does nothing</p>
     * @param value ignored
     */
    @Override
    public void setThird(final T value) {
        // This method ensures read-only access
    }
}
