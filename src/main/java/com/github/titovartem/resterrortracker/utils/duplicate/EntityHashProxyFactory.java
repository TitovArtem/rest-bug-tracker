package com.github.titovartem.resterrortracker.utils.duplicate;

/**
 * This factory creates an object of class with overrided {@link Object#hashCode()}
 * and {@link Object#equals(Object)} methods for necessary fields of the given entity.
 * It's necessary for entities which should be used in
 * {@link com.github.titovartem.resterrortracker.utils.duplicate.DuplicateFilter} and
 * can't override <tt>hashCode</tt> and <tt>equals</tt> methods for necessary fields.
 */
public interface EntityHashProxyFactory<T> {

    /**
     * @param entity an entity which needs to override {@link Object#hashCode()}
     * and {@link Object#equals(Object)} methods
     * @return an object of class with overrided {@link Object#hashCode()}
     * and {@link Object#equals(Object)} methods for necessary fields of the given entity
     */
    Object create(T entity);
}