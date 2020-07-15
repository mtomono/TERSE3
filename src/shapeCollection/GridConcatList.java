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

package shapeCollection;

import collection.TList;
import static function.ComparePolicy.inc;
import static java.lang.Math.abs;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import orderedSet.Range;

/**
 *
 * @author masao
 * @param <T>
 */
public class GridConcatList<T> extends AbstractList<T> {
    int axisIndex;
    TList<GridOverList<T>> grids;
    TList<Range<Integer>> map;
    GridCoord axis;
    
    static public <T> GridOverList<T> concat(GridOverList<T>... grids) {
        return concat(TList.sof(grids));
    }
    static public <T> GridOverList<T> concat(TList<GridOverList<T>> grids) {
        if (grids.isEmpty())
            return GridOverList.empty();
        if (grids.size()==1)
            return grids.get(0);
        TList<Integer> candidates = grids.get(0).axis.axis.pair(grids.get(1).axis.axis).filterAt(p->!p.l().vr.equals(p.r().vr));
        assert candidates.size()==1 : "all GridX have to have the same range for all axis except one.";
        int target = candidates.get(0);
        return concat(target,grids.sortTo(inc((g) -> g.axis.axis.get(target).vr.start())));
    }
    
    static public <T> GridOverList<T> concat(int axisIndex, GridOverList<T>... grids) {
        return concat(axisIndex,TList.sof(grids));
    }
    static public <T> GridOverList<T> concat(int axisIndex, TList<GridOverList<T>> grids) {
        GridConcatList<T> gcl = new GridConcatList(axisIndex, grids);
        return new GridOverList<>(gcl.axis, TList.set(gcl));
    }
    
    public GridConcatList(int axisIndex, GridOverList<T>... grids) {
        this(axisIndex,TList.sof(grids));
    }
    public GridConcatList(int axisIndex, TList<GridOverList<T>> grids) {
        assert grids.map(g->g.axis.axis.size()).isUniform() : "all GridX have to have the same dimension";
        assert grids.map(g->g.axis.axis).transposeT(l->l).pickUp(TList.range(0,grids.get(0).axis.axis.size())
                .filter(i->i!=axisIndex)).forAll(l->l.map(g->g.vr).isUniform()) 
                : "all GridX have to have the same range for all axis except one in which they adjoin";
        this.grids = grids;
        this.map=grids.map(g->(g.axis).axis.get(axisIndex).vr).fix();
        assert map.diff((a,b)->a.end().equals(b.start())).forAll(b->b):"all GridX have to adjoin each other";
        this.axisIndex = axisIndex;
        this.axis = new GridCoord((grids.get(0).axis).axis.fix().cset(axisIndex, new GridAxis(map.get(0).start(),map.last().end()-1)));
    }
    
    GridOverList<T> fallIn(List<Integer> address) {
        return grids.get(abs(Collections.binarySearch(map.map(r->r.end()), address.get(axisIndex))+1));
    }
    
    public T get(Integer... address) {
        return get(TList.sof(address));
    }
    public T get(List<Integer> address) {
        return fallIn(address).get(address);
    }
    
    public T set(T v, Integer... address) {
        return set(v, TList.sof(address));
    }
    public T set(T v, List<Integer> address) {
        return fallIn(address).set(v, address);
    }

    @Override
    public T get(int index) {
        return get(axis.address(index));
    }
    
    @Override
    public T set(int index, T v) {
        return set(v,axis.address(index));
    }

    @Override
    public int size() {
        return grids.sumI(g->g.axis.size());
    }
}
