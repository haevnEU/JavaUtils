package de.haevn.utils.datastructure;

/**
 * <h1>Tripple</h1>
 * <br>
 * <p>This class provides a simple tripple class that can be used to store three elements.</p>
 * <p>It extends the {@link Tuple} class.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final Tripple<String, Integer, Boolean> tripple = new Tripple<>("Hello", 42, true);
 *     System.out.println(tripple.getFirst());
 *     System.out.println(tripple.getSecond());
 *     System.out.println(tripple.getThird());
 *     tripple.setFirst("World");
 *     tripple.setSecond(24);
 *     tripple.setThird(false);
 * }
 * </pre>
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @param <T> The type of the third element.
 * @author Haevn
 * @version 1.0
 * @since 1.0
 */
public class Tripple<K, V, T> extends Tuple<K, V>{

    /**
     * An empty Tripple.
     */
    public static final Tripple<?, ?, ?> EMPTY = new Tripple<>(null, null, null);

    /**
     * The third element.
     */
    private T third;

    /**
     * <h2>Tripple(K, V, T)</h2>
     * <p>Creates a new tripple with the given elements.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Tripple<String, Integer, Boolean> tripple = new Tripple<>("Hello", 42, true);
     * }
     * </pre>
     * @param first  The first element.
     * @param second The second element.
     * @param third  The third element.
     */
    public Tripple(final K first, final V second, final T third) {
        super(first, second);
        this.third = third;
    }

    /**
     * <h2>getThird()</h2>
     * <p>Returns the third element.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Boolean value = tripple.getThird();
     * }
     * </pre>
     * @return the third element
     */
    public T getThird() {
        return third;
    }

    /**
     * <h2>setThird(T)</h2>
     * <p>Sets the third element.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     tripple.setThird(false);
     * }
     * </pre>
     * @param value the new value of the third element
     */
    public void setThird(final T value) {
        this.third = value;
    }

    /**
     * <h2>isEmpty()</h2>
     * <p>Checks if the tripple is empty.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *   if(tripple.isEmpty()){
     *      System.out.println("Tripple is empty");
     *   }
     * }
     * </pre>
     * @return true iff the tripple is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    /**
     * <h2>equals(Object)</h2>
     * <p>Compares this tripple to another object.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Tripple<String, Integer, Boolean> tripple = new Tripple<>("Hello", 42, true);
     *     final Tripple<String, Integer, Boolean> otherTripple = new Tripple<>("Hello", 42, true);
     *     if(tripple.equals(otherTripple)){
     *         System.out.println("Tripples are equal");
     *     }
     * }
     * </pre>
     * <p>It does the following checks</p>
     * <ul>
     *     <li>Check if the object is the same as this tripple</li>
     *     <li>Check if the object is null or not an instance of Tripple</li>
     *     <li>Check if the first, second and third elements are equal</li>
     *     <li>Return true if all checks pass, false otherwise</li>
     * </ul>
     * @param obj the object to compare this tripple to
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
        Tripple<?, ?, ?> tripple = (Tripple<?, ?, ?>) obj;
        return getFirst().equals(tripple.getFirst()) && getSecond().equals(tripple.getSecond()) && third.equals(tripple.third);
    }
}
