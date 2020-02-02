/*
 Copyright 2017, 2018, 2019 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
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
    
    public MetricGridGraphOld<TPoint2i> build() {
        return new MetricGridGraphOld<>(gcoord,dirs,weight,ShapeUtil::p2i);
    }
}
