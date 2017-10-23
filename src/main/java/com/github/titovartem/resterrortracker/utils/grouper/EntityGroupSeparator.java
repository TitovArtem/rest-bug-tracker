package com.github.titovartem.resterrortracker.utils.grouper;

import java.util.List;
import java.util.Map;

/**
 * The root interface for group separators.
 * A group separator provides grouping of data from the given elements.
 */
public interface EntityGroupSeparator<K, E> {

    /**
     * Groups the given elements.
     * @param elements a list of elements
     * @return a map where keys are groups and values are elements
     */
    Map<K, List<E>> group(List<E> elements);
}
