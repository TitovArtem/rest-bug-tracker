package com.github.titovartem.resterrortracker.utils.duplicate;

import com.github.titovartem.resterrortracker.utils.grouper.EntityGroupSeparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The group separator groups elements by relevant objects created with
 * {@link com.github.titovartem.resterrortracker.utils.duplicate.EntityHashProxyFactory}.
 */
public class DuplicateGroupSeparator<E> implements EntityGroupSeparator<Object, E> {

    private EntityHashProxyFactory<E> proxyFactory;

    public DuplicateGroupSeparator(EntityHashProxyFactory<E> proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public Map<Object, List<E>> group(List<E> elements) {
        Map<Object, List<E>> res = new HashMap<Object, List<E>>();
        
        for (E entity : elements) {
            Object entityProxy = proxyFactory.create(entity);
            List<E> list = res.get(entityProxy);
            if (list == null) {
                list = new ArrayList<E>();
                res.put(entityProxy, list);
            }
            list.add(entity);
        }
        return res;
    }
}
