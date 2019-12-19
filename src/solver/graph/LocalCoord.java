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

import collection.TList;
import java.util.List;
import static shape.ShapeUtil.point3;
import static shape.ShapeUtil.vector3;
import shape.TMatrix3d;
import shape.TPoint3d;
import shape.TVector3d;
import shapeCollection.Cubes;

/**
 *
 * @author masao
 */
public class LocalCoord {
    final public TList<TVector3d> bases;
    final public TPoint3d origin;
    final public TMatrix3d globalize;
    final public TMatrix3d localize;
    final public Cubes cubes;
    public LocalCoord(TList<TVector3d> bases, TPoint3d origin) {
        this.bases=bases;
        this.origin=origin;
        this.globalize=TMatrix3d.coordinateTransform(bases.get(0), bases.get(1), bases.get(2));
        this.localize=globalize.invertR();
        this.cubes = new Cubes(vector3(-0.5,-0.5,-0.5), vector3(1d,1d,1d));
    }
    
    public TVector3d localize(TVector3d v) {
        return localize.transformToVector(v);
    }

    public TPoint3d localize(TPoint3d p) {
        return localize.transformToPoint(p.subR(origin));
    }
    
    public TVector3d globalize(TVector3d v) {
        return globalize.transformToVector(v);
    }
    
    public TPoint3d globalize(TPoint3d p) {
        return globalize.transformToPoint(p).addR(origin);
    }
    
    public TPoint3d globalize(List<Integer> p) {
        return globalize(point3(TList.set(p).map(i->i.doubleValue())));
    }
    
    public List<Integer> round(List<Double> point) {
        return TList.set(point).map(v->(int)Math.round(v)).sfix();
    }
}
