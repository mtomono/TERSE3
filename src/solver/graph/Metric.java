/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.List;
import math.VectorOp;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface Metric<K> {
    public double measure(K from, K to);
    static public Metric<List<? extends Number>> l2 = (f,t)->sqrt(TList.set(t).pair(f,(x,y)->(pow(x.doubleValue()-y.doubleValue(),2))).sumD(i->i));
    static public Metric<List<? extends Number>> l1 = (f,t)->TList.set(t).pair(f,(x,y)->abs(x.doubleValue()-y.doubleValue())).sumD(i->i);
    static public Metric<List<? extends Number>> weighted(Metric<List<? extends Number>> base, List<? extends Number> weight) {
        TList<? extends Number> w = TList.set(weight);
        return (f,t)->base.measure(w.pair(f,(a,b)->a.doubleValue()*b.doubleValue()),w.pair(t,(a,b)->a.doubleValue()*b.doubleValue()));
    }
}
