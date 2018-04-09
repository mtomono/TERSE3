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
import static shape.ShapeUtil.err;

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
    
    //todo
    // implement cuboid which is equivalent to rect2d
    
}
