package com.github.titovartem.resterrortracker.utils.filter.duplicate;

import com.github.titovartem.resterrortracker.utils.filter.EntityFilter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The filter which selects only unique elements are provided by relevant objects created with
 * {@link com.github.titovartem.resterrortracker.utils.filter.duplicate.EntityHashProxyFactory}.
 */
public class DuplicateFilter<T> implements EntityFilter<T> {

    private EntityHashProxyFactory<T> decoratorFactory;

    public DuplicateFilter(EntityHashProxyFactory<T> decoratorFactory) {
        this.decoratorFactory = decoratorFactory;
    }

    @Override
    public List<T> filter(List<T> elements) {
        Set<Object> entitySet = new HashSet<Object>();

        List<T> res = new LinkedList<T>();
        for (T entity : elements) {
            Object entityDecorator = decoratorFactory.create(entity);
            if (!entitySet.contains(entityDecorator)) {
                entitySet.add(entityDecorator);
                res.add(entity);
            }
        }
        return res;
    }
}
