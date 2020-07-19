/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.Collection;
import java.util.List;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class BareBoneGridSolver {
    MetricGraph<List<Integer>> graph;
    public BareBoneGridSolver(GridCoord gcoord) {
        this.graph=new MetricizedGraph<>(metric(3),GridGraphBuilder.builder(gcoord).alt().build());
    }
    
    public TList<List<Integer>> solve(List<Integer> from, List<Integer>to, Collection<List<Integer>> blocks) {
        return NodeGraphBuilder.builder(graph, from, to).earlyExit().astar(metric(3)).block(blocks).build().fillLoop().findRoute();
    }
    
    static public Metric<List<Integer>> metric(double v) {
        return GridMetric.l1().costv(v).i();
    }
}
