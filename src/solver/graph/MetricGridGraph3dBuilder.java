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
import static shape.ShapeUtil.p3i;
import static shape.ShapeUtil.vector3;
import shape.TPoint3i;
import shape.TVector3d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class MetricGridGraph3dBuilder {
    GridCoord gcoord;
    TList<TPoint3i> dirs;
    TVector3d weight;
    
    public static MetricGridGraph3dBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static MetricGridGraph3dBuilder builder(GridCoord gcoord) {
        return new MetricGridGraph3dBuilder(gcoord);
    }
    
    public MetricGridGraph3dBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().map(ShapeUtil::p3i);
        this.weight=vector3(1,1,3);
    }
        
    public MetricGridGraph3dBuilder alt() {
        this.dirs=gcoord.dirsAlternate().map(d->p3i(d));
        return this;
    }
    
    public MetricGridGraph3dBuilder weight(double... weight) {
        this.weight=vector3(wrap(weight));
        return this;
    }
    
    public MetricGridGraph<TPoint3i> build() {
        return new MetricGridGraph<>(gcoord,dirs,weight,ShapeUtil::p3i);
    }
}
