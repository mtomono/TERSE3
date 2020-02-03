/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;
import java.util.Map;

/**
 *
 * @author masao
 */
public class MetricGraphCache<K> implements MetricGraph<K> {
    MetricGraph<K> base;
    Map<K,TList<P<K,Double>>> cache;
    public MetricGraphCache(MetricGraph<K> base, Map<K,TList<P<K,Double>>> cache) {
        this.base=base;
        this.cache=cache;
    }

    @Override
    public TList<P<K, Double>> next(K from) {
        return cache.computeIfAbsent(from,f->base.next(f));
    }

    @Override
    public TList<K> all() {
        return base.all();
    }
}
