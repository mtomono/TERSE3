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

import static arithmetic.Arithmetic.mod;
import collection.TList;
import static function.ComparePolicy.inc;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import shape.PolygonC2d.Edge;
import solver.graph.GridScale;
import static solver.graph.GridScale.ortho;

/**
 * Polygon Closed 2d.
 * PolygonC2d doesn't contain the last point.
 * in that point, the representation of this shape is different from that of Polygon2d.
 * @author masao
 */
public class PolygonC2d extends TList<TPoint2d> {
    public static PolygonC2d c(List<TPoint2d> vertices) {
        assert !vertices.isEmpty();
        return new PolygonC2d(vertices);
    }
    
    public static PolygonC2d c(TPoint2d... vertices) {
        return c(TList.of(vertices));
    }
    
    PolygonC2d(List<TPoint2d> vertices) {
        super(vertices);
    }
    
    public TPoint2d rget(int p) {
        return get(rindex(p));
    }
    
    public int rindex(int p) {
        return mod.o(p,size());
    }
    
    public Polygon2d toPolygon() {
        return Polygon2d.c(this.append(get(0)));
    }
    
    public TList<List<Integer>> digitizeEdges(GridScale grid, double v) {
        return toPolygon().digitize(grid,v);
    }
    
    public TList<List<Integer>> digitizeEdges(GridScale grid) {
        return digitizeEdges(grid, 0);
    }
    
    public PolygonC2d localize(GridScale grid) {
        return c(map(p->grid.localize(p.expand()).shrink()).sfix());
    }
    
    /**
     * .
     * often times, you don't even have to choose the vertical coordinate. in that case,
     * use this convenient method.
     * @param grid
     * @return 
     */
    public TList<List<Integer>> digitize(GridScale grid) {
        return digitize(grid,0);
    }
    
    /**
     * .often times, you don't have to choose the axis to monotonize.
     * in that case,
 use this convenient method.
     * @param grid
     * @param v
     * @return 
     */
    public TList<List<Integer>> digitize(GridScale grid, double v) {
        return digitize(grid,v,X);
    }
    
    public TList<List<Integer>> digitize(GridScale grid, double v, int axis) {
        assert grid.is2d():"grid must be 2d (if not, do some transformation)";
        return localize(grid).monotonize(axis).flatMapc(c->c.digitizeLocallyMonotonized(v, axis));
    }
    
    /**
     * .
     * before you call this, you have to have this shape localized to a certain GridCore and monotonize it in a certain axis.
     * @param v
     * @param axis
     * @return 
     */
    public TList<List<Integer>> digitizeLocallyMonotonized(double v, int axis) {
        return digitizeEdges(ortho,v).sortTo(inc(p->p.get(axis)))
                .diffChunk((a,b)->!a.get(axis).equals(b.get(axis)))
                .map(l->l.sortTo(inc(p->p.get(theOther(axis)))))
                .flatMapc(l->TList.rangeSym(l.get(0).get(theOther(axis)),l.last().get(theOther(axis)))
                        .map(i->TList.set(l.get(0)).sfix().cset(theOther(axis), i).sout()));
    }
    
    /**
     * cut between a and b.
     * in any way, the result PolygonC2d starts from a.
     * @param a
     * @param b
     * @return 
     */
    public PolygonC2d cut(int a, int b) {
        assert a < size() && b < size() : "cut below size";
        if (a == b)
            return this;
        if (a < b)
            return PolygonC2d.c(subList(b,size()).append(subList(0,a)).startFrom(get(a)).sfix());
        else
            return PolygonC2d.c(subList(b,a).startFrom(get(a)).sfix());
    }
    
    /**
     * cut into two.
     * @param a
     * @param b
     * @return 
     */
    public TList<PolygonC2d> divide(int a, int b) {
        assert a < size() && b < size() : "cut below size";
        if (abs(a-b)<2)
            return TList.sof(this);
        return TList.sof(cut(a,b),cut(b,a));
    }
    
