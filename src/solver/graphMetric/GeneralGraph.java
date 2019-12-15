/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graphMetric;

import solver.graph.BareGraph;
import collection.MVMap;
import collection.P;
import collection.TList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author masao
 * @param <K>
 */
public class GeneralGraph<K> implements BareGraph<K> {
    MVMap<K,K> body;
    Map<P<K,K>,Double> metric;
    
    public GeneralGraph(Map<K,TList<K>> body, Map<P<K,K>,Double> metric) {
        this.body = new MVMap<>(body);
        this.metric=metric;
    }
    @Override
    public TList<K> next(K from) {
        return body.getList(from);
    }
    
    @Override
    public TList<K> all() {
        Set<K> retval = new HashSet<>(body.keySet());
        for (TList<K> v : body.values())
            retval.addAll(v);
        return TList.set(retval);
    }
    
    public GeneralGraph<K> addPath(K from, K to, double distance) {
        body.getList(from).add(to);
        metric.put(P.p(from, to), distance);
        return this;
    }
}
