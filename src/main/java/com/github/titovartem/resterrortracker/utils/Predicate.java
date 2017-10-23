package com.github.titovartem.resterrortracker.utils;

/**
 * Represents a boolean-valued function of one argument.
 */
public interface Predicate<T> {

    /**
     * Checks the given argument for this predicate.
     * @param t the given argument
     * @return true if the given argument matches the predicate, otherwise false
     */
    boolean apply(T t);
}
