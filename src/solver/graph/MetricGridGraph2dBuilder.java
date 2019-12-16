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
public class MetricGridGraph2dBuilder {
    GridCoord gcoord;
    TList<TPoint2i> dirs;
    TVector3d weight;
    
    public static MetricGridGraph2dBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static MetricGridGraph2dBuilder builder(GridCoord gcoord) {
        return new MetricGridGraph2dBuilder(gcoord);
    }
    
    public MetricGridGraph2dBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().map(d->p2i(d)).sfix();
        this.weight=vector3(1,1,3);
    }
        
    public MetricGridGraph2dBuilder alt() {
        this.dirs=gcoord.dirsAlternate().map(d->p2i(d));
        return this;
    }
    
    public MetricGridGraph2dBuilder weight(double... weight) {
        this.weight=vector3(wrap(weight));
        return this;
    }
    
    public MetricGridGraph<TPoint2i> build() {
        return new MetricGridGraph<>(gcoord,dirs,weight,ShapeUtil::p2i);
    }
}
