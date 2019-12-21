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
import java.util.function.Function;
import shape.TPoint3d;

/**
 *
 * @author masao
 */
public class GridMono implements GridSpace{
    final public GridGraph graph;
    final public Metric<List<Double>> baseMetric;
    final public LocalCoord coord;
    
    public GridMono(GridGraph graph, LocalCoord coord, Metric<List<Double>>baseMetric) {
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
        return coord.compensateHv(target);
    }

    @Override
    public TPoint3d globalize(List<Integer> point) {
        return coord.globalize(point);
    }

    @Override
    public Optional<List<Integer>> localize(TPoint3d point) {
        List<Integer> retval= coord.round(coord.localize(point));
        return graph.gcoord.contains(retval)?Optional.of(retval):Optional.empty();
    }

    @Override
    public TList<List<Integer>> grids(TList<TPoint3d> line) {
        return coord.grids(line).filter(g->graph.gcoord.contains(g));
    }

}
