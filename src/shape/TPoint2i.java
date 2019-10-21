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
import javax.vecmath.Point2i;
import javax.vecmath.Tuple2i;
import static shape.ShapeUtil.p2i;

/**
 *
 * @author masao
 */
public class TPoint2i extends Point2i implements List<Integer> {
    final static public TPoint2i zero = new TPoint2i(0, 0).lock();
    final static public TPoint2i x1 = new TPoint2i(1, 0).lock();
    final static public TPoint2i y1 = new TPoint2i(0, 1).lock();
    final static public Comparator<Point2i> xc = Comparator.<Point2i>comparingInt(p->p.x);
    final static public Comparator<Point2i> yc = Comparator.<Point2i>comparingInt(p->p.y);
    private boolean locked = false;

    public TPoint2i lock() {
        this.locked = true;
        return this;
    }

    public TPoint2i() {
        super();
    }
    
    public TPoint2i(Tuple2i t) {
        super(t);
    }
    
    public TPoint2i(int x, int y) {
        super(x, y);
    }
    
    public static TPoint2i c(Tuple2i start, Tuple2i end) {
        return new TPoint2i(end).self(p->p.sub(start));
    }
    
    public TPoint2i retval(Consumer<TPoint2i> consumer) {
        TPoint2i retval = new TPoint2i(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TPoint2i self(Consumer<TPoint2i> consumer) {
        if (locked)
            throw new RuntimeException("changed while locked");
        consumer.accept(this);
        return this;
    }

    public TPoint2i to(TPoint2i to) {
        return TPoint2i.c(this, to);
    }
    
    public TPoint2i from(TPoint2i from) {
        return TPoint2i.c(from, this);
    }
    
    public TPoint2i addR(Tuple2i v1) {
        return retval(v->v.add(v1));
    }
    
    public TPoint2i addS(Tuple2i v1) {
        return self(v->v.add(v1));
    }
    
    public TPoint2i subR(Tuple2i v1) {
        return retval(v->v.sub(v1));
    }
    
    public TPoint2i subS(Tuple2i v1) {
        return self(v->v.sub(v1));
    }
    
    public TPoint2i scaleR(int v1) {
        return retval(v->v.scale(v1));
    }
    
    public TPoint2i scaleS(int v1) {
        return self(v->v.scale(v1));
    }
    
    public TVector2d scaleR(double scale) {
        return new TVector2d(x,y).scaleS(scale);
    }
    
    public TPoint2i negateR() {
        return retval(v->v.negate());
    }
    
    public TPoint2i negateS() {
        return self(v->v.negate());
    }
    
    public int det(Tuple2i other) {
        return x*other.y-other.x*y;
    }
    
    public TPoint2i signum() {
        return new TPoint2i(Integer.signum(x), Integer.signum(y));
    }
    
    public TPoint2i flip() {
        return new TPoint2i(y, x);
    }
    
    public int manhattanLength() {
        return abs(x)+abs(y);
    }
    
    public TPoint3i expandX(int X) {
        return new TPoint3i(X, x, y);
    }
    
    public TPoint3i expandY(int Y) {
        return new TPoint3i(y, Y, x);
    }
    
    public TPoint3i expandZ(int Z) {
        return new TPoint3i(x, y, Z);
    }
    
    /**
     * judge the quadrant.
     * only applicable to unit point.
     * 0:x+y+ 1:x-y+ 2:x-y- 3:x+y-
     * @return 
     */
    public int quadrant() {
        assert abs(x+y)==1 : "when quadrant is called, abs(x+y) is assumed to be 1 but "+this;
        assert x*y==0 : "when quadrant is called, x*y is assumed to be 0 but "+this;
        return 3-abs(x*2+y+1);
    }
    
    final static public TList<TPoint2i> quadrants = TList.sof(
            new TPoint2i(1, 0),
            new TPoint2i(0, 1),
            new TPoint2i(-1,0),
            new TPoint2i(0,-1)
    );
    static TPoint2i quadrant(int i) {
        return quadrants.get(i);
    }

    public String toCsv() {
        return Integer.toString(x)+","+Integer.toString(y);
    }

    static public TPoint2i fromCsv(String line) {
        return p2i(TList.sof(line.split(",")).map(i->Integer.parseInt(i)));
    }

    //--------- compatibility with list
    
    public List<Integer> asList() {
        return a2l(x,y);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer))
            return false;
        Integer t = (Integer) o;
        return t==x||t==y;
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
            default: throw new NoSuchElementException("TVector3d#get index was "+index);
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
        return "("+x+","+y+")";
    }
}
