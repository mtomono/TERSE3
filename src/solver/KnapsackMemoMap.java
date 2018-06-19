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
import shape.TPoint2i;
import shapeCollection.Grid;

/**
 * Memo recursive version of Knapsack.
 * @author masao
 */
public class KnapsackMemoMap extends Knapsack {
    Grid<Result<Integer>> memo;

    public KnapsackMemoMap(int size, int capacity) {
        this.memo=new Grid<>(TPoint2i.zero,new TPoint2i(size,capacity),(x,y)->null);
    }
    
    @Override
    public Result<Integer> value(int i, int rest, TList<TPoint2i> c) {
        return memo.computeIfNull(i,rest,(ii,r)->super.value(ii,r,c));
    }
}
