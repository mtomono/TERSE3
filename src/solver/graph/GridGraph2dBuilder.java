/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraph2dBuilder {
    GridCoord gcoord;
    TList<TPoint2i> dirs;
    TList<Double> cost;
    TList<Double> weight;
    
    public static GridGraph2dBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static GridGraph2dBuilder builder(GridCoord gcoord) {
        return new GridGraph2dBuilder(gcoord);
    }
    
    public GridGraph2dBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().map(d->p2i(d));
        this.cost=TList.sof(1.0,1.0,1.0,1.0,3.0,3.0);
        this.weight=this.cost;
    }
        
    public GridGraph2dBuilder alt() {
        this.dirs=gcoord.dirsAlternate().map(d->p2i(d));
        return this;
    }
    
    public GridGraph2dBuilder cost(double... cost) {
        this.cost=TList.set(wrap(cost));
        return this;
    }
    
    public GridGraph2dBuilder weight(double... weight) {
        this.weight=TList.set(wrap(weight));
        return this;
    }
    
    public GridGraph<TPoint2i> build() {
        return new GridGraph<>(gcoord,dirs,cost,weight,(a,b)->a.addR(b),a->p2i(a));
    }
}
