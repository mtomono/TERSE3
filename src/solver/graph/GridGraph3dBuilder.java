/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import shape.ShapeUtil;
import static shape.ShapeUtil.p3i;
import static shape.ShapeUtil.vector3;
import shape.TPoint3i;
import shape.TVector3d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraph3dBuilder {
    GridCoord gcoord;
    TList<TPoint3i> dirs;
    TVector3d weight;
    
    public static GridGraph3dBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static GridGraph3dBuilder builder(GridCoord gcoord) {
        return new GridGraph3dBuilder(gcoord);
    }
    
    public GridGraph3dBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().map(ShapeUtil::p3i);
        this.weight=vector3(1,1,3);
    }
        
    public GridGraph3dBuilder alt() {
        this.dirs=gcoord.dirsAlternate().map(d->p3i(d));
        return this;
    }
    
    public GridGraph3dBuilder weight(double... weight) {
        this.weight=vector3(wrap(weight));
        return this;
    }
    
    public GridGraph<TPoint3i> build() {
        return new GridGraph<>(gcoord,dirs,weight,ShapeUtil::p3i);
    }
}
