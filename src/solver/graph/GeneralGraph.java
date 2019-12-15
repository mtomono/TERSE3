/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

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
public class GeneralGraph<K> implements MetricGraph<K> {
    MVMap<K,P<K,Double>> body;
    
    public GeneralGraph(Map<K,TList<P<K,Double>>> body) {
        this.body = new MVMap<>(body);
    }
    @Override
    public TList<P<K,Double>> next(K from) {
        return body.getList(from);
    }
    
    @Override
    public TList<K> all() {
        Set<K> retval = new HashSet<>(body.keySet());
        for (TList<P<K,Double>> v : body.values())
            retval.addAll(v.map(p->p.l()));
        return TList.set(retval);
    }
    
    public GeneralGraph<K> addPath(K from, K to, double distance) {
        body.getList(from).add(P.p(to, distance));
        return this;
    }

    @Override
    public double heuristic(K from, K to) {
        return 0;
    }
}
