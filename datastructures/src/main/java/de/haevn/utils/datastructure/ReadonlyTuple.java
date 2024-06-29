package de.haevn.utils.datastructure;

/**
 * <h1>ReadonlyTuple</h1>
 * <br>
 * <p>This class provides a read-only version of the {@link Tuple} class.</p>
 * <p>It overrides the setter methods to ensure read-only access.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final ReadonlyTuple<String, Integer> tuple = new ReadonlyTuple<>("Hello", 42);
 *     System.out.println(tuple.getFirst());
 *     System.out.println(tuple.getSecond());
 * }
 * </pre>
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class ReadonlyTuple<K, V> extends Tuple<K, V> {

    /**
     * <h2>ReadonlyTuple(K, V)</h2>
     * <p>Creates a new read-only tuple with the given elements.</p>
     *
     * @param key   The key.
     * @param value The value.
     */
    public ReadonlyTuple(final K key, final V value) {
        super(key, value);
    }

    /**
     * <h2>setFirst(K)</h2>
     * <p>Does nothing</p>
     * @param key ignored
     */
    @Override
    public void setFirst(final K key) {
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
}
