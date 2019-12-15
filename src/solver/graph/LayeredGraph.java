/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;
import solver.graph.Graph;

/**
 *
 * @author masao
 * @param <K>
 */
public class LayeredGraph<K> implements Graph<K> {
    final public TList<Graph<K>> body;
    
    public LayeredGraph(TList<Graph<K>> body) {
        this.body = body;
    }
    
    public LayeredGraph(Graph<K>... body) {
        this(TList.sof(body));
    }

    @Override
    public TList<P<K, Double>> next(K from) {
        return body.map(g->g.next(from)).sfix().flatMap(l->l).sfix();
    }

    @Override
    public TList<K> all() {
        return body.map(g->g.all()).sfix().flatMap(l->l).sfix();
    }

    @Override
    public double heuristic(K from, K to) {
        return body.map(g->g.heuristic(from,to)).filter(d->d<=0).minval(d->d).orElse(0.0);
    }
    
}
