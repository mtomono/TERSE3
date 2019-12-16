/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;

/**
 *
 * @author masao
 */
public class MetricizedGraph<K> implements MetricGraph<K> {
    Metric<K> metric;
    Graph<K> base;
    public MetricizedGraph(Metric<K> metric, Graph<K> base) {
        this.metric = metric;
        this.base = base;
    }
    
    @Override
    public TList<P<K, Double>> next(K from) {
        return base.next(from).map(k->P.p(k,metric.measure(from, k)));
    }

    @Override
    public TList<K> all() {
        return base.all();
    }
}
