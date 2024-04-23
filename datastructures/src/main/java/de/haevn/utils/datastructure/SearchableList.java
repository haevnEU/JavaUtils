package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchableList<T> extends ArrayList<T> {
    public List<T> search(final Predicate<T> query) {
        return this.stream().filter(query).toList();
    }
}
