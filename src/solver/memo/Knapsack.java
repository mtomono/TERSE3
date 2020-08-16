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

package solver.memo;

import collection.TList;
import static function.ComparePolicy.inc;
import shape.TPoint2i;

/**
 * Knapsack problem solver.
 * 
 * @author masao
 */
public class Knapsack extends DP<TPoint2i, Result<Integer>>{
        
    public Knapsack() {
    }
    
    /**
     * 
     * @param i from here to the end of the list are candidate elements to be used
     * @param rest remaining space
     * @param c target list of elements.
     * @return 
     */
    @Override
    Result<Integer> valueCore(int i, int rest, TList<TPoint2i> c) {
        if (i==c.size())
            return new Result<>(0);
        if (rest<c.get(i).x)
            return value(i+1,rest);
        return TList.sof(value(i+1,rest), value(i+1,rest-c.get(i).x).add(i,v->v+c.get(i).y)).max(inc(x->x.value)).get();
    }
}
