/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static shape.ShapeUtil.p3i;
import shape.TPoint3i;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraph3dBuilder {
    GridCoord gcoord;
    TList<TPoint3i> dirs;
    TList<Double> cost;
    TList<Double> weight;
    
    public static GridGraph3dBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static GridGraph3dBuilder builder(GridCoord gcoord) {
        return new GridGraph3dBuilder(gcoord);
    }
    
    public GridGraph3dBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().map(d->p3i(d));
        this.cost=TList.sof(1.0,1.0,1.0,1.0,3.0,3.0);
        this.weight=this.cost;
    }
        
    public GridGraph3dBuilder alt() {
        this.dirs=gcoord.dirsAlternate().map(d->p3i(d));
        return this;
    }
    
    public GridGraph3dBuilder cost(double... cost) {
        this.cost=TList.set(wrap(cost));
        return this;
    }
    
    public GridGraph3dBuilder weight(double... weight) {
        this.weight=TList.set(wrap(weight));
        return this;
    }
    
    public GridGraph<TPoint3i> build() {
        return new GridGraph<>(gcoord,dirs,cost,weight,(a,b)->a.addR(b),a->p3i(a));
    }
}
