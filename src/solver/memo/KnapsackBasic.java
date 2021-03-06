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
import static java.lang.Integer.max;
import shape.TPoint2i;

/**
 * Knapsack problem solver.
 * 
 * @author masao
 */
public class KnapsackBasic {    
    public KnapsackBasic() {
    }
        
    /**
     * core mothod of this algorithm.
     * returns the biggest value possibly be made from i to n-1th items when 
     * capacity of 'rest' remains
     * @param i
     * @param rest
     * @return 
     */
    int value(int i, int rest, TList<TPoint2i> c) {//x:volume y:value
        if (i==c.size())
            return 0;
        if (rest<c.get(i).x)
            return value(i+1, rest, c);
        return max(value(i+1,rest,c), value(i+1,rest-c.get(i).x,c)+c.get(i).y);
    }
    
    public int solve(int capacity, TList<TPoint2i>c) {
        return value(0, capacity,c);
    }
}
