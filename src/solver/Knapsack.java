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
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;
import shape.TPoint2i;
import static solver.Solvers.extract2i;

/**
 * Knapsack problem solver.
 * 
 * @author masao
 */
public class Knapsack {    
    
    public Knapsack() {
    }
    
    /**
     * 
     * @param i from here to the end of the list are candidate elements to be used
     * @param rest remaining space
     * @param c target list of elements.
     * @return 
     */
    Result<Integer> value(int i, int rest, TList<TPoint2i> c) {
        if (i==c.size())
            return new Result<>(0);
        if (rest<c.get(i).x)
            return value(i+1,rest,c);
        return TList.sof(value(i+1,rest,c), value(i+1,rest-c.get(i).x,c).add(i,v->v+c.get(i).y)).max(inc(x->x.value)).get();
    }
    
    public Result<Integer> solve(int capacity, TList<TPoint2i> c) {
        return value(0, capacity, c);
    }
        
    static public <T> Result<Integer> solve(Knapsack k, int capacity, TList<T> target, ToIntFunction<T> volume, ToIntFunction<T> value) {
        return k.solve(capacity, extract2i(target,volume, value));
    }
    static public <T> TList<T> solveElements(Knapsack k, int capacity, TList<T> target, ToIntFunction<T> volume, ToIntFunction<T> value) {
        Result<Integer> result = solve(k,capacity,target,volume,value);
        return target.pickUp(result.content);
    }
}
