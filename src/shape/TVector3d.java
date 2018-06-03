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
import static collection.TList.toTList;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.vecmath.Matrix3d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author masao
 */
public class TVector3d extends Vector3d {
    static public TVector3d zero = new TVector3d(0, 0, 0);
    static public TVector3d x1 = new TVector3d(1, 0, 0);
    static public TVector3d y1 = new TVector3d(0, 1, 0);
    static public TVector3d z1 = new TVector3d(0, 0, 1);
    
    public TVector3d() {
        super();
    }
    
    public TVector3d(Tuple3d t) {
        super(t);
    }
    
    static public TVector3d c(Tuple3d start, Tuple3d end) {
        return new TVector3d(end).subS(start);
    }
    
    static public TVector3d x(double v) {
        return new TVector3d(v, 0, 0);
    }
    
    static public TVector3d y(double v) {
        return new TVector3d(0, v, 0);
    }
    
    static public TVector3d z(double v) {
        return new TVector3d(0, 0, v);
    }
    
    public TVector3d(double x, double y, double z) {
        super(x, y, z);
    }
    
    public TVector3d retval(Consumer<TVector3d> consumer) {
        TVector3d retval = new TVector3d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TVector3d self(Consumer<TVector3d> consumer) {
        consumer.accept(this);
        return this;
    }
    
    public TVector3d subR(Tuple3d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TVector3d subS(Tuple3d v1) {
        return self(v->v.sub(v1));
    }
    
    public void sub(double x, double y, double z) {
        setX(this.x-x);
        setY(this.y-y);
        setZ(this.z-z);
    }
    
    public TVector3d subR(double x, double y, double z) {
        return retval(v->v.sub(x, y, z));
    }
    
    public TVector3d subS(double x, double y, double z) {
        return self(v->v.sub(x, y, z));
    }
    
    public TVector3d addR(Tuple3d v1) {
        return retval(v->v.add(v1));
    }
    
    public TVector3d addS(Tuple3d v1) {
        return self(v->v.add(v1));
    }
    
    public void add(double x, double y, double z) {
        setX(this.x+x);
        setY(this.y+y);
        setZ(this.z+z);
    }
    
    public TVector3d addR(double x, double y, double z) {
        return retval(v->v.add(x, y, z));
    }
    
    public TVector3d addS(double x, double y, double z) {
        return self(v->v.add(x, y, z));
    }
    
    public TPoint3d moveFrom(TPoint3d point) {
        return point.moveR(this);
    }
    
    public TVector3d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TVector3d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TVector3d sizeR(double l) {
        return retval(v->v.scale(l/v.length()));
    }
    
    public TVector3d sizeS(double l) {
        return self(v->v.scale(l/v.length()));
    }
    
    public TVector3d extendR(double l) {
        return sizeR(length()+l);
    }
    
    public TVector3d extendS(double l) {
        return sizeS(length()+l);
    }
    
    public TVector3d negateR() {
        return retval(v->v.negate());
    }
    
    public TVector3d negateS() {
        return self(v->v.negate());
    }

    public Angle3d angleO(Vector3d end) {
        return new Angle3d(this, end);
    }
    
    public TVector3d cross(Vector3d v1) {
        return new TVector3d().retval(v->v.cross(this, v1));
    }
    
    public TVector3d normalizeR() {
        return retval(v->v.normalize());
    }

    public TVector3d normalizeS() {
        return self(v->v.normalize());
    }
    
    public TVector3d transformR(Matrix3d m) {
        return retval(v->m.transform(v));
    }

    public TVector3d transformS(Matrix3d m) {
        return self(v->m.transform(v));
    }
    
    public TVector3d setXR(double v1) {
        return retval(v->v.setX(v1));
    }
    
    public TVector3d setXS(double v1) {
        return self(v->v.setX(v1));
    }
    
    public TVector3d setYR(double v1) {
        return retval(v->v.setY(v1));
    }
    
    public TVector3d setYS(double v1) {
        return self(v->v.setY(v1));
    }
    
    public TVector3d setZR(double v1) {
        return retval(v->v.setZ(v1));
    }
    
    public TVector3d setZS(double v1) {
        return self(v->v.setZ(v1));
    }
    
    public TVector3d interpolateR(TVector3d to, double rate) {
        return retval(p->p.interpolate(to, rate));
    }
    
    public TVector3d interpolateS(TVector3d to, double rate) {
        return self(p->p.interpolate(to, rate));
    }
    
    public TVector2d shrink() {
        return new TVector2d(x, y);
    }
    
    public TVector3d flip() {
        return new TVector3d(y, x, z);
    }

    public TVector3d hypotenuseOf(TVector3d side) {
        return scaleR(side.length()/dot(side.normalizeR()));
    }
    
    public TVector3d theOtherSideOf(TVector3d side) {
        return hypotenuseOf(side).subR(side);
    }
    
    /**
     * Counterclockwise right angle rotation in righthanded system.
     * 
     * @return 
     */
    public TVector3d rotCcw() {
        return new TVector3d(-y, x, z);
    }
    
    /**
     * Clockwise right angle rotation in righthanded system.
     * 
     * @return 
     */
    public TVector3d rotCw() {
        return new TVector3d(y, -x, z);
    }
    
    public TVector3d rotZ(double angle) {
        return new TVector3d(x*cos(angle)-y*sin(angle), x*sin(angle)+y*cos(angle), z);
    }
    
    public TList<TVector3d> disrelative(TList<TVector3d> relativeSeq) {
        return relativeSeq.iterator().heap(this, (a,b)->a.addR(b)).stream().collect(toTList());
    }
        
    static public TList<TVector3d> relative(TList<TVector3d> concreteSeq) {
        return concreteSeq.diff((a,b)->b.subR(a));
    }
    
    public TList<TVector3d> quadrant(UnaryOperator<TVector3d> rot) {
        return Stream.iterate(this, rot).limit(4).collect(toTList());
    }
    
    static public TVector3d average(TList<? extends Tuple3d> ps) {
        return new TVector3d(TPoint3d.average(ps));
    }

    public String toCsv() {
        return Double.toString(x)+","+Double.toString(y)+","+Double.toString(z);
    }
}
