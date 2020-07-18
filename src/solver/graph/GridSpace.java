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
 * Space defined with grids.
 * grids can be one, which are basically defined by GridCore.
 * globalize and localize points in view of cube.
 * when grids are multiple, it handles the situation by increasing the dimension of cube.
 * in that case this class adds new dimension at the head of the list.
 * 
 * this class can produce metric for route searching. for that purpose, this comes with
 * the facility of calculating compensation rate for horisontal vs vertical.
 * 
 * @author masao
 */
public interface GridSpace {
    
    Graph<List<Integer>> graph();
    
    TPoint3d globalizeI(List<Integer> point);
    TPoint3d globalizeD(List<Double> point);
    
    default TList<TPoint3d> globalizeI(TList<List<Integer>> points) {
        return points.map(p->globalizeI(p)).sfix();
    }

    default TList<TPoint3d> globalizeD(TList<List<Double>> points) {
        return points.map(p->globalizeD(p)).sfix();
    }

    Optional<List<Integer>> localize(TPoint3d point);

    TList<List<Integer>> toCube(TList<TPoint3d> line);
    
    default Metric<List<Integer>> globalMetric(Metric<List<Double>> base, double costv) {
        return base.morph(Metric.<Double>weight(TList.sof(1,1,costv)).compose(this::globalizeI));
    }
    default Metric<List<Integer>> localMetric(Metric<List<Double>> base, double costv) {
        return base.morph(Metric.weight(TList.sof(1,1,costv)));
    }
}
