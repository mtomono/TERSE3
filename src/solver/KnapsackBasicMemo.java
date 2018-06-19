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
import java.util.Map;
import java.util.TreeMap;
import static orderedSet.Comparators.sof;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import static shape.TPoint2i.xc;
import static shape.TPoint2i.yc;

/**
 * Memo recursive version of Knapsack.
 * @author masao
 */
public class KnapsackBasicMemo extends KnapsackBasic {
    Map<TPoint2i, Integer>memo;

    public KnapsackBasicMemo() {
        this.memo = new TreeMap<>(sof(xc,yc).compile());
    }
    
    /**
     * memoing the result of value().
     * i'm so excited when i found out this way to make a simple but powerful wrapping of this
     * method. i put this note so that i can come back here later.
     * 
     * computeIfAbsent looks super suitable for this purpose. from the beginning, map is only suitable
     * when the space (i x rest) is sparse. but this is the case in which all the element in (i x rest)
     * is calculated. judging from it, container for this purpose should be something like 2d array.
     * but in java, 2d array doesn't have not much advantage over List of List (memory structure is 
     * already similar). so it lead me to add the method like computeIfNull to Grid class.
     * @param i
     * @param rest
     * @param c
     * @return 
     */
    @Override
    public int value(int i, int rest, TList<TPoint2i> c) {
        return memo.computeIfAbsent(p2i(i,rest),p->super.value(i,rest,c));
    }
        

}
