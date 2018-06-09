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
import static java.lang.Integer.max;
import java.util.function.ToIntFunction;

/**
 * Knapsack problem solver.
 * 
 * @author masao
 */
public class KnapsackBasic<T> {
    TList<T> candidates;
    ToIntFunction<T> volume;
    ToIntFunction<T> value;
    
    public KnapsackBasic(TList<T> candidates, ToIntFunction<T> volume, ToIntFunction<T> value) {
        this.candidates = candidates;
        this.volume = volume;
        this.value = value;
    }
    
    /**
     * core mothod of this algorithm.
     * returns the biggest value possibly be made from i to n-1th items when 
     * capacity of 'rest' remains
     * @param i
     * @param rest
     * @return 
     */
    int value(int i, int rest) {
        if (i==candidates.size())
            return 0;
        T candidate = candidates.get(i);
        if (rest<volume.applyAsInt(candidate))
            return value(i+1, rest);
        return max(value(i+1,rest), value(i+1,rest-volume.applyAsInt(candidate))+value.applyAsInt(candidate));
    }
    
    public int solve(int capacity) {
        return value(0, capacity);
    }
}
