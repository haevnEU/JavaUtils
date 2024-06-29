package de.haevn.utils.datastructure;

/**
 * <h1>Tuple</h1>
 * <br>
 * <p>This class provides a simple tuple class that can be used to store two elements.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final Tuple<String, Integer> tuple = new Tuple<>("Hello", 42);
 *     System.out.println(tuple.getFirst());
 *     System.out.println(tuple.getSecond());
 *     tuple.setFirst("World");
 *     tuple.setSecond(24);
 * }
 * </pre>
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class Tuple<K, V> {
    /**
     * An empty Tuple.
     */
    public static final Tuple<?, ?> EMPTY = new Tuple<>(null, null);
    /**
     * The first element.
     */
    private K key;

    /**
     * The second element.
     */
    private V value;

    /**
     * <h2>Tuple(K, V)</h2>
     * <p>Creates a new tuple with the given elements.</p>
     *
     * @param key   The first element.
     * @param value The second element.
     */
    public Tuple(final K key,final V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * <h2>getFirst()</h2>
     * <p>Returns the first element.</p>
     * @return The first element.
     */
    public K getFirst() {
        return key;
    }

    /**
     * <h2>setFirst(K)</h2>
     * <p>Sets the first element.</p>
     * @param key The new first element.
     */
    public void setFirst(final K key) {
        this.key = key;
    }

    /**
     * <h2>getSecond()</h2>
     * <p>Returns the second element.</p>
     * @return The second element.
     */
    public V getSecond() {
        return value;
    }

    /**
     * <h2>setSecond(V)</h2>
     * <p>Sets the second element.</p>
     * @param value The new second element.
     */
    public void setSecond(final V value) {
        this.value = value;
    }

    /**
     * <h2>isEmpty()</h2>
     * <p>Checks if the tuple is empty.</p>
     * @return true iff the tuple is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    /**
     * <h2>equals(Object)</h2>
     * <p>Compares this tuple to another object.</p>
     <p>It does the following checks</p>
     * <ul>
     *     <li>Check if the object is the same as this tripple</li>
     *     <li>Check if the object is null or not an instance of Tripple</li>
     *     <li>Check if the first, second and third elements are equal</li>
     *     <li>Return true if all checks pass, false otherwise</li>
     * </ul>
     * @param obj The object to compare to.
     * @return true iff the objects are equal, false otherwise
     */
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
