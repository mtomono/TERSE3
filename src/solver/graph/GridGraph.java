/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.List;
import math.VectorOp;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraph implements Graph<List<Integer>> {
    public final GridCoord gcoord;
    public final TList<List<Integer>> dirs;
    
    public GridGraph(GridCoord gcoord, TList<List<Integer>> dirs) {
        this.gcoord = gcoord;
        this.dirs = dirs;
    }

    @Override
    public TList<List<Integer>> next(List<Integer> from) {
        return dirs.map(d->VectorOp.addI(from, d)).filter(p->gcoord.contains(p)).sfix();
    }

    @Override
    public TList<List<Integer>> all() {
        return gcoord.addresses();
    }
    
}
