package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * A simple class for grouping objects.
 *
 * @param <T> The type of the object
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public class ObjectGroup<T> {
    private final List<T> elements;

    public ObjectGroup() {
        this(new ArrayList<>());
    }

    @SafeVarargs
    public ObjectGroup(final T... elements) {
        this(List.of(elements));
    }

    public ObjectGroup(final List<T> elements) {
        this.elements = elements;
    }

    public ObjectGroup<T> add(final T element) {
        elements.add(element);
        return this;
    }

    @SafeVarargs
    public final ObjectGroup<T> addAll(final T... elements) {
        this.elements.addAll(List.of(elements));
        return this;
    }

    public ObjectGroup<T> remove(final T element) {
        elements.remove(element);
        return this;
    }

    public ObjectGroup<T> clear() {
        elements.clear();
        return this;
    }

    public List<T> getElements() {
        return elements;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(final T element) {
        return elements.contains(element);
    }

    public T get(final int index) {
        return elements.get(index);
    }

    public List<T> filter(final Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).toList();
    }

    public Optional<T> findFirst(final Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).findFirst();
    }

    public Optional<T> findAny(final Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).findAny();
    }

    public boolean allMatch(final Predicate<? super T> predicate) {
        return elements.stream().allMatch(predicate);
    }

    public boolean anyMatch(final Predicate<? super T> predicate) {
        return elements.stream().anyMatch(predicate);
    }

    public boolean noneMatch(Predicate<? super T> predicate) {
        return elements.stream().noneMatch(predicate);
    }

    public ObjectGroup<T> forEach(final Consumer<? super T> action) {
        elements.forEach(action);
        return this;
    }

    public Stream<T> stream() {
        return elements.stream();
    }
}
