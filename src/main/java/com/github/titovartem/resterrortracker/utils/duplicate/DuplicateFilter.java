package com.github.titovartem.resterrortracker.utils.duplicate;

import com.github.titovartem.resterrortracker.utils.filter.EntityFilter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The filter which selects only unique elements are provided by relevant objects created with
 * {@link com.github.titovartem.resterrortracker.utils.duplicate.EntityHashProxyFactory}.
 */
public class DuplicateFilter<T> implements EntityFilter<T> {

    private EntityHashProxyFactory<T> proxyFactory;

    public DuplicateFilter(EntityHashProxyFactory<T> proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public List<T> filter(List<T> elements) {
        Set<Object> entitySet = new HashSet<Object>();

        List<T> res = new LinkedList<T>();
        for (T entity : elements) {
            Object entityProxy = proxyFactory.create(entity);
            if (!entitySet.contains(entityProxy)) {
                entitySet.add(entityProxy);
                res.add(entity);
            }
        }
        return res;
    }
}
