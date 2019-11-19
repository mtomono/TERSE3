/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import math.VectorOp;
import shape.TPoint2d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 * @param <K>
 */
public class GridGraph<K extends List<Integer>> implements AStarGraph<K> {
    public final GridCoord gcoord;
    public final TList<P<K, Double>> dirs;
    public final TList<P<Double,Double>> weight;
    BinaryOperator<K> add;
    Function<List<Integer>,K> translator;
    
    public GridGraph(GridCoord gcoord, TList<K> dirs, TList<Double> cost, TList<Double> weight, BinaryOperator<K> add, Function<List<Integer>,K>translator) {
        this.gcoord = gcoord;
        this.dirs = dirs.pair(cost).sfix();
        this.add = add;
        this.translator=translator;
        this.weight = weight.fold(2).map(l->P.p(l.get(0),l.get(1)));
    }

    @Override
    public double heuristic(K from, K to) {
        return TList.set(VectorOp.subI(to,from)).pair(weight, (a,b)->a>0?a*b.l():a*b.r()).sumD(d->d);
    }

    @Override
    public TList<P<K, Double>> next(K from) {
        return dirs.map(d->P.p(add.apply(from, d.l()),d.r())).filter(p->gcoord.contains(p.l())).sfix();
    }

    @Override
    public TList<K> all() {
        return gcoord.addresses().map(translator);
    }
    
}
