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
import java.util.List;
import java.util.Optional;
import static math.VectorOp.round;
import shape.TPoint3d;

/**
 *
 * @author masao
 */
public class GridComposite implements GridSpace {
    final public CompositeGraph<List<Integer>> graph;
    final public TList<GridMono> grids;
    final public Metric<List<Double>> baseMetric;
    
    public GridComposite(CompositeGraph<List<Integer>> graph, TList<GridMono> grids, Metric<List<Double>> baseMetric) {
        assert !grids.isEmpty() : "grids cannot be empty";
        assert grids.forAll(g->graph.body.contains(g.graph)) : "wrong grid is included in grids";
        this.graph=graph;
        this.grids=grids;
        this.baseMetric = baseMetric;
    }
    
    public Graph<List<Integer>> graph() {
        return graph;
    }
    
    public GridComposite depth(TList<Integer> depth) {
        return new GridComposite(graph,this.grids.pickUp(depth),baseMetric);
    }
    
    @Override
    public Optional<List<Integer>> localize(TPoint3d point) {
        return grids.map(g->round(g.coord.localize(point))).pair(TList.range(0,grids.size())).stream().filter(p->grids.get(p.r()).graph.gcoord.contains(p.l())).findFirst().map(p->TList.set(p.l()).startFrom(p.r()));
    }
    
    @Override
    public TPoint3d globalize(List<Integer> point) {
        return grids.get(point.get(0)).coord.globalize(point.subList(1, point.size()));
    }
    
    @Override
    public TList<List<Integer>> toCube(TList<TPoint3d> line) {
        return TList.range(0,grids.size()).map(i->grids.get(i).toCube(line).map(l->(List<Integer>)TList.set(l).insertAt(0,i))).sfix().flatMap(l->l).sfix();
    }
    
    @Override    
    public Metric<List<Integer>> metric(double weight) {
        return baseMetric.morph(Metric.<Double>weight(TList.sof(1,1,compensateHv(weight))).compose(this::globalize));
    }
    
    @Override
    public double compensateHv(double target) {
        return grids.minval(g->g.compensateHv(target)).orElseThrow(()->new RuntimeException("this can't happen"));
    }
}
