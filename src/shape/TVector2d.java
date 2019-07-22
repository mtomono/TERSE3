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
import static collection.TList.toTList;
import static collection.c.a2l;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.vecmath.*;
import static shape.ShapeUtil.vector2;

/**
 *
 * @author masao
 */
public class TVector2d extends Vector2d implements List<Double> {
    final static public TVector2d zero = new TVector2d(0, 0);
    final static public Comparator<Vector2d> xc = Comparator.<Vector2d>comparingDouble(p->p.x);
    final static public Comparator<Vector2d> yc = Comparator.<Vector2d>comparingDouble(p->p.y);

    public TVector2d() {
        super();
    }
    
    public TVector2d(Tuple2d t) {
        super(t);
    }
    
    public TVector2d(Tuple2i t) {
        super(t.x, t.y);
    }
    
    public TVector2d(double x, double y) {
        super(x, y);
    }
    
    public static TVector2d c(Tuple2d start, Tuple2d end) {
        return new TVector2d(end).self(p->p.sub(start));
    }
    
    public TVector2d retval(Consumer<TVector2d> consumer) {
        TVector2d retval = new TVector2d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TVector2d self(Consumer<TVector2d> consumer) {
        consumer.accept(this);
        return this;
    }

    public TVector2d subR(Tuple2d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TVector2d subS(Tuple2d v1) {
        return self(v->v.sub(v1));
    }
    
    public TVector2d addR(Tuple2d v1) {
        return retval(v->v.add(v1));
    }
    
    public TVector2d addS(Tuple2d v1) {
        return self(v->v.add(v1));
    }
    
    public TVector2d addR(double x, double y) {
        return retval(v->v.add(x, y));
    }
    
    public TVector2d addS(double x, double y) {
        return self(v->v.add(x, y));
    }
    
    public void add(double x, double y) {
        setX(this.x+x);
        setY(this.y+y);
    }
    
    public TVector2d subR(double x, double y) {
        return retval(v->v.sub(x, y));
    }
    
    public TVector2d subS(double x, double y) {
        return self(v->v.sub(x, y));
    }
    
    public void sub(double x, double y) {
        setX(this.x-x);
        setY(this.y-y);
    }
    
    public TPoint2d moveFrom(TPoint2d point) {
        return point.moveR(this);
    }
    
    public TVector2d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TVector2d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TVector2d sizeR(double l) {
        return retval(v->v.scale(l/v.length()));
    }
    
    public TVector2d sizeS(double l) {
        return self(v->v.scale(l/v.length()));
    }
    
    public TVector2d negateR() {
        return retval(v->v.negate());
    }
    
    public TVector2d negateS() {
        return self(v->v.negate());
    }

    public Angle2d angleO(Vector2d end) {
        return new Angle2d(this, end);
    }
    
    public double det(Vector2d v1) {
        return x*v1.y-v1.x*y;
    }
    
    public TVector2d normalizeR() {
        return retval(v->v.normalize());
    }

    public TVector2d normalizeS() {
        return self(v->v.normalize());
    }
    
    public TVector2d setXR(double v1) {
        return retval(v->v.setX(v1));
    }
    
    public TVector2d setXS(double v1) {
        return self(v->v.setX(v1));
    }
    
    public TVector2d setYR(double v1) {
        return retval(v->v.setY(v1));
    }
    
    public TVector2d setYS(double v1) {
        return self(v->v.setY(v1));
    }
    
    public TVector2d interpolateR(TVector2d to, double rate) {
        return retval(p->p.interpolate(to, rate));
    }
    
    public TVector2d interpolateS(TVector2d to, double rate) {
        return self(p->p.interpolate(to, rate));
    }
    
    public TVector2d roundR(int i) {
        return retval(p->{
            p.x=roundAt(p.x,i);
            p.y=roundAt(p.y,i);
        });
    }
    
    public TVector2d roundS(int i) {
        return self(p->{
            p.x=roundAt(p.x,i);
            p.y=roundAt(p.y,i);
        });
    }
    
    public TVector3d expand() {
        return expand(0);
    }
    
    public TVector3d expand(double z) {
        return new TVector3d(x, y, z);
    }
    
    public TVector2d rotCcw() {
        return new TVector2d(-y, x);
    }
    
    public TVector2d rotCw() {
        return new TVector2d(y, -x);
    }
    
    public TVector2d rot(double angle) {
        return new TVector2d(x*cos(angle)-y*sin(angle), x*sin(angle)+y*cos(angle));
    }
    
    public TVector2d hypotenuseOf(TVector2d side) {
        return scaleR(side.length()/dot(side.normalizeR()));
    }
    
    public TVector2d theOtherSideOf(TVector2d side) {
        return hypotenuseOf(side).subR(side);
    }
    
    public TVector2d flip() {
        return new TVector2d(y, x);
    }
    
    public TList<TVector2d> disrelative(TList<TVector2d> relativeSeq) {
        return relativeSeq.iterator().accum(this, (a,b)->a.addR(b)).stream().collect(toTList());
    }
        
    static public TList<TVector2d> relative(TList<TVector2d> concreteSeq) {
        return concreteSeq.diff((a,b)->b.subR(a));
    }
        
    public TList<TVector2d> quadrant(UnaryOperator<TVector2d> rot) {
        return Stream.iterate(this, rot).limit(4).collect(toTList());
    }
    static public TVector2d average(TList<? extends Tuple2d> ps) {
        return new TVector2d(TPoint2d.average(ps));
    }

    public String toCsv() {
        return Double.toString(x)+","+Double.toString(y);
    }

    static public TVector2d fromCsv(String line) {
        return vector2(TList.sof(line.split(",")).map(i->Double.parseDouble(i)));
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
