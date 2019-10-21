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

import static arithmetic.Calculation.roundAt;
import collection.TList;
import static collection.c.a2l;
import java.util.*;
import java.util.function.Consumer;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import static shape.ShapeUtil.point3;

/**
 *
 * @author masao
 */
public class TPoint3d extends Point3d implements List<Double> {
    final static public TPoint3d zero = new TPoint3d(0, 0, 0).lock();
    final static public Comparator<Point3d> xc = Comparator.<Point3d>comparingDouble(p->p.x);
    final static public Comparator<Point3d> yc = Comparator.<Point3d>comparingDouble(p->p.y);
    final static public Comparator<Point3d> zc = Comparator.<Point3d>comparingDouble(p->p.z);
    private boolean locked = false;

    public TPoint3d lock() {
        this.locked = true;
        return this;
    }

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
        if (locked)
            throw new RuntimeException("changed while locked");
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
    
    public TPoint3d roundR(int i) {
        return retval(p->{
            p.x=roundAt(p.x,i);
            p.y=roundAt(p.y,i);
            p.z=roundAt(p.z,i);
        });
    }
    
    public TPoint3d roundS(int i) {
        return self(p->{
            p.x=roundAt(p.x,i);
            p.y=roundAt(p.y,i);
            p.z=roundAt(p.z,i);
        });
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
    
    public String toCsv() {
        return Double.toString(x)+","+Double.toString(y)+","+Double.toString(z);
    }
    
    static public TPoint3d fromCsv(String line) {
        return point3(TList.sof(line.split(",")).map(i->Double.parseDouble(i)));
    }

    //--------- compatibility with list
    
    public List<Double> asList() {
        return a2l(x,y,z);
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Double))
            return false;
        Double t = (Double) o;
        return t==x||t==y||t==z;
    }

    @Override
    public Iterator<Double> iterator() {
        return asList().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Double[]{x,y,z};
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return asList().toArray(a);
    }

    @Override
    public boolean add(Double e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return asList().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Double> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends Double> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double get(int index) {
        switch (index) {
            case 0: return x;
            case 1: return y;
            case 2: return z;
            default: throw new NoSuchElementException("TVector3d#get index was "+index);
        }
    }

    @Override
    public Double set(int index, Double element) {
        switch (index) {
            case 0: {x=element;return x;}
            case 1: {y=element;return y;}
            case 2: {z=element;return z;}
            default: throw new NoSuchElementException("TVector3d#get index was "+index);
        }
    }

    @Override
    public void add(int index, Double element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        return asList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return asList().lastIndexOf(o);
    }

    @Override
    public ListIterator<Double> listIterator() {
        return asList().listIterator();
    }

    @Override
    public ListIterator<Double> listIterator(int index) {
        return asList().listIterator(index);
    }

    @Override
    public List<Double> subList(int fromIndex, int toIndex) {
        return asList().subList(fromIndex, toIndex);
    }
}
