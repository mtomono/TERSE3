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
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridGraphBuilder {
    GridCoord gcoord;
    TList<List<Integer>> dirs;
    
    public static GridGraphBuilder builder(int... end) {
        return builder(GridCoord.gcoord(end));
    }
    
    public static GridGraphBuilder builder(GridCoord gcoord) {
        return new GridGraphBuilder(gcoord);
    }
    
    public GridGraphBuilder(GridCoord gcoord) {
        this.gcoord=gcoord;
        this.dirs=gcoord.dirsAlternate().sfix();
    }
        
    public GridGraphBuilder alt() {
        this.dirs=gcoord.dirsAlternate();
        return this;
    }
        
    public GridGraphBuilder alt3d() {
        this.dirs=gcoord.dirsAlternate().subList(0,6).sfix();
        return this;
    }
        
    public GridGraph build() {
        return new GridGraph(gcoord,dirs);
    }
}
