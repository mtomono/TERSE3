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
import shape.TPoint3d;

/**
 *
 * @author masao
 */
public class GridRoute {
    final public GridSpace target;
    final public NodeGraph<List<Integer>> body;
    
    public GridRoute(GridSpace target, NodeGraph<List<Integer>> body) {
        this.target=target;
        this.body=body;
    }
    
    public TList<List<Integer>> route() {
        return tighten(body.findRoute());
    }
    
    public TList<TPoint3d> groute() {
        return target.globalize(route());
    }
    
    TList<TList<List<Integer>>> routeInGrids() {
        if (target instanceof GridComposite)
            return route().diffChunk((a,b)->!a.get(0).equals(b.get(0))).sfix();
        return TList.sof(route());
    }
    
    TList<Integer> grids() {
        return routeInGrids().map(l->l.get(0).get(0));
    }
    
    TList<TList<TPoint3d>> grouteInGrids() {
        return routeInGrids().map(r->target.globalize(route()));
    }
    
    public static TList<List<Integer>> tighten(TList<List<Integer>> path) {
        System.out.println("target:"+path);
        return TList.sof(path,path.seek(1),path.seek(2)).transpose(l->l)
                .filter(l->l.size()==3&&!VectorOp.subI(l.get(0), l.get(1)).equals(VectorOp.subI(l.get(1), l.get(2))))
                .map(l->l.get(1)).startFrom(path.get(0)).append(path.last()).sfix();
    }
}
