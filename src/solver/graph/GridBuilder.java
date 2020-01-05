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
import static shape.ShapeUtil.point3;
import static shape.ShapeUtil.vector3;
import shape.TPoint3d;
import shape.TVector3d;
import shapeCollection.GridCoord;
import static solver.graph.GridComposite.extractGraph;

/**
 *
 * @author masao
 */
public class GridBuilder {
    static public TList<TVector3d> basis(double... v) {
        return TList.sof(vector3(v[0],v[1],v[2]),vector3(v[3],v[4],v[5]),vector3(v[6],v[7],v[8]));
    }
    static public TPoint3d origin(double... v) {
        return point3(v[0],v[1],v[2]);
    }
    static public GridMono gmono(TList<TVector3d> basis, TPoint3d origin, Metric<List<Double>> baseMetrics, int... fromAndTo) {
        GridCore coord=new GridCore(basis,origin);
        GridCoord gcoord=GridCoord.gcoord(fromAndTo);
        GridGraph graph=GridGraphBuilder.builder(gcoord).alt3d().build();
        return new GridMono(graph,coord);
    }
    static public GridMono gmono(TList<TVector3d> basis, TPoint3d origin, int... fromAndTo) {
        return gmono(basis,origin,Metric.l1(),fromAndTo);
    }
    
    TList<GridMono> gmonos = null;
    GeneralGraphBuilder<List<Integer>> gbuilder;
    Metric<List<Double>> baseMetric = Metric.l2();
    public GridBuilder() {
        this.gbuilder=GeneralGraphBuilder.builder();
    }
    static public GridBuilder builder() {
        return new GridBuilder();
    }
    public GridBuilder gmonos(GridMono... gmonos) {
        this.gmonos=TList.sof(gmonos);
        return this;
    }
    public GridBuilder links(List<Integer>... links) {
        gbuilder.merge(links);
        return this;
    }
    public GridBuilder baseMetric(Metric<List<Double>> baseMetric) {
        this.baseMetric=baseMetric;
        return this;
    }
    public GridComposite build() {
        assert gmonos != null : "gmonos is null";
        return new GridComposite(new CompositeGraph<>(extractGraph(gmonos).append(gbuilder.build())), gmonos);
    }
}
