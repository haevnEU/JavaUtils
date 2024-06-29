package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <h1>NotifiableList</h1>
 * <br>
 * <p>This class extends the {@link ArrayList} class and provides the {@link IListUpdateConsumer} interface to notify
 * consumers about changes in the list.</p>
 *
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final NotifiableList<String> list = new NotifiableList<>();
 *     list.subscribe((object, added) -> System.out.println("Object " + object + " was " + (added ? "added" : "removed")));
 *     list.add("Hello World");
 *     list.remove("Hello World");
 * }
 * </pre>
 * @param <T>
 */
public class NotifiableList<T> extends ArrayList<T> {

    /**
     * The list of consumers that are subscribed to this list.
     */
    final List<IListUpdateConsumer<T>> consumers = new ArrayList<>();

    /**
     * <h2>subscribe(IListUpdateConsumer)</h2>
     * <p>Subscribes a consumer to this list.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     list.subscribe((object, added) -> System.out.println("Object " + object + " was " + (added ? "added" : "removed")));
     *     }
     * </pre>
     * @param consumer the consumer to be subscribed
     */
    public void subscribe(final IListUpdateConsumer<T> consumer) {
        consumers.add(consumer);
    }

    /**
     * <h2>unsubscribe(IListUpdateConsumer)</h2>
     * <p>Unsubscribes a consumer from this list.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     list.unsubscribe(consumer);
     *     }
     * </pre>
     * @param consumer the consumer to be unsubscribed
     */
    public void unsubscribe(final IListUpdateConsumer<T> consumer) {
        consumers.remove(consumer);
    }

    /**
     * <h2>add(T)</h2>
     * <p>Appends the specified element to the end of this list and notifies all subscribed consumers.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     list.add("Hello World");
     * }
     * </pre>
     * @see ArrayList#add(Object)
     * @param t element whose presence in this collection is to be ensured
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean add(final T t) {
        boolean added = super.add(t);
        if (added) {
            consumers.forEach(consumer -> consumer.changed(t, true));
        }
        return added;
    }

    /**
     * <h2>addAll(Collection)</h2>
     * <p>Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator and notifies all subscribed consumers.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     list.addAll(Arrays.asList("Hello", "World"));
     * }
     * </pre>
     * @see ArrayList#addAll(Collection)
     * @param list collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(final Collection<? extends T> list) {
        boolean added = super.addAll(list);
        if (added) {
            list.forEach(t -> consumers.forEach(consumer -> consumer.changed(t, true)));
        }
        return added;
    }

    /**
     * <h2>remove(Object)</h2>
     * <p>Removes the first occurrence of the specified element from this list, if it is present and notifies all subscribed consumers.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     list.remove("Hello World");
     * }
     * </pre>
     * @see ArrayList#remove(Object)
     * @param obj object to be removed from this list, if present
     * @return true if an element was removed as a result of this call
     */
    public boolean removeElement(final T obj) {
        boolean removed = super.remove(obj);
        if (removed) {
            consumers.forEach(consumer -> consumer.changed(obj, false));
        }
        return removed;
    }

    /**
     * <h2>remove(int)</h2>
     * <p>Removes the element at the specified position in this list and notifies all subscribed consumers.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     list.remove(0);
     * }
     * </pre>
     *
     * @see ArrayList#remove(int)
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    @Override
    public T remove(final int index) {
        T removed = super.remove(index);
        if (removed != null) {
            consumers.forEach(consumer -> consumer.changed(removed, false));
        }
        return removed;
    }

    /**
     * <h2>clear()</h2>
     * <p>Removes all of the elements from this list and notifies all subscribed consumers.</p>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     list.clear();
     * }
     * </pre>
     * @see ArrayList#clear()
     */
    @Override
    public void clear() {
        super.forEach(t -> consumers.forEach(consumer -> consumer.changed(t, false)));
        super.clear();
    }

    /**
     * <h2>IListUpdateConsumer</h2>
     * <p>Functional interface to consume changes in a list.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     list.subscribe((object, added) -> System.out.println("Object " + object + " was " + (added ? "added" : "removed")));
     * }
     * </pre>
     * @param <T>
     */
    public interface IListUpdateConsumer<T> {
        /**
         * <h2>changed(T, boolean)</h2>
         * <p>Called when an object is added or removed from the list.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *      list.subscribe((object, added) -> System.out.println("Object " + object + " was " + (added ? "added" : "removed")));
         *      IListUpdateConsumer consumer = new IListUpdateConsumer() {
         *          @Override
         *          public void changed(Object object, boolean added) {
         *              System.out.println("Object " + object + " was " + (added ? "added" : "removed"));
         *          }
         *      };
         * }
         * @param object the object that was added or removed
         * @param added true if the object was added, false if it was removed
         */
        void changed(final T object, final boolean added);
    }
}
