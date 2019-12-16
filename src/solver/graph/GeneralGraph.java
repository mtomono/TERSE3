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
import solver.graph.Graph;

/**
 *
 * @author masao
 * @param <K>
 */
public class GeneralGraph<K> implements Graph<K> {
    MVMap<K,K> body;
    
    public GeneralGraph(Map<K,TList<K>> body) {
        this.body = new MVMap<>(body);
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
    
    public GeneralGraph<K> addPath(K from, K to) {
        body.getList(from).add(to);
        return this;
    }
}
