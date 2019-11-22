/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static arithmetic.Arithmetic.mod;
import collection.TList;
import static function.ComparePolicy.inc;
import static java.lang.Math.abs;
import shape.PolygonC2d.Edge;

/**
 * Polygon Closed 2d.
 * PolygonC2d doesn't contain the last point.
 * in that point, the representation of this shape is different from that of Polygon2d.
 * @author masao
 */
public class PolygonC2d extends TList<TPoint2d> {
    public static PolygonC2d c(TList<TPoint2d> vertices) {
        assert !vertices.isEmpty();
        return new PolygonC2d(vertices);
    }
    
    public static PolygonC2d set(TList<TPoint2d> vertices) {
        return c(vertices);
    }
    
    public static PolygonC2d c(TPoint2d... vertices) {
        return c(TList.of(vertices));
    }
    
    PolygonC2d(TList<TPoint2d> vertices) {
        super(vertices);
    }
    
    public TPoint2d rget(int p) {
        return get(rindex(p));
    }
    
    public int rindex(int p) {
        return mod.o(p,size());
    }
    
    public PolygonC2d subC(int a, int b) {
        assert a < size() && b < size() : "cut below size";
        if (a == b)
            return this;
        if (a < b)
            return PolygonC2d.c(subList(b,size()).append(subList(0,a)).startFrom(get(a)).sfix());
        else
            return PolygonC2d.c(subList(b,a).startFrom(get(a)).sfix());
    }
    
    public TList<PolygonC2d> divide(int a, int b) {
        assert a < size() && b < size() : "cut below size";
        if (abs(a-b)<2)
            return TList.sof(this);
        return TList.sof(subC(a,b),subC(b,a));
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
            set(subList(2,size()).startFrom(get(0)).sfix()).decompose(retval);
            return;
        }
        set(rotate(1)).decompose(retval);
    }
}
