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

import collection.Scale;
import collection.TList;
import static java.lang.Integer.max;
import java.util.Collection;
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
    final public TList<GridMono> grids; //grids do not have to be in order of graph.graph is only used in route searching。
    final public TList<Collection<List<Integer>>> limitedBlocks; //this field is used to inactivate a certain part of some grid in localize().
    
    static public TList<Graph<List<Integer>>> extractGraph(TList<GridMono> gmonos) {
        return gmonos.<Graph<List<Integer>>>map(g->g.graph);
    }
    
    public GridComposite(CompositeGraph<List<Integer>> graph, TList<GridMono> grids, TList<Collection<List<Integer>>> limitedBlocks) {
        assert !grids.isEmpty() : "grids cannot be empty";
        assert grids.forAll(g->graph.body.contains(g.graph)) : "wrong grid is included in grids";
        this.graph=graph;
        this.grids=grids;
        this.limitedBlocks=limitedBlocks.append(TList.nCopies(max(0,grids.size()-limitedBlocks.size()), TList.empty()));
    }
    
    public Graph<List<Integer>> graph() {
        return graph;
    }
    
    @Override
    public Optional<List<Integer>> localize(TPoint3d point) {
        return grids.map(g->round(g.coord.localize(point))).pair(TList.range(0,grids.size())).stream()
            .filter(p->grids.get(p.r()).graph.gcoord.contains(p.l())&&!limitedBlocks.get(p.r()).contains(p.l())).findFirst().map(p->TList.set(p.l()).append(p.r()));
    }
    
    @Override
    public TPoint3d globalize(List<Integer> point) {
        return grids.get(point.get(point.size()-1)).coord.globalize(point.subList(0, point.size()-1));
    }
    
    @Override
    public TList<List<Integer>> toCube(TList<TPoint3d> line) {
        return grids.pair(new Scale(),(g,i)->g.toCube(line).map(l->(List<Integer>)TList.set(l).fix().addOne(i))).sfix().flatMap(l->l).sfix();
    }
    
    public TList<List<Integer>> toCube(TPoint3d... line) {
        return toCube(TList.sof(line));
    }
    
// debug methods
    public TList<List<Integer>> showDirs() {
        TList<List<Integer>> retval = graph.body.filter(g->g instanceof GridGraph).map(g->(GridGraph)g).flatMap(g->g.dirs);
        System.out.println(retval);
        return retval;
    }
}
