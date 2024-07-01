package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * <h1>ObjectGroup</h1>
 * <br>
 * <p>This class provides a simple way to group objects together.</p>
 * <p>Following operations are supported:</p>
 * <ul>
 *     <li>Adding elements</li>
 *     <li>Removing elements</li>
 *     <li>Clearing the group</li>
 *     <li>Getting the elements</li>
 *     <li>Check if an element is in the group</li>
 *     <li>Filtering for specific elements</li>
 *     <li>Applying a custom operation to each element</li>
 * </ul>
 * <p>Note: Most operation are piped and return their instance</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final ObjectGroup<String> group = new ObjectGroup<>();
 *     group.add("Hello")
 *     .add("World")
 *     .forEach(System.out::println)
 *     .add("!")
 *     .forEach(System.out::println)
 *     .clear();
 * }
 * </pre>
 *
 * @param <T> The type of the object
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class ObjectGroup<T> {
    private final List<T> elements;

    /**
     * <h2>ObjectGroup()</h2>
     * <p>Creates a new ObjectGroup with an empty list of elements.</p>
     */
    public ObjectGroup() {
        this(new ArrayList<>());
    }

    /**
     * <h2>ObjectGroup(T...)</h2>
     * <p>Creates a new ObjectGroup with the given elements.</p>
     * @param elements The elements to be added to the group
     */
    @SafeVarargs
    public ObjectGroup(final T... elements) {
        this(List.of(elements));
    }

    /**
     * <h2>ObjectGroup(List)</h2>
     * <p>Creates a new ObjectGroup with the given list of elements.</p>
     * @param elements The list of elements to be added to the group
     */
    public ObjectGroup(final List<T> elements) {
        this.elements = elements;
    }

    /**
     * <h2>add(T)</h2>
     * <p>Adds an element to the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>();
     *     group.add("Hello World");
     * }
     * </pre>
     * @param element The element to be added
     * @return The ObjectGroup instance
     */
    public ObjectGroup<T> add(final T element) {
        elements.add(element);
        return this;
    }

    /**
     * <h2>addAll(T...)</h2>
     * <p>Adds multiple elements to the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>();
     *     group.addAll("Hello", "World");
     * }
     * </pre>
     * @param elements The elements to be added
     * @return The ObjectGroup instance
     */
    @SafeVarargs
    public final ObjectGroup<T> addAll(final T... elements) {
        this.elements.addAll(List.of(elements));
        return this;
    }

    /**
     * <h2>addAll(List)</h2>
     * <p>Adds multiple elements to the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>();
     *     group.addAll(List.of("Hello", "World"));
     * }
     * </pre>
     * @param elements The elements to be added
     * @return The ObjectGroup instance
     */
    public ObjectGroup<T> addAll(final List<T> elements) {
        this.elements.addAll(elements);
        return this;
    }

    /**
     * <h2>remove(T)</h2>
     * <p>Removes an element from the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     group.remove(obj);
     * }
     * </pre>
     * @param element The element to be removed
     * @return The ObjectGroup instance
     */
    public ObjectGroup<T> remove(final T element) {
        elements.remove(element);
        return this;
    }

    /**
     * <h2>clear()</h2>
     * <p>Removes all elements from the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     group.clear();
     * }
     * </pre>
     * @return The ObjectGroup instance
     */
    public ObjectGroup<T> clear() {
        elements.clear();
        return this;
    }

    /**
     * <h2>getElements()</h2>
     * <p>Returns the list of elements.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     List<Object> elements = group.getElements();
     * }
     * </pre>
     * @return The list of elements
     */
    public List<T> getElements() {
        return elements;
    }

    /**
     * <h2>size()</h2>
     * <p>Returns the number of elements in the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     int size = group.size();
     * }
     * </pre>
     *
     * @return The number of elements in the group
     */
    public int size() {
        return elements.size();
    }

    /**
     * <h2>isEmpty()</h2>
     * <p>Checks if the group is empty.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     boolean isEmpty = group.isEmpty();
     * }
     * </pre>
     * @return true if the group is empty, false otherwise
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * <h2>contains(T)</h2>
     * <p>Checks if the group contains a specific element.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     boolean contains = group.contains(obj);
     * }
     * </pre>
     * @param element The element to be checked
     * @return true iff the group contains the element, false otherwise
     */
    public boolean contains(final T element) {
        return elements.contains(element);
    }

    /**
     * <h2>get(int)</h2>
     * <p>Returns the element at the specified position in the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     Object obj = group.get(0);
     * }
     * </pre>
     * @param index The index of the element
     * @return The element at the specified position
     */
    public T get(final int index) {
        return elements.get(index);
    }

    /**
     * <h2>filter(Predicate)</h2>
     * <p>Filters the elements of the group based on the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<Object> group = new ObjectGroup<>();
     *     List<Object> filtered = group.filter(obj -> obj instanceof String);
     * }
     * </pre>
     * @param predicate The predicate to be used for filtering
     * @return The list of filtered elements
     */
    public List<T> filter(final Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).toList();
    }

    /**
     * <h2>findFirst(Predicate)</h2>
     * <p>Returns the first element of the group that matches the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     Optional<String> first = group.findFirst(str -> str.startsWith("H"));
     * }
     * </pre>
     * @param predicate The predicate to be used for filtering
     * @return The first element that matches the predicate
     */
    public Optional<T> findFirst(final Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).findFirst();
    }

    /**
     * <h2>findAny(Predicate)</h2>
     * <p>Returns any element of the group that matches the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     Optional<String> any = group.findAny(str -> str.startsWith("H"));
     * }
     * </pre>
     * @param predicate The predicate to be used for filtering
     * @return Any element that matches the predicate
     */
    public Optional<T> findAny(final Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).findAny();
    }

    /**
     * <h2>allMatch(Predicate)</h2>
     * <p>Checks if all elements of the group match the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     boolean allMatch = group.allMatch(str -> str.length() > 3);
     * }
     * </pre>
     * @param predicate The predicate to be used for matching
     * @return true if all elements match the predicate, false otherwise
     */
    public boolean allMatch(final Predicate<? super T> predicate) {
        return elements.stream().allMatch(predicate);
    }

    /**
     * <h2>anyMatch(Predicate)</h2>
     * <p>Checks if any element of the group matches the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     boolean anyMatch = group.anyMatch(str -> str.startsWith("H"));
     * }
     * </pre>
     * @param predicate The predicate to be used for matching
     * @return true if any element matches the predicate, false otherwise
     */
    public boolean anyMatch(final Predicate<? super T> predicate) {
        return elements.stream().anyMatch(predicate);
    }

    /**
     * <h2>noneMatch(Predicate)</h2>
     * <p>Checks if none of the elements of the group match the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     boolean noneMatch = group.noneMatch(str -> str.length() > 10);
     * }
     * </pre>
     * @param predicate The predicate to be used for matching
     * @return true if none of the elements match the predicate, false otherwise
     */
    public boolean noneMatch(Predicate<? super T> predicate) {
        return elements.stream().noneMatch(predicate);
    }

    /**
     * <h2>forEach(Consumer)</h2>
     * <p>Performs the given action for each element of the group.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     group.forEach(System.out::println);
     * }
     * </pre>
     * @param action The action to be performed
     * @return The ObjectGroup instance
     */
    public ObjectGroup<T> forEach(final Consumer<? super T> action) {
        elements.forEach(action);
        return this;
    }

    /**
     * <h2>stream()</h2>
     * <p>Returns a sequential {@link Stream} with this group as its source.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     Stream<String> stream = group.stream();
     * }
     * </pre>
     * @return The stream of elements
     */
    public Stream<T> stream() {
        return elements.stream();
    }

    /**
     * <h2>parallelStream()</h2>
     * <p>Returns a possibly parallel {@link Stream} with this group as its source.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final ObjectGroup<String> group = new ObjectGroup<>("Hello", "World");
     *     Stream<String> stream = group.parallelStream();
     * }
     * </pre>
     * @return The parallel stream of elements
     */
    public Stream<T> parallelStream() {
        return elements.parallelStream();
    }
}
