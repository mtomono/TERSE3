/*
 Copyright 2017, 2018, 2019 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package solver.graph.gridShape;

import collection.P;
import collection.TList;
import static collection.c.a2l;
import static java.lang.Math.abs;
import java.util.List;
import static shape.ShapeUtil.p2i;
import static shape.ShapeUtil.p3i;
import shape.TPoint2d;
import shape.TPoint2i;
import shape.TPoint3i;
import solver.graph.LocalCoord;

/**
 *
 * @author masao
 */
public class GridTriangle {
    TList<TPoint2d> shape;
    LocalCoord coord;
    public GridTriangle(TList<TPoint2d> shape, LocalCoord coord) {
        this.shape=close(shape.rotate(longestEdgeFromX(close(shape))));
        this.coord=coord;
    }
    
    public static int longestEdgeFromX(TList<TPoint2d> shape) {
        return TList.range(0,4).pair(shape).diff((a,b)->P.p(a.l(), abs(a.r().x-b.r().x)))
                .stream().max((a,b)->a.r().compareTo(b.r())).get().l();
    }
    
    public static TList<TPoint2d> close(TList<TPoint2d> shape) {
        return shape.append(shape.get(0));
    }
    
    public TList<TPoint2d> bottom() {
        return shape.subList(0,2);
    }
    
    public TList<TPoint2d> others() {
        return shape.reverse().subList(0,3);
    }

    public TList<List<Integer>> fillingTiles() {
        TList<List<Integer>> bottom = coord.toCube(bottom().map(p->p.expand()));
        if (abs(bottom.get(0).get(0)-bottom.last().get(0))<2)
            return coord.toCube(shape.map(p->p.expand()));
        TList<TList<List<Integer>>> chunkedBottom = bottom.diffChunk((a,b)->!a.get(0).equals(b.get(0)));
        TList<TList<List<Integer>>> chunkedOthers = coord.toCube(others().map(p->p.expand())).diffChunk((a,b)->!a.get(0).equals(b.get(0)));
        return chunkedBottom.pair(chunkedOthers, (a,b)->a.append(b).sfix().sortTo((c,d)->c.get(1)-d.get(1))).map(e->TList.rangeSym(e.get(0).get(1),e.last().get(1)).map(y->(List<Integer>)TList.sof(e.get(0).get(0),y))).flatMap(p->p);
    }
}
