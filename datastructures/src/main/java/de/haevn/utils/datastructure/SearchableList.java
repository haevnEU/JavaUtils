package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchableList<T> extends ArrayList<T> {
    public List<T> search(final Predicate<T> query) {
        return this.stream().filter(query).toList();
    }

    public List<T> searchParallel(final Predicate<T> query) {
        return this.parallelStream().filter(query).toList();
    }

    public List<T> search(final Predicate<T> query, int max) {
        return this.stream().filter(query).limit(max).toList();
    }

    public List<T> searchParallel(final Predicate<T> query, int max) {
        return this.parallelStream().filter(query).limit(max).toList();
    }

    public List<T> search(final SearchBuilder<T> searchBuilder){
        return search(searchBuilder.build());
    }

    public List<T> searchParallel(final SearchBuilder<T> searchBuilder){
        return searchParallel(searchBuilder.build());
    }

    public List<T> search(final SearchBuilder<T> searchBuilder, int max){
        return search(searchBuilder.build(), max);
    }

    public List<T> searchParallel(final SearchBuilder<T> searchBuilder, int max){
        return searchParallel(searchBuilder.build(), max);
    }




    public SearchBuilder<T> searchBuilder(){
        return new SearchBuilder<>();
    }

    public static class SearchBuilder<T>{

        List<Predicate<T>> predicates = new ArrayList<>();

        public SearchBuilder<T> add(Predicate<T> predicate){
            predicates.add(predicate);
            return this;
        }

        public Predicate<T> build(){
            return predicates.stream().reduce(Predicate::and).orElse(_ -> true);
        }
    }

}