    class Edge {
        int index;
        public Edge(int i) {
            this.index=i;
        }
        TPoint2d from() {
            return rget(index);
        }
        TPoint2d to() {
            return rget(index+1);
        }
        boolean contains(int axis, double tested) {
            return (from().get(axis)<=tested&&tested<=to().get(axis))||(from().get(axis)>=tested&&tested>=to().get(axis));
        }
        double rate(int axis, double target) {
            return (from().get(axis)-target)/(from().get(axis)-to().get(axis));
        }
        double interpolate(int axis, double rate) {
            return (from().get(axis)-to().get(axis))*rate+from().get(axis);
        }
        double intersect(int axis, double target) {
            return interpolate(theOther(axis),rate(axis,target));
        }
        TPoint2d cut(int axis, double target) {
            double counter=intersect(axis,target);
            return axis < 0?new TPoint2d(target,counter):new TPoint2d(counter,target);
        }
    }
    final static int X=0;
    final static int Y=1;
    
    int theOther(int axis) {
        return (axis+1)%2;
    }
    
    class Event {
        int event;
        Event(int event) {
            this.event=event;
        }
        TPoint2d cw() {
            return rget(event-1);
        }
        TPoint2d ccw() {
            return rget(event+1);
        }
        TPoint2d current() {
            return rget(event);
        }
        TVector2d cwEdge() {
            return cw().to(current());
        }
        TVector2d ccwEdge() {
            return current().to(ccw());
        }
        boolean reflexUpper(int axis) {
            return cw().get(axis)>current().get(axis)&&ccw().get(axis)>current().get(axis)&&cwEdge().det(ccwEdge())<0;
        }
        boolean reflexLower(int axis) {
            return cw().get(axis)<current().get(axis)&&ccw().get(axis)<current().get(axis)&&cwEdge().det(ccwEdge())<0;
        }
        TList<Edge> otherEdges() {
            return TList.range(0,size()).filter(i->i!=rindex(event)&&i!=rindex(event-1)).map(i->new Edge(i)).sfix();
        }
        
        Edge closest(int axis) {
            TPoint2d p = rget(event);
            return otherEdges().filter(e->e.contains(axis,p.get(axis))).sfix().stream().min(inc(e->abs(e.intersect(axis,p.get(axis))-p.get(theOther(axis))))).get();
        }

        int closestUpper(int axis) {
            Edge e = closest(axis);
            return e.from().get(axis) > e.to().get(axis)?rindex(e.index):rindex(e.index+1);
        }

        int closestLower(int axis) {
            Edge e = closest(axis);
            return e.from().get(axis) < e.to().get(axis)?rindex(e.index):rindex(e.index+1);
        }
        TList<PolygonC2d> divideUpper(int axis) {
            return divide(event,closestUpper(axis));
        }
        TList<PolygonC2d> divideLower(int axis) {
            return divide(event,closestLower(axis));
        }
        
        @Override
        public String toString() {
            return Integer.toString(event);
        }
    }
    
    public TList<PolygonC2d> monotonize(int axis) {
        TList<Event> events = TList.range(0,size()).sortTo(inc(p->rget(p).get(axis))).map(l->new Event(l)).sfix();
        for(Event e : events) {
            if (e.reflexUpper(axis)) 
                return e.divideLower(axis).map(p->p.monotonize(axis)).sfix().flatMap(l->l);
            if (e.reflexLower(axis))
                return e.divideUpper(axis).map(p->p.monotonize(axis)).sfix().flatMap(l->l);
        }
        return TList.sof(this);
    }
    
    public void decompose(TList<TList<TPoint2d>> retval) {
        if (size()==3) {
            retval.add(this);
            return;
        }
        if (get(0).to(get(1)).det(get(1).to(get(2)))>0) {
            retval.add(TList.sof(get(0),get(1),get(2)));
            c(subList(2,size()).startFrom(get(0)).sfix()).decompose(retval);
            return;
        }
        c(rotate(1)).decompose(retval);
    }
}
