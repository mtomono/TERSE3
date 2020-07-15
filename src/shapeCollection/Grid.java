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

package shapeCollection;

import collection.TList;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.Integer.signum;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import orderedSet.Range;
import shape.TPoint2i;

/**
 *
 * @author masao
 * @param <T>
 */
public class Grid<T> implements Cloneable {
    final public TPoint2i from;
    final public TPoint2i to;
    final public TPoint2i togo;
    final public TPoint2i sigTogo;
    final public TList<Integer> x;
    final public TList<Integer> y;
    final public Range<Integer> xr;
    final public Range<Integer> yr;
    final public TList<TList<T>> space;
    
    static public <T> Grid<T> c(TPoint2i from, TPoint2i to, Collection<TPoint2i> blocked, T safe, T block) {
        return new Grid<>(from, to, (a,b)->safe).mark(blocked, p->block);
    }
    
    public Grid(TPoint2i from, TPoint2i to, BiFunction<Integer, Integer, T> f) {
        this.from = from;
        this.to = to;
        this.x = TList.rangeSym(from.x, to.x);
        this.y = TList.rangeSym(from.y, to.y);
        this.xr = new Range<>(min(from.x, to.x), max(from.x, to.x)+1);
        this.yr = new Range<>(min(from.y, to.y), max(from.y, to.y)+1);
        this.togo = from.to(to);
        this.sigTogo = new TPoint2i(signum(togo.x), signum(togo.y));
        this.space = y.map(n->x.map(m->f.apply(m,n)).sfix()).sfix();
    }
    
    public Grid(TPoint2i from, TPoint2i to, TList<T> l) {
        this(from,to,(a,b)->l.get((a-from.x)+(b-from.y)*(to.x-from.x+1)));
        assert l.size() >= (to.x-from.x+1)*(to.y-from.y+1);
    }
    
    public Grid(int x, int y) {
        this(TPoint2i.zero, new TPoint2i(x,y), (xx,yy)->null);
    }
        
    Grid(TPoint2i from, TPoint2i to, TList<Integer> x, TList<Integer> y, Range<Integer> xr, Range<Integer> yr, TPoint2i togo, TPoint2i sigTogo, TList<TList<T>> space) {
        this.from = from;
        this.to = to;
        this.x = x;
        this.y = y;
        this.xr = xr;
        this.yr = yr;
        this.togo = togo;
        this.sigTogo = sigTogo;
        this.space = space;
    }
    
    /**
     * trim this grid to new one.
     * 
     * @param from
     * @param to
     * @param f
     * @return 
     */
    public Grid<T> trim(TPoint2i from, TPoint2i to, BiFunction<Integer, Integer, T> f) {
        return new Grid<>(from, to, (a,b)->contains(a,b)?get(a,b):f.apply(a,b));
    }
    
    public Grid<T> mark(Collection<TPoint2i> points, Function<TPoint2i, T> f) {
        points.forEach(p->Grid.this.cset(p, f.apply(p)));
        return this;
    }
    
