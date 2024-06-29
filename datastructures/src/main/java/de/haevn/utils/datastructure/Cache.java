package de.haevn.utils.datastructure;

import de.haevn.utils.enumeration.MillisecondTimeUnits;

/**
 * This is a simple cache class that can be used to cache values for a certain amount of time.<br>
 * The default duration is 1 day.<br>
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

    public Cache(final T value, long duration){
        this.value = value;
        this.duration = duration;
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
