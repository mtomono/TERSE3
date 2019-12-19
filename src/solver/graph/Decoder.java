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
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class Decoder {
    final public TList<GridCoord> grids;
    final public TList<LocalCoord> coords;
    
    public Decoder(TList<GridCoord> grids, TList<LocalCoord> coords) {
        this.grids = grids;
        this.coords = coords;
    }
    
    public Decoder depth(TList<Integer> depth) {
        return new Decoder(this.grids.pickUp(depth),this.coords.pickUp(depth));
    }
    
    public Optional<List<Integer>> encode(TPoint3d point) {
        return coords.map(c->c.round(c.localize(point))).pair(TList.range(0,grids.size())).stream().filter(p->grids.get(p.r()).contains(p.l())).findFirst().map(p->TList.set(p.l()).startFrom(p.r()));
    }
    
    public TPoint3d decode(List<Integer> point) {
        return coords.get(point.get(0)).globalize(point.subList(1, point.size()));
    }
}
