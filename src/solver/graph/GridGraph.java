/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;
import static java.lang.Math.abs;
import java.util.List;
import java.util.function.Function;
import math.VectorOp;
import shape.TVector3d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 * @param <K>
 */
public class GridGraph<K extends List<Integer>> implements Graph<K> {
    public final GridCoord gcoord;
    public final TVector3d weight;
    public final TList<K> dirs;
    Function<List<Integer>,K> translator;
    
    public GridGraph(GridCoord gcoord, TList<K> dirs, TVector3d weight, Function<List<Integer>,K>translator) {
        this.gcoord = gcoord;
        this.dirs = dirs;
        this.translator=translator;
        this.weight = weight;
    }

    public double cost(List<Integer> k) {
        return VectorOp.dot(weight, k);
    }

    @Override
    public double heuristic(K from, K to) {
        return cost(VectorOp.subI(to,from));
    }
    
    @Override
    public TList<P<K, Double>> next(K from) {
        return dirs.map(d->P.p(translator.apply(VectorOp.addI(from, d)),cost(d))).filter(p->gcoord.contains(p.l())).sfix();
    }

    @Override
    public TList<K> all() {
        return gcoord.addresses().map(translator);
    }
    
}
