package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    private T value;
    private final List<IObserver<T>> observers = new ArrayList<>();

    public Observable(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        synchronized (this) {
            this.value = value;
        }
        observers.forEach(observer -> observer.update(value));
    }

    public void addObserver(final IObserver<T> observer) {
        observers.add(observer);
    }

    public void removeObserver(final IObserver<T> observer) {
        observers.remove(observer);
    }

    public interface IObserver<T> {
        void update(T value);
    }
}
