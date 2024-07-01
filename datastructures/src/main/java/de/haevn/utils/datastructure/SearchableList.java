package de.haevn.utils.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * <h1>SearchableList</h1>
 * <br>
 * <p>This class extends the {@link ArrayList} class and provides a simple way to search for elements in the list.</p>
 * <p>It provides a simple way to search for elements in the list using a {@link Predicate} or a {@link SearchBuilder}.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final SearchableList<String> list = new SearchableList<>();
 *     list.add("Hello");
 *     list.add("World");
 *     list.add("Universe");
 *     final List<String> result = list.search(s -> s.startsWith("W"));
 *     System.out.println(result);
 * }
 * </pre>
 * @param <T> The type of the elements in the list.
 */
public class SearchableList<T> extends ArrayList<T> {

    /**
     * <h2>search({@link Predicate})</h2>
     * <p>Searches for elements in the list that match the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.search(s -> s.startsWith("W"));
     * }
     * </pre>
     * @param query the predicate to search for
     * @return a list of elements that match the predicate
     */
    public List<T> search(final Predicate<T> query) {
        return this.stream().filter(query).toList();
    }

    /**
     * <h2>searchParallel({@link Predicate})</h2>
     * <p>Searches for elements in the list that match the given predicate in parallel.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.searchParallel(s -> s.startsWith("W"));
     * }
     * </pre>
     * @param query the predicate to search for
     * @return a list of elements that match the predicate
     */
    public List<T> searchParallel(final Predicate<T> query) {
        return this.parallelStream().filter(query).toList();
    }

    /**
     * <h2>search({@link Predicate}, int)</h2>
     * <p>Searches for elements in the list that match the given predicate and limits the result to the given amount.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.search(s -> s.startsWith("W"), 5);
     * }
     * </pre>
     * @param query the predicate to search for
     * @param max the maximum amount of elements to return
     * @return a list of elements that match the predicate
     */
    public List<T> search(final Predicate<T> query, int max) {
        return this.stream().filter(query).limit(max).toList();
    }

    /**
     * <h2>searchParallel({@link Predicate}, int)</h2>
     * <p>Searches for elements in the list that match the given predicate in parallel and limits the result to the given amount.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.searchParallel(s -> s.startsWith("W"), 5);
     * }
     * </pre>
     * @param query the predicate to search for
     * @param max the maximum amount of elements to return
     * @return a list of elements that match the predicate
     */
    public List<T> searchParallel(final Predicate<T> query, int max) {
        return this.parallelStream().filter(query).limit(max).toList();
    }

    /**
     * <h2>search({@link SearchBuilder})</h2>
     * <p>Searches for elements in the list that match the given search builder.</p>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.search(list.searchBuilder().add(s -> s.startsWith("W")));
     * }
     * </pre>
     * @param searchBuilder the search builder to use for the search
     * @return a list of elements that match the search builder query
     */
    public List<T> search(final SearchBuilder<T> searchBuilder){
        return search(searchBuilder.build());
    }

    /**
     * <h2>searchParallel({@link SearchBuilder})</h2>
     * <p>Searches for elements in the list that match the given search builder in parallel.</p>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.searchParallel(list.searchBuilder().add(s -> s.startsWith("W")));
     * }
     * </pre>
     * @param searchBuilder the search builder to use for the search
     * @return a list of elements that match the search builder query
     */
    public List<T> searchParallel(final SearchBuilder<T> searchBuilder){
        return searchParallel(searchBuilder.build());
    }

    /**
     * <h2>search({@link SearchBuilder}, int)</h2>
     * <p>Searches for elements in the list that match the given search builder and limits the result to the given amount.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.search(list.searchBuilder().add(s -> s.startsWith("W")), 5);
     * }
     * </pre>
     * @param searchBuilder the search builder to use for the search
     * @param max the maximum amount of elements to return
     * @return a list of elements that match the search builder query
     */
    public List<T> search(final SearchBuilder<T> searchBuilder, int max){
        return search(searchBuilder.build(), max);
    }

    /**
     * <h2>searchParallel({@link SearchBuilder}, int)</h2>
     * <p>Searches for elements in the list that match the given search builder in parallel and limits the result to the given amount.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> result = list.searchParallel(list.searchBuilder().add(s -> s.startsWith("W")), 5);
     * }
     * </pre>
     * @param searchBuilder the search builder to use for the search
     * @param max the maximum amount of elements to return
     * @return a list of elements that match the search builder query
     */
    public List<T> searchParallel(final SearchBuilder<T> searchBuilder, int max){
        return searchParallel(searchBuilder.build(), max);
    }


    /**
     * <h2>searchBuilder()</h2>
     * <p>Creates a new {@link SearchBuilder}.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final SearchBuilder<String> searchBuilder = list.searchBuilder();
     * }
     * </pre>
     * @return a new search builder
     */
    public SearchBuilder<T> searchBuilder(final Predicate<T> predicate){
        return new SearchBuilder<>(predicate);
    }

