/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;
import solver.graph.MetricGraph;

/**
 *
 * @author masao
 * @param <K>
 */
public class MetricCompositeGraph<K> implements MetricGraph<K> {
    final public TList<MetricGraph<K>> body;
    
    public MetricCompositeGraph(TList<MetricGraph<K>> body) {
        this.body = body;
    }
    
    public MetricCompositeGraph(MetricGraph<K>... body) {
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
}
