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
import javax.vecmath.*;
import static shape.ShapeUtil.point2;

/**
 *
 * @author masao
 */
public class TPoint2d extends Point2d implements List<Double> {
    final static public TPoint2d zero = new TPoint2d(0, 0);
    final static public Comparator<Point2d> xc = Comparator.<Point2d>comparingDouble(p->p.x);
    final static public Comparator<Point2d> yc = Comparator.<Point2d>comparingDouble(p->p.y);

    public TPoint2d() {
        super();
    }
    
    public TPoint2d(Tuple2d t) {
        super(t);
    }
    
    public TPoint2d(Tuple2i t) {
        super(t.x,t.y);
    }
    
    public TPoint2d(double x, double y) {
        super(x, y);
    }
    
    public TPoint2d retval(Consumer<TPoint2d> consumer) {
        TPoint2d retval = new TPoint2d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TPoint2d self(Consumer<TPoint2d> consumer) {
        consumer.accept(this);
        return this;
    }

    public TPoint2d subR(Tuple2d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TPoint2d subS(Tuple2d v1) {
        return self(v->v.sub(v1));
    }
    
    public TPoint2d addR(Tuple2d v1) {
        return retval(v->v.add(v1));
    }
    
    public TPoint2d addS(Tuple2d v1) {
        return self(v->v.add(v1));
    }

    public TPoint2d addR(double x, double y) {
        return retval(v->v.add(x, y));
    }
    
    public TPoint2d addS(double x, double y) {
        return self(v->v.add(x, y));
    }
    
    public void add(double x, double y) {
        setX(this.x+x);
        setY(this.y+y);
    }
    
    public TPoint2d subR(double x, double y) {
        return retval(v->v.sub(x, y));
    }
    
    public TPoint2d subS(double x, double y) {
        return self(v->v.sub(x, y));
    }
    
    public void sub(double x, double y) {
        setX(this.x-x);
        setY(this.y-y);
    }

    public TPoint2d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TPoint2d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TPoint2d moveR(Vector2d move) {
        return retval(p->p.add(move));
    }
    
    public TPoint2d moveS(Vector2d move) {
        return self(p->p.add(move));
    }
    
    public TPoint2d interpolateR(TPoint2d to, double rate) {
        return retval(p->p.interpolate(to, rate));
    }
    
    public TPoint2d interpolateS(TPoint2d to, double rate) {
        return self(p->p.interpolate(to, rate));
    }
    
    public TVector2d to(Point2d to) {
        return TVector2d.c(this, to);
    }
    
    public TVector2d from(Point2d from) {
        return TVector2d.c(from, this);
    }
    
    public TPoint2d setXR(double v1) {
        return retval(v->v.setX(v1));
    }
    
    public TPoint2d setXS(double v1) {
        return self(v->v.setX(v1));
    }
    
    public TPoint2d setYR(double v1) {
        return retval(v->v.setY(v1));
    }
    
    public TPoint2d setYS(double v1) {
        return self(v->v.setY(v1));
    }

    public TPoint2d roundR(int i) {
        return retval(p->{
            p.x=roundAt(p.x,i);
            p.y=roundAt(p.y,i);
        });
    }
    
    public TPoint2d roundS(int i) {
        return self(p->{
            p.x=roundAt(p.x,i);
            p.y=roundAt(p.y,i);
        });
    }
    
    public TPoint3d expand() {
        return expand(0);
    }
    
    public TPoint3d expand(double z) {
        return new TPoint3d(x, y, z);
    }
    
    public TPoint2d flip() {
        return new TPoint2d(y, x);
    }
    
    static public TPoint2d average(TList<? extends Tuple2d> ps) {
        return new TPoint2d(ps.averageD(p->p.x), ps.averageD(p->p.y));
    }
    
    public String toCsv() {
        return Double.toString(x)+","+Double.toString(y);
    }

    static public TPoint2d fromCsv(String line) {
        return point2(TList.sof(line.split(",")).map(i->Double.parseDouble(i)));
    }


    //--------- compatibility with list
    
    public List<Double> asList() {
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
        if (!(o instanceof Double))
            return false;
        Double t = (Double) o;
        return t==x||t==y;
    }

    @Override
    public Iterator<Double> iterator() {
        return asList().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Double[]{x,y};
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
            default: throw new NoSuchElementException("TVector3d#get index was "+index);
        }
    }

    @Override
    public Double set(int index, Double element) {
        switch (index) {
            case 0: {x=element;return x;}
            case 1: {y=element;return y;}
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
