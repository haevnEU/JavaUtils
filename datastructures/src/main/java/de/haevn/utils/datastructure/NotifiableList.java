package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotifiableList<T> extends ArrayList<T> {
    final List<IListUpdateConsumer<T>> consumers = new ArrayList<>();

    public void subscribe(IListUpdateConsumer<T> consumer) {
        consumers.add(consumer);
    }

    public void unsubscribe(IListUpdateConsumer<T> consumer) {
        consumers.remove(consumer);
    }

    @Override
    public boolean add(final T t) {
        boolean added = super.add(t);
        if (added) {
            consumers.forEach(consumer -> consumer.changed(t, true));
        }
        return added;
    }

    @Override
    public boolean addAll(final Collection<? extends T> list) {
        boolean added = super.addAll(list);
        if (added) {
            list.forEach(t -> consumers.forEach(consumer -> consumer.changed(t, true)));
        }
        return added;
    }

    public boolean removeElement(final T obj) {
        boolean removed = super.remove(obj);
        if (removed) {
            consumers.forEach(consumer -> consumer.changed(obj, false));
        }
        return removed;
    }

    @Override
    public T remove(final int index) {
        T removed = super.remove(index);
        if (removed != null) {
            consumers.forEach(consumer -> consumer.changed(removed, false));
        }
        return removed;
    }

    @Override
    public void clear() {
        super.forEach(t -> consumers.forEach(consumer -> consumer.changed(t, false)));
        super.clear();
    }


    public static interface IListUpdateConsumer<T> {
        void changed(final T object, final boolean added);
    }
}
