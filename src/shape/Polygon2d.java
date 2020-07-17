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

import collection.P;
import collection.TList;
import static function.ComparePolicy.inc;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import orderedSet.Range;
import static shape.ShapeUtil.err;
import solver.graph.GridScale;

/**
 *
 * @author masao
 */
public class Polygon2d extends TList<TPoint2d> {
    
    public static Polygon2d c(List<TPoint2d> vertices) {
        assert !vertices.isEmpty();
        return new Polygon2d(vertices);
    }
        
    public static Polygon2d c(TPoint2d... vertices) {
        return c(TList.of(vertices));
    }
    
    Polygon2d(List<TPoint2d> vertices) {
        super(vertices);
    }
    
    public TList<Segment2d> asEdges() {
        return diff((a,b)->new Segment2d(a, b));
    }
    
    public double length() {
        return asEdges().stream().mapToDouble(s->s.length()).sum();
    }
    
    public TList<Segment2d> asClosed() {
        return pair(rotate(1), (a,b)->new Segment2d(a, b));
    }
    
    public boolean isClosed() {
        return get(0).epsilonEquals(last(), err*1000);
    }
    
    public Polygon2d subPolygon(int from, int to) {
        return Polygon2d.c(subList(from, to));
    }
    
    public Polygon3d expand(double v) {
        return Polygon3d.c(map(p->p.expand(v)));
    }
    
    public Polygon3d expand() {
        return expand(0);
    }
    
    public TList<List<Integer>> digitize(GridScale grid, double v) {
        return expand(v).digitize(grid);
    }
    
    public TList<List<Integer>> digitize(GridScale grid) {
        return digitize(grid,0);
    }
    
    public Rect2d cover() {
        return new Rect2d(new TPoint2d(stream().mapToDouble(p->p.x).min().getAsDouble(), stream().mapToDouble(p->p.y).min().getAsDouble()), 
                          new TPoint2d(stream().mapToDouble(p->p.x).max().getAsDouble(), stream().mapToDouble(p->p.y).max().getAsDouble()));
    }
        
    public TList<Polygon2d> chunk(int factor) {
        if (factor >= size())
            return TList.wrap(this);
        TList<Polygon2d> retval = TList.c();
        for (int i=0; i<size(); i+=factor)
            retval.add(subPolygon(i+factor<=size()?i:Math.max(0, size()-factor), Math.min(i+factor, size())));
        return retval;
    }
    
    public Polygon2d cull(int factor) {
        TList<Integer> picker = TList.range(0, size()/factor).map(i->i*factor).append(TList.wrap(size()-1));
        return Polygon2d.c(pickUp(picker));
    }
    
    public TList<P<TList<Segment2d>, Rect2d>> hint(int chunkFactor, double margin) {
       return chunk(chunkFactor).map(p->P.p(p.asEdges(), p.cover().margin(margin)));
    }
    
        public abstract class ClosestPart {
        Polygon2d target;
        public ClosestPart(Polygon2d target) {
            this.target = target;
        }
        
        /**
         * search kernel is the range where closest part searching is limited to the hint chunks those which overlaps with.
         * @param point
         * @param hint
         * @return 
         */
        public abstract Range<Double> searchKernel(TPoint2d point, TList<P<TList<Segment2d>, Rect2d>>hint);
        
        public Optional<Segment2d> find(TPoint2d point, TList<P<TList<Segment2d>, Rect2d>> hint) {
            return hint.filter(p->p.r().range(point).overlaps(searchKernel(point, hint))).flatMap(p->p.l()).fix().min(s->s.distance(point));
        }
    }
    
    public class LocallyClosestPart extends ClosestPart {
        public LocallyClosestPart(Polygon2d target) {
            super(target);
        }
        /**
         * search kernel is those which touches the target point
         * @param point
         * @param hint
         * @return 
         */
        @Override
        public Range<Double> searchKernel(TPoint2d point, TList<P<TList<Segment2d>, Rect2d>> hint) {
            return hint.map(h->h.r()).filter(h->h.contains(point)).map(h->h.range(point)).accum(new Range<>(0.0,0.0), (a,b)->a.cover(b)).last();
        }
    }
    
