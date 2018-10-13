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
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import static shape.ShapeUtil.err;
import static shape.TPoint3d.zero;

/**
 *
 * @author masao
 */
public class Polygon3d extends TList<TPoint3d> {
    public static Polygon3d c(TList<TPoint3d> vertices) {
        assert !vertices.isEmpty();
        return new Polygon3d(vertices);
    }
    
    public static Polygon3d c(TPoint3d... vertices) {
        return c(TList.of(vertices));
    }
    
    Polygon3d(List<TPoint3d> vertices) {
        super(vertices);
    }
    
    public TList<Segment3d> asEdges() {
        return diff((a,b)->new Segment3d(a, b));
    }
    
    public double length() {
        return asEdges().stream().mapToDouble(s->s.length()).sum();
    }
    
    public TList<Segment3d> asClosed() {
        return pair(rotate(1), (a,b)->new Segment3d(a, b));
    }
    
    public boolean isClosed() {
        return get(0).epsilonEquals(last(), err);
    }
    
    public Polygon3d subPolygon(int from, int to) {
        return Polygon3d.c(subList(from, to));
    }
    
    public TList<Polygon3d> chunk(int factor) {
        TList<Polygon3d> retval = TList.c();
        for (int i=0; i<size(); i+=factor)
            retval.add(subPolygon(i, Math.min(i+factor, size())));
        return retval;
    }
    
    public Polygon2d shrink() {
        return Polygon2d.c(map(p->p.shrink()));
    }
    
    @Override
    public Polygon3d fix() {
        return new Polygon3d(super.fix());
    }
    
    @Override
    public Polygon3d sfix() {
        return new Polygon3d(super.sfix());
    }
    
    public Polygon3d apply(Function<Polygon3d, TList<TPoint3d>> f) {
        return new Polygon3d(f.apply(this));
    }
    
    public Polygon3d reduceDuplicatePoint(double err) {
        return apply(p->reduceDuplicatePoint(this, err));
    }
    
    public Polygon3d reduceInLinePoint(double err) {
        return apply(p->reduceInLinePoint(this,err));
    }
    
    public Polygon3d reducePointBetweenShortEdges(double err) {
        return new Polygon3d(reducePointBetweenShortEdges(this, err));
    }
    
    static public TList<TPoint3d> reduceDuplicatePoint(TList<TPoint3d> body, double err) {
        if (body.size()<2)
            return body;
        return body.subList(1,body.size()).pair(
                body.diff((a,b)->a.to(b))
        ).filter(p->p.r().length()>err).map(p->p.l()).
                insertAt(0, body.get(0));
    }
    
    static public TList<TPoint3d> filterByPreAndPost(TList<TPoint3d> body, BiPredicate<TVector3d, TVector3d> pred) {
        if (body.size()<3)
            return body;
        return body.subList(1,body.size()-1).pair(
                        body.diff((a,b)->a.to(b)).diff((a,b)->pred.test(a, b))
                ).filter(p->!p.r()).map(p->p.l()).
                        insertAt(0, body.get(0)).append(body.last());
    }
    static public TList<TPoint3d> reduceInLinePoint(TList<TPoint3d> body, double err) {
        return filterByPreAndPost(body, (a,b)->b.normalizeS().subS(a.normalizeS()).length()<err);
    }
    static public TList<TPoint3d> reducePointBetweenShortEdges(TList<TPoint3d> body, double err) {
        return filterByPreAndPost(body, (a,b)->a.length()<err&&b.length()<err);
    }
    
    //todo
    // implement cuboid which is equivalent to rect2d
    
}
