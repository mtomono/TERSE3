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
import shape.TPoint3d;

/**
 *
 * @author masao
 */
public interface GridSpace {
    
    Graph<List<Integer>> graph();
    
    Metric<List<Integer>> metric(double weight);
    
    double compensateHv(double target);

    TPoint3d globalize(List<Integer> point);
    default TList<TPoint3d> globalize(TList<List<Integer>> points) {
        return points.map(p->globalize(p)).sfix();
    }

    Optional<List<Integer>> localize(TPoint3d point);

    TList<List<Integer>> grids(TList<TPoint3d> line);

}
