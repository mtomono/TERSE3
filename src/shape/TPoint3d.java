/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package shape;

import collection.TList;
import java.util.function.Consumer;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author masao
 */
public class TPoint3d extends Point3d {
    static public TPoint3d zero = new TPoint3d(0, 0, 0);

    public TPoint3d() {
        super();
    }
    
    public TPoint3d(Tuple3d t) {
        super(t);
    }
    
    public TPoint3d(double x, double y, double z) {
        super(x, y, z);
    }

    public TPoint3d retval(Consumer<TPoint3d> consumer) {
        TPoint3d retval = new TPoint3d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TPoint3d self(Consumer<TPoint3d> consumer) {
        consumer.accept(this);
        return this;
    }
    
    public TPoint3d subR(Tuple3d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TPoint3d subS(Tuple3d v1) {
        return self(v->v.sub(v1));
    }
    
    public void sub(double x, double y, double z) {
        setX(this.x-x);
        setY(this.y-y);
        setZ(this.z-z);
    }
    
    public TPoint3d subR(double x, double y, double z) {
        return retval(v->v.sub(x, y, z));
    }
    
    public TPoint3d subS(double x, double y, double z) {
        return self(v->v.sub(x, y, z));
    }
    
    public TPoint3d addR(Tuple3d v1) {
        return retval(v->v.add(v1));
    }
    
    public TPoint3d addS(Tuple3d v1) {
        return self(v->v.add(v1));
    }
    
    public void add(double x, double y, double z) {
        setX(this.x+x);
        setY(this.y+y);
        setZ(this.z+z);
    }
    
    public TPoint3d addR(double x, double y, double z) {
        return retval(v->v.add(x, y, z));
    }
    
    public TPoint3d addS(double x, double y, double z) {
        return self(v->v.add(x, y, z));
    }
    
    public TPoint3d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TPoint3d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TPoint3d moveR(Vector3d move) {
        return retval(p->p.add(move));
    }
    
    public TPoint3d moveS(Vector3d move) {
        return self(p->p.add(move));
    }
    
    public TPoint3d interpolateR(TPoint3d to, double rate) {
        return retval(p->p.interpolate(to, rate));
    }
    
    public TPoint3d interpolateS(TPoint3d to, double rate) {
        return self(p->p.interpolate(to, rate));
    }
    
    public TVector3d to(Point3d to) {
        return TVector3d.c(this, to);
    }
    
    public TVector3d from(Point3d from) {
        return TVector3d.c(from, this);
    }
    
    public TPoint3d setXR(double v1) {
        return retval(v->v.setX(v1));
    }
    
    public TPoint3d setXS(double v1) {
        return self(v->v.setX(v1));
    }
    
    public TPoint3d setYR(double v1) {
        return retval(v->v.setY(v1));
    }
    
    public TPoint3d setYS(double v1) {
        return self(v->v.setY(v1));
    }
    
    public TPoint3d setZR(double v1) {
        return retval(v->v.setZ(v1));
    }
    
    public TPoint3d setZS(double v1) {
        return self(v->v.setZ(v1));
    }
    public TPoint3d transformR(Matrix3d m) {
        return retval(p->m.transform(p));
    }

    public TPoint3d transformS(Matrix3d m) {
        return self(p->m.transform(p));
    }
    
    public TPoint2d shrink() {
        return new TPoint2d(x, y);
    }

    public TPoint3d flip() {
        return new TPoint3d(y, x, z);
    }

    static public TPoint3d average(TList<? extends Tuple3d> ps) {
        return new TPoint3d(ps.averageD(p->p.x), ps.averageD(p->p.y), ps.averageD(p->p.z));
    }
}