    public class GloballyClosestPart extends ClosestPart {
        public GloballyClosestPart(Polygon2d target) {
            super(target);
        }
        /**
         * search kernel is the best candidate
         * @param point
         * @param hint
         * @return 
         */
        @Override
        public Range<Double> searchKernel(TPoint2d point, TList<P<TList<Segment2d>, Rect2d>> hint) {
            return hint.map(h->h.r()).fix().min(p->p.distance(point)).get().range(point);
        }
    }
    
    /**
     * simplest form of finding closest segment
     * @param point
     * @return can be empty when this polygon is made of only one point
     */
    public Optional<Segment2d> closestPart(TPoint2d point) {
        return asEdges().fix().min(s->s.distance(point));
    }

    public Optional<Segment2d> closestPart(TPoint2d point, TList<P<TList<Segment2d>, Rect2d>> hint) {
        return new GloballyClosestPart(this).find(point, hint);
    }
    
    /**
     * closest segment Which is only hit by hint box
     * @param point
     * @param hint
     * @return 
     */
    public Optional<Segment2d> localClosestPart(TPoint2d point, TList<P<TList<Segment2d>, Rect2d>> hint) {
        return new LocallyClosestPart(this).find(point, hint);
    }
    
    public double closestPosition(TPoint2d point) {
        return closestPointPosition(point).r();
    }
    
    public double distance(TPoint2d point) {
        Iterator<TPoint2d> iter = iterator();
        if (!iter.hasNext())
            throw new RuntimeException();
        TPoint2d point0 = iter.next();
        if (!iter.hasNext())
            return point0.distance(point);
        return closestPart(point).get().distance(point);
    }
    
    public TPoint2d closestPoint(TPoint2d point) {
        Iterator<TPoint2d> iter = iterator();
        if (!iter.hasNext())
            throw new RuntimeException();
        TPoint2d point0 = iter.next();
        if (!iter.hasNext())
            return point0;
        return closestPart(point).get().closestPoint(point);
    }
    
    public P<TPoint2d, Double> closestPointPosition(TPoint2d point) {
        assert closestPart(point).isPresent();
        Segment2d closestPart = closestPart(point).get();
        int intPart = indexOf(closestPart.start);
        TPoint2d closestPoint = closestPart.closestPoint(point);
        double decPart = closestPart.start.distance(closestPoint)/closestPart.length();
        return P.p(closestPoint, decPart + intPart);        
    }
    
    /**
     * cut this polygon in forward order.
     * @param from
     * @param to
     * @return 
     */
    public Polygon2d firstCutByClosestPoints(TPoint2d from, TPoint2d to) {
        TList<P<TPoint2d, Double>> cutters = TList.sof(from, to).map(p->closestPointPosition(p)).fix();
        assert cutters.isAscending(inc(p->p.r()));
        return Polygon2d.c(TList.wrap(cutters.get(0).l()).append(subList((int)ceil(cutters.get(0).r()), (int)ceil(cutters.get(1).r()))).append(TList.wrap(cutters.get(1).l())).fix());
    }
    
    /**
     * cut this polygon in the other way than firstCutByClosestPosition() only if this polygon is closed.
     * @param from
     * @param to
     * @return 
     */
    public Polygon2d anotherCutByClosestPoints(TPoint2d from, TPoint2d to) {
        assert isClosed();
        TList<P<TPoint2d, Double>> cutters = TList.sof(from, to).map(p->closestPointPosition(p)).fix();
        assert cutters.isDescending(inc(p->p.r()));
        Polygon2d rotated = Polygon2d.c(rotate((int)floor(cutters.get(0).r())).fix());
        if (ceil(cutters.get(0).r())==ceil(cutters.get(1).r()))
            return Polygon2d.c(TList.wrap(cutters.get(0).l()).append(rotated.subList(1, size())).append(TList.wrap(cutters.get(1).l())).fix());
        return rotated.firstCutByClosestPoints(from, to);
    }

    /**
     * cut this polygon depending on the way from and to is ordered in aspect of position.
     * @param from
     * @param to
     * @return 
     */
    public Polygon2d cutByClosestPoints(TPoint2d from, TPoint2d to) {
        TList<P<TPoint2d, Double>> cutters = TList.sof(from, to).map(p->closestPointPosition(p)).fix();
        if (cutters.isAscending(inc(p->p.r())))
            return firstCutByClosestPoints(from, to);
        else
            return anotherCutByClosestPoints(from, to);
    }
}
