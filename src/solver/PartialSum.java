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

/**
 * Knapsack problem solver.
 * 
 * @author masao
 */
public class PartialSum {
    
    public PartialSum() {
    }
    
    /**
     * core mothod of this algorithm.
     * returns the biggest value possibly be made from i to n-1th items when 
     * capacity of 'rest' remains
     * @param i
     * @param rest
     * @return 
     */
    Result<Boolean> value(int i, int rest, TList<Integer> c) {
        if (i==c.size() || rest==0)
            return new Result<>(rest==0);
        if (rest<c.get(i))
            return value(i+1,rest,c);
        return TList.sof(value(i+1,rest,c),value(i+1,rest-c.get(i),c).add(i, b->b)).max(inc(r->r.value?1:0)).get();
        
    }
    
    public Result<Boolean> solve(int target, TList<Integer>c) {
        return value(0, target,c);
    }
}
