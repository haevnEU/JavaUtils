package de.haevn.javautils.utils;

public class Cache <T>{
    private final long duration;
    private final T value;
    private final long creationTime;

    public Cache(T value){
        this(value, 1000 * 60 * 60 * 24);
    }

    public Cache(T value, long creationTime){
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
