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

import java.util.Collection;
import java.util.List;

/**
 *
 * @author masao
 */
public class GridSolver {
    GridSpace target;
    Metric<List<Integer>> metric;
    NodeGraphBuilder<List<Integer>> ngb;
    
    public GridSolver(GridSpace target, double costv, List<Integer> from, List<Integer> to) {
        this.target=target;
        this.metric=target.metric(costv);
        this.ngb=NodeGraphBuilder.builder(metric, target.graph(), from, to);
    }
    
    public GridSolver earlyExit() {
        this.ngb.earlyExit();
        return this;
    }
    
    public GridSolver fullSearch() {
        this.ngb.fullSearch();
        return this;
    }
    
    public GridSolver astar() {
        this.ngb.astar(metric);
        return this;
    }
    
    public GridSolver node() {
        this.ngb.node();
        return this;
    }
    
    public GridSolver black(Collection<List<Integer>> blocks) {
        this.ngb.block(blocks);
        return this;
    }
    
    public GridSolver white(Collection<List<Integer>> blocks) {
        this.ngb.white(blocks);
        return this;
    }
    
    public GridRoute solve() {
        NodeGraph<List<Integer>> retval= this.ngb.build();
        retval.fillLoop();
        return new GridRoute(target,retval);
    }
}
