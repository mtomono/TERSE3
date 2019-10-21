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
import static collection.c.a2l;
import static java.lang.Math.abs;
import java.util.*;
import java.util.function.Consumer;
import javax.vecmath.Point3i;
import javax.vecmath.Tuple3i;
import static shape.ShapeUtil.p3i;

/**
 *
 * @author masao
 */
public class TPoint3i extends Point3i implements List<Integer> {
    final static public TPoint3i zero = new TPoint3i(0, 0, 0).lock();
    final static public TPoint3i x1 = new TPoint3i(1, 0, 0).lock();
    final static public TPoint3i y1 = new TPoint3i(0, 1, 0).lock();
    final static public TPoint3i z1 = new TPoint3i(0, 0, 1).lock();
    final static public Comparator<Point3i> xc = Comparator.<Point3i>comparingInt(p->p.x);
    final static public Comparator<Point3i> yc = Comparator.<Point3i>comparingInt(p->p.y);
    final static public Comparator<Point3i> zc = Comparator.<Point3i>comparingInt(p->p.z);
    private boolean locked = false;

    public TPoint3i lock() {
        this.locked = true;
        return this;
    }

    public TPoint3i() {
        super();
    }
    
    public TPoint3i(Tuple3i t) {
        super(t);
    }
    
    public TPoint3i(int x, int y, int z) {
        super(x, y, z);
    }
    
    public static TPoint3i c(Tuple3i start, Tuple3i end) {
        return new TPoint3i(end).self(p->p.sub(start));
    }
    
    public TPoint3i retval(Consumer<TPoint3i> consumer) {
        TPoint3i retval = new TPoint3i(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TPoint3i self(Consumer<TPoint3i> consumer) {
        if (locked)
            throw new RuntimeException("changed while locked");
        consumer.accept(this);
        return this;
    }

    public TPoint3i to(TPoint3i to) {
        return TPoint3i.c(this, to);
    }
    
    public TPoint3i from(TPoint3i from) {
        return TPoint3i.c(from, this);
    }
    
    public TPoint3i addR(Tuple3i v1) {
        return retval(v->v.add(v1));
    }
    
    public TPoint3i addS(Tuple3i v1) {
        return self(v->v.add(v1));
    }
    
    public TPoint3i subR(Tuple3i v1) {
        return retval(v->v.sub(v1));
    }
    
    public TPoint3i subS(Tuple3i v1) {
        return self(v->v.sub(v1));
    }
    
    public TPoint3i scaleR(int v1) {
        return retval(v->v.scale(v1));
    }
    
    public TPoint3i scaleS(int v1) {
        return self(v->v.scale(v1));
    }
    
    public TVector3d scaleR(double scale) {
        return new TVector3d(x,y,z).scaleS(scale);
    }
    
    public TPoint3i negateR() {
        return retval(v->v.negate());
    }
    
    public TPoint3i negateS() {
        return self(v->v.negate());
    }
    
    public TVector3d asVector() {
        return new TVector3d(x,y,z);
    }
    
    public TVector3d det(TPoint3i other) {
        return asVector().cross(other.asVector());
    }
    
    public TPoint3i signum() {
        return new TPoint3i(Integer.signum(x), Integer.signum(y), Integer.signum(z));
    }
    
    public TPoint2i shrinkX() {
        return new TPoint2i(y,z);
    }
    
    public TPoint2i shrinkY() {
        return new TPoint2i(z,x);
    }
    
    public TPoint2i shrinkZ() {
        return new TPoint2i(x,y);
    }
    
    public int manhattanLength() {
        return abs(x)+abs(y)+abs(z);
    }
    
    public String toCsv() {
        return Integer.toString(x)+","+Integer.toString(y)+","+Integer.toString(z);
    }
    
    static public TPoint3i fromCsv(String line) {
        return p3i(TList.sof(line.split(",")).map(i->Integer.parseInt(i)));
    }
    
    //--------- compatibility with list
    
    public List<Integer> asList() {
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
        if (!(o instanceof Integer))
            return false;
        Double t = (Double) o;
        return t==x||t==y||t==z;
    }

    @Override
    public Iterator<Integer> iterator() {
        return asList().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Integer[]{x,y};
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return asList().toArray(a);
    }

    @Override
    public boolean add(Integer e) {
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
    public boolean addAll(Collection<? extends Integer> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
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
    public Integer get(int index) {
        switch (index) {
            case 0: return x;
            case 1: return y;
            case 2: return z;
            default: throw new NoSuchElementException("TPoint3i#get index was "+index);
        }
    }

    @Override
    public Integer set(int index, Integer element) {
        switch (index) {
            case 0: {x=element;return x;}
            case 1: {y=element;return y;}
            default: throw new NoSuchElementException("TVector3d#get index was "+index);
        }
    }

    @Override
    public void add(int index, Integer element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer remove(int index) {
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
    public ListIterator<Integer> listIterator() {
        return asList().listIterator();
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return asList().listIterator(index);
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return asList().subList(fromIndex, toIndex);
    }
    
    @Override
    public String toString() {
        return "("+x+","+y+","+z+")";
    }
}
