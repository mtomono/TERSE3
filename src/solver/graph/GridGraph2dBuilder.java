/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import shape.ShapeUtil;
import static shape.ShapeUtil.p2i;
import static shape.ShapeUtil.vector3;
import shape.TPoint2i;
import shape.TVector3d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraph2dBuilder {
    GridCoord gcoord;
    TList<TPoint2i> dirs;
    TVector3d weight;
    
    public static GridGraph2dBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static GridGraph2dBuilder builder(GridCoord gcoord) {
        return new GridGraph2dBuilder(gcoord);
    }
    
    public GridGraph2dBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().map(d->p2i(d)).sfix();
        this.weight=vector3(1,1,3);
    }
        
    public GridGraph2dBuilder alt() {
        this.dirs=gcoord.dirsAlternate().map(d->p2i(d));
        return this;
    }
    
    public GridGraph2dBuilder weight(double... weight) {
        this.weight=vector3(wrap(weight));
        return this;
    }
    
    public GridGraph<TPoint2i> build() {
        return new GridGraph<>(gcoord,dirs,weight,ShapeUtil::p2i);
    }
}
