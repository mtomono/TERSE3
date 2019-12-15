/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graphMetric;

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
        
    public GridGraph build() {
        return new GridGraph(gcoord,dirs);
    }
}