    public Grid<T> copy() {
        Grid<T> g = null;
        try {
            g = clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }
        
    @Override
    public Grid<T> clone() throws CloneNotSupportedException {
        super.clone();
        Grid<T> retval = new Grid<>(from, to, (a,b)->null);
        y.forEach(yy->retval.setY(yy, getY(yy)));
        return retval;
    }
    
    int xInList(int px) {
        assert xr.contains(px) : "px out of range:"+px+" :"+xr;
        return (px - x.get(0))*sigTogo.x;
    }
    
    int yInList(int py) {
        assert yr.contains(py) : "py out of range:"+py+" :"+yr;
        return (py - y.get(0))*sigTogo.y;
    }
    
    public T get(TPoint2i at) {
        return get(at.x, at.y);
    }

    public T get(int x, int y) {
        return space.get(yInList(y)).get(xInList(x));
    }
    
    /**
     * c(hained)set.
     * set method for chained method.
     * @param at
     * @param v
     * @return 
     */
    public Grid<T> cset(TPoint2i at, T v) {
        return cset(at.x, at.y, v);
    }
    
    /**
     * c(hained)set.
     * set method for chained method.
     * @param x
     * @param y
     * @param v
     * @return 
     */
    public Grid<T> cset(int x, int y, T v) {
        set(x,y,v);
        return this;
    }
    
    public T set(TPoint2i at, T v) {
        return set(at.x, at.y, v);
    }
    
    public T set(int x, int y, T v) {
        space.get(yInList(y)).set(xInList(x), v);
        return v;
    }
    
    public TList<T> getY(int y) {
        return space.get(yInList(y));
    }
    
    public TList<TList<T>> getY() {
        return y.map(i->getY(i));
    }
    
    public Grid<T> setY(int y, TList<T> l) {
        assert l.size() == x.size();
        x.forEach(xx->space.get(yInList(y)).set(xInList(xx), l.get(xInList(xx))));
        return this;
    }
    
    public TList<T> getX(int x) {
        return space.map(l->l.get(xInList(x)));
    }
    public TList<TList<T>> getX() {
        return x.map(i->getX(i));
    }
    
    public Grid<T> setX(int x, TList<T>l) {
        assert l.size() == y.size();
        y.forEach(yy->space.get(yInList(yy)).set(xInList(x), l.get(yInList(yy))));
        return this;
    }
    
    public Grid<T> set(Grid<T> other) {
        x.forEach(xx->y.forEach(yy->Grid.this.cset(new TPoint2i(xx,yy), other.get(xx,yy))));
        return this;
    }
    
    public Grid<T> shift(TPoint2i shift) {
        return new Grid<>(from.addR(shift), to.addR(shift), (a,b)->get(new TPoint2i(a,b).subS(shift)));
    }
    
    public <S> Grid<S> map(Function<T, S> f) {
        return new Grid<>(from, to, (a,b)->f.apply(get(a,b)));
    }
    
    public <S> Grid<S> mapByPosition(BiFunction<TPoint2i, T, S> f) {
        return new Grid<>(from, to, (a,b)->f.apply(new TPoint2i(a,b), get(a,b)));
    }
    /**
     * point list.
     * listing all the points.
     * @return 
     */
    public TList<TPoint2i> plist() {
        return x.cross(y, (a,b)->new TPoint2i(a,b));
    }
    
    /**
     * flatten to list.
     * listing all items along the all points list.
     * @return 
     */
    public TList<T> flat() {
        return plist().map(p->get(p));
    }
    
    public TList<TPoint2i> pfilter(Predicate<T> pred) {
        return plist().filter(p->pred.test(get(p)));
    }
    
    public TList<T> filter(Predicate<T> pred) {
        return flat().filter(pred);
    }
    
    public <S> Grid<S> pair(Grid<T> other, BiFunction<T, T, S> f) {
        return new Grid<>(from, to, (a,b)->f.apply(get(a,b),other.get(a,b)));
    }
    
    public Grid<T> rotateX(int diff) {
        final Grid<T> retval = copy();
        x.pair(x.rotate(diff)).stream().forEach(p->retval.setX(p.l(), getX(p.r())));
        return retval;
    }
    
    public Grid<T> rotateY(int diff) {
        final Grid<T> retval = copy();
        y.pair(y.rotate(diff)).stream().forEach(p->retval.setY(p.l(), getY(p.r())));
        return retval;
    }
    
    public Grid<T> pushHeadX(TList<T> pushed) {
        return rotateX(-1).setX(x.get(0), pushed);
    }
    
    public Grid<T> pushTailX(TList<T> pushed) {
        return rotateX(1).setX(x.last(), pushed);
    }
    
    public Grid<T> pushHeadY(TList<T> pushed) {
        return rotateY(-1).setY(y.get(0), pushed);
    }
    
    public Grid<T> pushTailY(TList<T> pushed) {
        return rotateY(1).setY(y.last(), pushed);
    } 
    
    public Grid<T> reverseY() {
        return new Grid<>(new TPoint2i(from.x, to.y), new TPoint2i(to.x, from.y), x, y.reverse(), xr, yr, new TPoint2i(togo.x, -togo.y), new TPoint2i(sigTogo.x, -sigTogo.y), space.reverse().sfix());
    }

    public Grid<T> reverseX() {
        return new Grid<>(new TPoint2i(to.x, from.y), new TPoint2i(from.x, to.y), x.reverse(), y, xr, yr, new TPoint2i(-togo.x, togo.y), new TPoint2i(-sigTogo.x, sigTogo.y), space.map(l->l.reverse().sfix()));
    }
    
    public Grid<T> reverseXY() {
        return reverseX().reverseY();
    }
    
    public Grid<T> toRightHandedSystem() {
        Grid<T> retval = copy();
        if (sigTogo.x < 0)
            retval = retval.reverseX();
        if (sigTogo.y > 0)
            retval = retval.reverseY();
        return retval;
    }
    
    public Grid<T> flip() {
        return new Grid<>(from.flip(), to.flip(), y,x,yr,xr, togo.flip(),sigTogo.flip(),space.transposeT(l->l));
    }
    
    public boolean contains(int x, int y) {
        return xr.contains(x)&&yr.contains(y);
    }
    
    public boolean contains(TPoint2i p) {
        return contains(p.x, p.y);
    }
    
    public T computeIfNull(int x, int y, BiFunction<Integer, Integer, T> f) {
        T old = get(x,y);
        if (old != null)
            return old;
        return set(x,y,f.apply(x,y));
    }
    
    public T computeIfNull(TPoint2i p, Function<TPoint2i,T> f) {
        return computeIfNull(p.x,p.y,(xx,yy)->f.apply(new TPoint2i(xx,yy)));
    }
    
    public String toFlatTestString() {
        return getY().map(l->l.toFlatString()).toWrappedString();
    }
    
    public String toDelimitedTestString() {
        return getY().map(l->l.toDelimitedString(",")).toWrappedString();
    }
    
    public String toString(Function<T,String> f) {
        return space.map(l->l.toFlatString()).toWrappedString();
    }
    
    public String dump() {
        return "from:"+from+"\n"
        +"to:"+to+"\n"
        +"x:"+x+"\n"
        +"y:"+y+"\n"
        +"xr:"+xr+"\n"
        +"yr:"+yr+"\n"
        +"togo:"+togo+"\n"
        +"sigTogo"+sigTogo+"\n"
        +"space:"+space.toWrappedString();
    }
    
    @Override
    public String toString() {
        return toString(T::toString);
    }
    
    @Override
    public boolean equals(Object e) {
        if (!(e instanceof Grid))
            return false;
        Grid<T> t = (Grid<T>) e;
        return getY().equals(t.getY());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79*hash+Objects.hashCode(this.from);
        hash = 79*hash+Objects.hashCode(this.to);
        hash = 79*hash+Objects.hashCode(this.space);
        return hash;
    }
}
