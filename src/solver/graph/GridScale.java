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
import static math.VectorOp.round;
import static shape.ShapeUtil.point3;
import static shape.ShapeUtil.vector3;
import shape.TMatrix3d;
import shape.TPoint2d;
import shape.TPoint3d;
import shape.TVector3d;
import static shape.TVector3d.x1;
import static shape.TVector3d.y1;
import static shape.TVector3d.z1;
import static shape.TPoint3d.zero;
import shape.TVector2d;
import shapeCollection.Cubes;

/**
 * Core Scale of Grid system.
 * very basic coordination system. also, divides spece into cubes.
 * everything is in blinded manner, meaning, doesn't know where to stop.
 * @author masao
 */
public class GridScale {
    static public GridScale ortho = new GridScale(TList.sof(x1,y1,z1),zero);
    final public TList<TVector3d> basis;
    final public TPoint3d origin;
    final public TMatrix3d globalize;
    final public TMatrix3d localize;
    final public Cubes cubes;
    
    static public GridScale c2d(TList<TVector2d> basis, TPoint2d origin) {
        return new GridScale(basis.map(v->v.expand()).append(z1),origin.expand());
    }
    static public GridScale c2d(TVector2d x, TVector2d y, TPoint2d origin) {
        return c2d(TList.sof(x,y),origin);
    }
    public GridScale(TList<TVector3d> basis, TPoint3d origin) {
        assert !basis.isEmpty() : "bases cannot be empty";
        this.basis=basis;
        this.origin=origin;
        this.globalize=TMatrix3d.coordinateTransform(basis.get(0), basis.get(1), basis.get(2));
        this.localize=globalize.invertR();
        this.cubes = new Cubes(vector3(-0.5,-0.5,-0.5), vector3(1d,1d,1d));
    }
    
    
    public boolean is2d() {
        return basis.get(0).z==0&&basis.get(1).z==0&&basis.get(2).x==0&&basis.get(2).y==0;
    }
    
    public TVector3d localize(TVector3d v) {
        return localize.transformToVector(v);
    }

    public TPoint3d localize(TPoint3d p) {
        return localize.transformToPoint(p.subR(origin));
    }
    
    public List<Integer> localizeIntoCube(TPoint3d p) {
        return round(localize(p));
    }
    
    public TList<TPoint3d> localize(TList<TPoint3d> globalLine) {
        return globalLine.map(x->localize(x));
    }
    
    public TVector3d globalize(TVector3d v) {
        return globalize.transformToVector(v);
    }
    
    public TPoint3d globalize(TPoint3d p) {
        return globalize.transformToPoint(p).addR(origin);
    }
    
    public TPoint3d globalizeD(List<Double> p) {
        return globalize(point3(p));
    }
    public TPoint3d globalizeI(List<Integer> p) {
        return globalizeD(TList.set(p).map(i->i.doubleValue()));
    }
        
    public TList<List<Integer>> toCube(TList<TPoint3d> localLine) {
        return localLine.diff((a,b)->toCube(a,b)).sfix().flatMap(p->p).sfix();
    }
    
    public TList<List<Integer>> toCube(TPoint3d localFrom, TPoint3d localTo) {
        if (GridMetric.l1d().i().measure(round(localTo),round(localFrom))<1)
            return TList.wrap(round(localTo));
        return cubes.go(localFrom, localTo).sfix();
    }
    
    @Override
    public String toString() {
        return basis.toString()+":"+origin;
    }
}
