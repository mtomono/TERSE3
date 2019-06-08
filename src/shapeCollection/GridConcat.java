/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static function.ComparePolicy.inc;
import java.util.Collections;
import java.util.List;
import orderedSet.Range;

/**
 *
 * @author masao
 */
public class GridConcat<T> {
    int axisIndex;
    TList<GridSimple<T>> grids;
    TList<Range<Integer>> map;
    
    public GridConcat(int axis, TList<GridSimple<T>> grids) {
        this.map=grids.map(g->g.axis().axis.get(axis).vr).fix();
        this.grids = grids.sortTo(inc(g->g.axis().axis.get(axis).vr.start()));
        this.axisIndex = axis;
    }
    
    public GridCoord axis() {
        return new GridCoord(grids.get(0).axis().axis.fix().cset(axisIndex, new GridAxis(map.get(0).start(),map.last().end()-1)));
    }
    
    GridSimple<T> fallIn(List<Integer> address) {
        return grids.get(Collections.binarySearch(map.map(r->r.end()-1), address.get(axisIndex)));
    }
    
    public T get(List<Integer> address) {
        return fallIn(address).get(address);
    }
    
    public T set(T v, List<Integer> address) {
        return fallIn(address).set(v, address);
    }
}
