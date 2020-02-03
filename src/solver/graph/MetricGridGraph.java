/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;
import java.util.List;

/**
 *
 * @author masao
 */
public class MetricGridGraph implements MetricGraph<List<Integer>> {
    GridGraph base;
    TList<Double> distance;
    public MetricGridGraph(GridGraph base, Metric<List<Integer>> metric) {
        this.base=base;
        List<Integer> zero = TList.nCopies(base.dirs.get(0).size(),0).sfix();
        this.distance=base.dirs.map(d->metric.measure(zero, d)).sfix();
    }

    @Override
    public TList<P<List<Integer>, Double>> next(List<Integer> from) {
        return base.next(from).pair(distance);
    }

    @Override
    public TList<List<Integer>> all() {
        return base.all();
    }
}
