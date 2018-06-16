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
    TList<TPoint2i> c;
    int capacity;
    
    public Knapsack(int capacity,TList<TPoint2i>c) {
        this.c=c;
        this.capacity=capacity;
    }
    
    /**
     * 
     * @param i from here to the end of the list are candidate elements to be used
     * @param rest remaining space
     * @param c target list of elements.
     * @return 
     */
    SearchResult value(int i, int rest) {
        if (i==c.size())
            return new SearchResult();
        if (rest<c.get(i).x)
            return value(i+1,rest);
        return TList.sof(value(i+1,rest), value(i+1,rest-c.get(i).x).add(i,c.get(i).y)).max(inc(x->x.value)).get();
    }
    
    public SearchResult solve() {
        return value(0, capacity);
    }
        
    static public <T> SearchResult solve(BiFunction<Integer,TList<TPoint2i>,Knapsack> k, int capacity, TList<T> target, ToIntFunction<T> volume, ToIntFunction<T> value) {
        return k.apply(capacity,extract2i(target,volume, value)).solve();
    }
    static public <T> TList<T> solveElements(BiFunction<Integer,TList<TPoint2i>,Knapsack> k, int capacity, TList<T> target, ToIntFunction<T> volume, ToIntFunction<T> value) {
        SearchResult result = solve(k,capacity,target,volume,value);
        return target.pickUp(result.content);
    }
}
