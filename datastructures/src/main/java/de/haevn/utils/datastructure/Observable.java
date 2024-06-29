package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Observable</h1>
 * <br>
 * <p>This class wraps a value and provides a method to change the corresponding value.</p>
 * <p>If the value changes all subscribed observers will be notified.</p>
 *
 * <h3>Example</h3>
 * <pre>
 *{@code
 *     final Observable<String> observable = new Observable<>("Hello World");
 *     observable.addObserver(value -> System.out.println("Value changed to: " + value));
 *     observable.set("Hello Universe");
 *}
 *</pre>
 * @param <T> The type of the value
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class Observable<T> {
    /**
     * The value of the observable.
     */
    private T value;

    /**
     * The list of observers that are subscribed to this observable.
     */
    private final List<IObserver<T>> observers = new ArrayList<>();

    /**
     * <h2>Observable(T)</h2>
     * <p>Creates a new observable with the given value.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final Observable<String> observable = new Observable<>("Hello World");
     *     }
     * </pre>
     * @param value the initial value of the observable
     */
    public Observable(final T value) {
        this.value = value;
    }

    /**
     * <h2>get()</h2>
     * <p>Returns the current value of the observable.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     final String value = observable.get();
     *     }
     * </pre>
     * @return the current value of the observable
     */
    public T get() {
        return value;
    }

    /**
     * <h2>set(T)</h2>
     * <p>Sets the value of the observable and notifies all observers.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     observable.set("Hello Universe");
     *     }
     * </pre>
     * @param value the new value of the observable
     */
    public void set(final T value) {
        synchronized (this) {
            this.value = value;
        }
        observers.forEach(observer -> observer.update(value));
    }

    /**
     * <h2>addObserver(IObserver)</h2>
     * <p>Subscribes an observer to this observable.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     observable.addObserver(value -> System.out.println("Value changed to: " + value));
     *     }
     * </pre>
     * @param observer the observer to be subscribed
     */
    public void addObserver(final IObserver<T> observer) {
        observers.add(observer);
    }

    /**
     * <h2>removeObserver(IObserver)</h2>
     * <p>Unsubscribes an observer from this observable.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     observable.removeObserver(observer);
     *     }
     * </pre>
     * @param observer the observer to be unsubscribed
     */
    public void removeObserver(final IObserver<T> observer) {
        observers.remove(observer);
    }

    /**
     * <h1>IObserver</h1>
     * <br>
     * <p>This interface is used to observe changes in an {@link Observable}.</p>
     * @param <T> The type of the value
     */
    public interface IObserver<T> {
        /**
         * <h2>update(T)</h2>
         * <p>Is called when the value of the observable changes.</p>
         * <h3>Example:</h3>
         * <pre>
         *     {@code
         *     observable.addObserver(value -> System.out.println("Value changed to: " + value));
         *     }
         * </pre>
         * @param value the new value of the observable
         */
        void update(T value);
    }
}
