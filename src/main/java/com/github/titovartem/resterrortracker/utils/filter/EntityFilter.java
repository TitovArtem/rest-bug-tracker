package com.github.titovartem.resterrortracker.utils.filter;

import java.util.List;

/**
 * The root interface for filters. A filter provides selection of data from the given elements.
 */
public interface EntityFilter<T> {

    /**
     * Filters the given elements.
     * @param elements a list of elements
     * @return a list of filtered elements
     */
    List<T> filter(List<T> elements);
}
