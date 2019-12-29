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
package solver.graph;

import collection.TList;
import static java.lang.Double.min;
import java.util.List;
import java.util.Optional;
import static math.VectorOp.round;
import static shape.ShapeUtil.point2;
import static shape.ShapeUtil.vector2;
import shape.TPoint3d;

/**
 * Single Grid.
 * this implements functionality for GridSpace made of one grid.
 * at the same time, a grid has a specific shape, so this class comes with a
 * methods which reveal it.
 * @author masao
 */
public class GridMono implements GridSpace{
    final public GridGraph graph;
    final public Metric<List<Double>> baseMetric;
    final public GridCore coord;
    
    public GridMono(GridGraph graph, GridCore coord, Metric<List<Double>>baseMetric) {
        this.graph=graph;
        this.coord=coord;
        this.baseMetric=baseMetric;
    }

    @Override
    public Graph<List<Integer>> graph() {
        return graph;
    }
    
    @Override
    public Metric<List<Integer>> metric(double weight) {
        return baseMetric.morph(Metric.weight(TList.sof(1,1,weight)));
    }

    @Override
    public double compensateHv(double target) {
        return target/hvRatio();
    }

    public double hvRatio() {
        return coord.basis.get(2).length()/min(coord.basis.get(0).length(),coord.basis.get(1).length());
    }
    
    @Override
    public TPoint3d globalize(List<Integer> cube) {
        return coord.globalize(cube);
    }
    
    public TList<TPoint3d> globalize(TList<List<Integer>> cubes) {
        return cubes.map(p->coord.globalize(p));
    }
    
    @Override
    public Optional<List<Integer>> localize(TPoint3d point) {
        List<Integer> retval= round(coord.localize(point));
        return graph.gcoord.contains(retval)?Optional.of(retval):Optional.empty();
    }
    
    @Override
    public TList<List<Integer>> toCube(TList<TPoint3d> line) {
        return coord.toCube(coord.localize(line)).filter(g->graph.gcoord.contains(g)).sfix();
    }
    
    public TList<List<Integer>> toCube(TPoint3d from, TPoint3d to) {
        return coord.toCube(coord.localize(from),coord.localize(to)).filter(g->graph.gcoord.contains(g)).sfix();
    }
    
    // shape of this.
    
    static TList<TList<Integer>> dirs = TList.sof(TList.sof(-1,-1),TList.sof(1,-1),TList.sof(1,1),TList.sof(-1,1)); //ll->lr->ur->ul
    
    public TList<TPoint3d> rect(List<Integer> ll, List<Integer> ur, double height) {
        return dirs.map(l->l.map(i->i<0?ll:ur)).map(p->point2(p.get(0).get(0),p.get(1).get(1)))
                .pair(dirs.map(l->vector2(l.get(0),l.get(1))), (a,b)->a.addR(b.scaleR(0.5))).map(d->coord.globalize(d.expand(height)));
    }
    
    public TList<TPoint3d> bottom(List<Integer> ll, List<Integer> ur) {
        return rect(ll,ur,ll.get(2)-0.5);
    }
    
    public TList<TPoint3d> top(List<Integer> ll, List<Integer> ur) {
        return rect(ll,ur,ur.get(2)+0.5);
    }
    
    public TList<TPoint3d> circumference() {
        TList<List<Integer>> ranges=graph.gcoord.llur();
        return bottom(ranges.get(0),ranges.get(1));
    }
}