    /**
     * <h1>SearchBuilder</h1>
     * <br>
     * <p>This class provides a simple way to build a search query for a {@link SearchableList}.</p>
     * <h3>Example</h3>
     * <pre>
     * {@code
     *     final SearchBuilder<String> searchBuilder = list.searchBuilder();
     *     searchBuilder.add(s -> s.startsWith("W")).add(s->s.length() > 5);
     *     final List<String> result = list.search(searchBuilder);
     * }
     * </pre>
     * @param <T> The type of the elements in the list.
     */
    public static class SearchBuilder<T>{
        /**
         * The list of predicates to build the search query.
         */
        private Predicate<T> predicate;

        /**
         * <h2>SearchBuilder({@link Predicate})</h2>
         * <p>Creates a new search builder with given {@link Predicate}.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     final SearchBuilder<String> searchBuilder = list.searchBuilder(_ -> true);
         * }
         * </pre>
         */
        public SearchBuilder(final Predicate<T> predicate){
            this.predicate = predicate;
        }

        /**
         * <h2>add({@link Predicate})</h2>
         * <p>Adds a predicate to the search query using the {@link SearchBuilder#and(Predicate)} operation.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     searchBuilder.add(s -> s.startsWith("W")).add(s->s.length() > 5);
         * }
         * </pre>
         * @param predicate the predicate to add
         * @return the search builder
         */
        public SearchBuilder<T> add(final Predicate<T> predicate){
            return and(predicate);
        }

        /**
         * <h2>build()</h2>
         * <p>Builds the search query.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     final List<String> result = list.search(searchBuilder.build());
         * }
         * </pre>
         * @return the search query
         */
        public Predicate<T> build(){
            return predicate;
        }

        /**
         * <h2>not()</h2>
         * <p>Negates the current predicate.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     searchBuilder.not();
         * }
         * </pre>
         * @return the search builder
         */
        public SearchBuilder<T> not(){
            this.predicate = this.predicate.negate();
            return this;
        }

        /**
         * <h2>or({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the OR operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.or(s -> s.startsWith("W"));
         * }
         * </pre>
         * @param predicate the {@link Predicate} to combine with
         * @return the search builder
         */
        public SearchBuilder<T> or(final Predicate<T> predicate){
            this.predicate = this.predicate.or(predicate);
            return this;
        }

        /**
         * <h2>and({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the AND operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.and(s -> s.startsWith("W"));
         * }
         * </pre>
         * @param predicate the {@link Predicate} to combine with
         * @return the search builder
         */
        public SearchBuilder<T> and(final Predicate<T> predicate){
            this.predicate = this.predicate.and(predicate);
            return this;
        }

        /**
         * <h2>xor({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the XOR operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.xor(s -> s.startsWith("W"));
         * }
         * </pre>
         * @param predicate the {@link Predicate} to combine with
         * @return the search builder
         */
        public SearchBuilder<T> xor(final Predicate<T> predicate){
            this.predicate = this.predicate.or(predicate).and(this.predicate.negate().or(predicate.negate()));
            return this;
        }

        /**
         * <h2>nan({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the NAND operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.nan(s -> s.startsWith("W"));
         * }
         * </pre>
         * @param predicate the {@link Predicate} to combine with
         * @return the search builder
         */
        public SearchBuilder<T> nand(final Predicate<T> predicate){
            this.predicate = this.predicate.and(predicate.negate());
            return this;
        }

        /**
         * <h2>nor({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the NOR operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.nor(s -> s.startsWith("W"));
         * }
         * </pre>
         *
         * @param predicate the {@link Predicate} to combine with
         * @return the search builder
         */
        public SearchBuilder<T> nor(final Predicate<T> predicate){
            this.predicate = this.predicate.or(predicate.negate());
            return this;
        }

        /**
         * <h2>xnor({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the XNOR operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.xnor(s -> s.startsWith("W"));
         * }
         * </pre>
         * @param predicate the {@link Predicate} to negate
         * @return the search builder
         */
        public SearchBuilder<T> xnor(final Predicate<T> predicate){
            this.predicate = this.predicate.and(predicate).or(this.predicate.negate().and(predicate.negate()));
            return this;
        }

        /**
         * <h2>implies({@link Predicate})</h2>
         * <p>Combines the current predicate with the given predicate using the IMPLIES operator.</p>
         * <h3>Example:</h3>
         * <pre>
         * {@code
         *     SearchBuilder<String> searchBuilder = list.searchBuilder(s -> s.length() > 5);
         *     searchBuilder.implies(s -> s.startsWith("W"));
         * }
         * </pre>
         * @param predicate the {@link Predicate} to combine with
         * @return the search builder
         */
        public SearchBuilder<T> implies(final Predicate<T> predicate){
            this.predicate = this.predicate.negate().or(predicate);
            return this;
        }
    }

}
