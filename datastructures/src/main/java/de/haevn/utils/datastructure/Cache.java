package de.haevn.utils.datastructure;

import de.haevn.utils.enumeration.MillisecondTimeUnits;

/**
 * <h1>Cache</h1>
 * <br>
 * <p>This is a simple cache class that can be used to cache values for a certain amount of time.</p>
 * <p>The default duration is 1 day.</p>
 * <p>It is possible to renew the cache by calling the {@link Cache#renew()} method.</p>
 *
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final Cache<String> cache = new Cache<>("Hello World");
 *     if(cache.isValid()){
 *        System.out.println(cache.getValue());
 *     }
 *     cache.renew();
 *     System.out.println(cache.isValid());
 *     String value = cache.getValue();
 * }
 * </pre>
 * @param <T> The type of the cached value.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class Cache <T>{

    /**
     * The duration of the cache in milliseconds.
     */
    private final long duration;

    /**
     * The value of the cache.
     */
    private final T value;

    /**
     * The creation time of the cache.
     */
    private final long creationTime;

    /**
     * <h2>Cache(T)</h2>
     * <p>Creates a new cache with the given value and a default duration of 1 day.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Cache<String> cache = new Cache<>("Hello World");
     *     }
     * </pre>
     * @param value the value to be cached
     */
    public Cache(final T value){
        this(value, MillisecondTimeUnits.DAYS.getValue());
    }

    /**
     * <h2>Cache(T, long)</h2>
     * <p>Creates a new cache with the given value and duration.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Cache<String> cache = new Cache<>("Hello World", 1000);
     *     }
     * </pre>
     * @param value the value to be cached
     * @param duration the duration of the cache
     */
    public Cache(final T value, long duration){
        this.value = value;
        this.duration = duration;
        this.creationTime = System.currentTimeMillis();
    }

    /**
     * <h2>isValid()</h2>
     * <p>Checks if the cache is still valid.</p>
     * <p>A cache is valid if the current time is less than the creation time plus the duration.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Cache<String> cache = new Cache<>("Hello World");
     *     if(cache.isValid()){
     *        System.out.println(cache.getValue());
     *     }
     *     }
     * </pre>
     * @return true if the cache is valid, false otherwise
     */
    public boolean isValid(){
        return System.currentTimeMillis() - creationTime < duration;
    }

    /**
     * <h2>isInvalid()</h2>
     * <p>Checks if the cache is invalid.</p>
     * <p>A cache is invalid if the current time is greater than the creation time plus the duration.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Cache<String> cache = new Cache<>("Hello World");
     *     if(cache.isInvalid()){
     *        System.out.println("Cache is invalid");
     *     }
     *     }
     * </pre>
     * @return true if the cache is invalid, false otherwise
     */
    public boolean isInvalid(){
        return !isValid();
    }

    /**
     * <h2>getValue()</h2>
     * <p>Returns the value of the cache.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Cache<String> cache = new Cache<>("Hello World");
     *     System.out.println(cache.getValue());
     *     }
     * </pre>
     * @return the value of the cache
     */
    public T getValue() {
        return value;
    }

    /**
     * <h2>renew()</h2>
     * <p>Creates a new cache with the same value and duration.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Cache<String> cache = new Cache<>("Hello World");
     *     cache.renew();
     *     }
     * </pre>
     * @return a new cache with the same value and duration
     */
    public Cache<T> renew(){
        return new Cache<>(value, duration);
    }
}
