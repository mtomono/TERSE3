/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.List;
import math2.C;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface GridMetric extends Metric<List<Double>> {
    @Override
    public double measure(List<Double> from, List<Double> to);
    static public GridMetric t(Metric<List<Double>> m) {
        return (f,t)->m.measure(f,t);
    }
    public static GridMetric l2() {
        return (f,t)->Math.sqrt(TList.set(t).pair(f,(x,y)->x-y).toC(d->d*d,C.d).sigma().body());
    }
    public static GridMetric l1() {
        return (f,t)->TList.set(t).pair(f,(x,y)->Math.abs(x-y)).toC(i->i,C.d).sigma().body();
    }
    default Metric<List<Integer>> i() {
        return morph(l->TList.set(l).map(i->(double)i));
    }
    default GridMetric weight(TList<? extends Number> weight) {
        return t(morph(l->weight.pair(l,(a,b)->a.doubleValue()*b)));
    }
    default GridMetric costv(double costv) {
        return weight(TList.sof(1, 1, costv));
    }
    
}
