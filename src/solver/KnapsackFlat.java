/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;
import static function.ComparePolicy.inc;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.Grid;

/**
 *
 * @author masao
 */
public class KnapsackFlat<T> {
    static public Grid<Integer> solve(TList<TPoint2i> items, int capacity) {
        Grid<Integer> dpmap = new Grid<>(TPoint2i.zero, p2i(items.size(), capacity), (x,y)->null);
        TList.range(0,capacity+1).forEach(rest->dpmap.set(items.size(), rest, 0));
        TList.range(0,items.size()).reverse().forEach(i->TList.range(0,capacity+1).forEach(j->{
            if (j<items.get(i).x) dpmap.set(i,j, dpmap.get(i+1,j));
            else dpmap.set(i,j, TList.sof(dpmap.get(i+1,j),dpmap.get(i+1,j-items.get(i).x)+items.get(i).y).max(inc(n->n)).get());
        }));
        return dpmap;
    }
}
