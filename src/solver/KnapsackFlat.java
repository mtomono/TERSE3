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
package solver;

import collection.TList;
import static function.ComparePolicy.inc;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.Grid;

/**
 * Non-recursive way of Knapsack solver.
 * @author masao
 * @param <T>
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
