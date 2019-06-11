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

package solver.path;

import collection.TList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import shapeCollection.GridCoord;
import shapeCollection.GridX;
import static solver.path.AStarGridX.wodFixed;

/**
 *
 * @author masao
 */
public class GridXBlocked {
    final public GridCoord axis;
    final public Function<List<Integer>, Integer> weight;
    
    public GridXBlocked(GridCoord axis, Integer... weight) {
        this(axis, wodFixed(weight));
    }

    public GridXBlocked(GridCoord axis, Function<List<Integer>,Integer> weight) {
        this.axis = axis;
        this.weight = weight;
    }
    
    public TList<List<Integer>> path(List<Integer> from, List<Integer> to, Collection<List<Integer>> blocked) {
        return new AStarGridX(AStarGridX.space(axis, blocked), from, to, weight).path();
    }
}