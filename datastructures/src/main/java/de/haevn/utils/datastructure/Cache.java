package de.haevn.utils.datastructure;

import de.haevn.utils.enumeration.MillisecondTimeUnits;

/**
 * A simple cache class.
 *
 * @param <T> The type of the cached value.
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class Cache <T>{
    private final long duration;
    private final T value;
    private final long creationTime;

    public Cache(final T value){
        this(value, MillisecondTimeUnits.DAYS.getValue());
    }

    public Cache(final T value, long creationTime){
        this.value = value;
        this.duration = creationTime;
        this.creationTime = System.currentTimeMillis();
    }

    public boolean isValid(){
        return System.currentTimeMillis() - creationTime < duration;
    }

    public boolean isInvalid(){
        return !isValid();
    }

    public T getValue() {
        return value;
    }

    public Cache<T> renew(){
        return new Cache<>(value, duration);
    }
}
