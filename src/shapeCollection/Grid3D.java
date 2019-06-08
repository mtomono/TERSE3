/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.Integer.signum;
import java.util.function.Function;
import orderedSet.Range;
import shape.TPoint2i;
import shape.TPoint3i;

/**
 *
 * @author masao
 */
public class Grid3D<T> implements Cloneable {
    final public TPoint3i from;
    final public TPoint3i to;
    final public TPoint3i togo;
    final public TPoint3i sigTogo;
    final public TList<Integer> x;
    final public TList<Integer> y;
    final public TList<Integer> z;
    final public Range<Integer> xr;
    final public Range<Integer> yr;
    final public Range<Integer> zr;
    final public TList<TList<TList<T>>> space;
    
    @FunctionalInterface
    public interface TripleFunction<X,Y,Z,W> {
        public W apply(X x, Y y, Z z);
    }

    public Grid3D(TPoint3i from, TPoint3i to, TripleFunction<Integer,Integer,Integer, T> f) {
        this.from = from;
        this.to = to;
        this.x = TList.rangeSym(from.x, to.x);
        this.y = TList.rangeSym(from.y, to.y);
        this.z = TList.rangeSym(from.z, to.z);
        this.xr = new Range<>(min(from.x, to.x), max(from.x, to.x)+1);
        this.yr = new Range<>(min(from.y, to.y), max(from.y, to.y)+1);
        this.zr = new Range<>(min(from.z, to.z), max(from.z, to.z)+1);
        this.togo = from.to(to);
        this.sigTogo = new TPoint3i(signum(togo.x), signum(togo.y), signum(togo.z));
        this.space = z.map(n->y.map(m->x.map(l->f.apply(l,m,n)).sfix()).sfix()).sfix();
    }
    
    public Grid3D(TPoint3i from, TPoint3i to, TList<T> l) {
        this(from,to,(a,b,c)->l.get((a-from.x)+(b-from.y)*(to.x-from.x+1)+(c-from.z)*(to.x-from.x+1)*(to.y-from.y+1)));
        assert l.size() >= (to.x-from.x+1)*(to.y-from.y+1);
    }
    
}
