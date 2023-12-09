package de.haevn.utils;

import java.util.Comparator;
import java.util.List;

/**
 * This class provides some useful methods for lists.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public final class ListOperation {
    private ListOperation() {
    }

    /**
     * Checks if the content of two lists is equal.
     *
     * @param first      First list.
     * @param second     Second list.
     * @param comparator {@link Comparator} to compare the content of the lists.
     * @param <T>        Type of the list.
     * @return True if the content is equal, false otherwise.
     */
    public static <T> boolean isContentEqual(List<T> first, List<T> second, Comparator<T> comparator) {
        return isContentEqual(first, second, comparator, 4);
    }

    /**
     * Checks if the content of two lists is equal.
     *
     * @param first      First list.
     * @param second     Second list.
     * @param comparator {@link Comparator} to compare the content of the lists.
     * @param tolerance  Describes the amount of missmatched entities
     * @param <T>        Type of the list.
     * @return True if the content is equal, false otherwise.
     */
    public static <T> boolean isContentEqual(List<T> first, List<T> second, Comparator<T> comparator, int tolerance) {

        if (null == first || null == second || first.size() != second.size()) {
            return false;
        }

        int idMatch = 0;
        for (int i = 0; i < first.size(); i++) {
            if (comparator.compare(first.get(i), second.get(i)) == 0) {
                idMatch++;
            }
        }

        // BUG this is a potential bug, the tolerance comparison should be in range of the tolerance
        return idMatch == tolerance;
    }
}
