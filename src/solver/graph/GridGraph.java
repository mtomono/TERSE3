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
import math.VectorOp;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraph implements Graph<List<Integer>> {
    public final GridCoord gcoord;
    public final TList<List<Integer>> dirs;
    
    public GridGraph(GridCoord gcoord, TList<List<Integer>> dirs) {
        this.gcoord = gcoord;
        this.dirs = dirs;
    }

    @Override
    public TList<List<Integer>> next(List<Integer> from) {
        return dirs.map(d->VectorOp.addI(from, d)).filter(p->gcoord.contains(p)).sfix();
    }

    @Override
    public TList<List<Integer>> all() {
        return gcoord.addresses();
    }
    
}
