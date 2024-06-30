package de.haevn.utils;

import java.util.Comparator;
import java.util.List;

/**
 * <h1>ListOperation</h1>
 * <p>ListOperation provides utility methods for lists.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public final class ListOperation {
    private ListOperation() {
    }

    /**
     * <h2>isContentEqual({@link List}, {@link List}, {@link Comparator})</h2>
     * <p>Checks if the content of two lists is equal, internally the {@link #isContentEqual(List, List, Comparator, int)}
     * method is called with a tolerance of 0.</p>
     * <b>Requirement: </b> Both lists must have the same size.
     *
     * @param first      First list.
     * @param second     Second list.
     * @param comparator {@link Comparator} to compare the content of the lists.
     * @param <T>        Type of the list.
     * @return True if the content is equal, false otherwise.
     */
    public static <T> boolean isContentEqual(final List<T> first, final List<T> second, Comparator<T> comparator) {
        return isContentEqual(first, second, comparator, 0);
    }

    /**
     * <h2>isContentEqual({@link List}, {@link List}, {@link Comparator}, int)</h2>
     * <p>Checks if the content of two lists is equal.</p>
     * <p>It compares the content of the lists with the given {@link Comparator} and a tolerance value.</p>
     * <b>Requirement: </b> Both lists must have the same size.
     *
     * @param first      First list.
     * @param second     Second list.
     * @param comparator {@link Comparator} to compare the content of the lists.
     * @param tolerance  Describes the amount of allowed mismatched entities
     * @param <T>        Type of the list.
     * @return True iff the content is equal and the tolerance is fulfilled, false otherwise.
     */
    public static <T> boolean isContentEqual(final List<T> first, final List<T> second, Comparator<T> comparator, final int tolerance) {

        if (null == first || null == second || first.size() != second.size()) {
            return false;
        }

        final List<T> tmpFirst = List.copyOf(first).stream().sorted().toList();
        final List<T> tmpSecond = List.copyOf(second).stream().sorted().toList();

        final int size = first.size();

        int idMatch = 0;
        for (int i = 0; i < size; i++) {
            if (comparator.compare(tmpFirst.get(i), tmpSecond.get(i)) == 0) {
                idMatch++;
            }
        }

        return idMatch >= (size - tolerance) && idMatch <= (size + tolerance);
    }
}
