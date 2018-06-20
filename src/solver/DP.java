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
import java.util.function.Function;
import orderedSet.Comparators;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.Grid;

/**
 * Dynamic Programming.
 * this is another answer to make memo recursion.
 * instead of making the calling sequence complicated, this enables to reuse the 
 * applying and memoing structure.
 * in this class, something which can be called as 'method wrapping' is realized.
 * this technique allows conformation separated from the implementation (especially 
 * that of the memory structure).
 * the implementation of this class casts a good contrast with KnapsackBasicMemo.
 * @author masao
 */
abstract public class DP<S,R> {
    static <S,R> Function<TPoint2i,R> basic(DP<S,R> k, TList<S> c) {
        return pi->k.valueCore(pi.x, pi.y, c);
    }

    static <S,R> Function<TPoint2i,R> memo(DP<S,R> k, Map<TPoint2i, R> map) {
        final Function<TPoint2i, R> f = k.value; //this is very the necessary step to fix the function this method is wrapping.
        return pi->map.computeIfAbsent(pi, f);
    }
    
    static <S,R> Function<TPoint2i,R> memo(DP<S,R> k, Grid<R> map) {
        final Function<TPoint2i, R> f = k.value;
        return pi->map.computeIfNull(pi, f);
    }

    abstract R valueCore(int i, int rest, TList<S> c);

    Function<TPoint2i, R> value; //this class cannot be an interface because of the existance of this value.
        
    public DP() {
        value=p->{throw new RuntimeException("DP:value function is not set");}; //reports when value is called before it is set.
    }
    
    /**
     * memo.
     * thanks to abstracting memo by lambda, the DP object itself is free of those fields. thus, making it possible
     * for other classes to use those static methods.
     * @param map
     * @return 
     */
    public DP<S,R> memo(Map<TPoint2i, R> map) {
        return setValue(memo(this,map));
    }
    
    public DP<S,R> memo() {
        return memo(new TreeMap<>(Comparators.<TPoint2i>sof(p->p.x, p->p.y).compile()));
    }
    
    public DP<S,R> memo(Grid<R> map) {
        return setValue(memo(this,map));
    }
    
    public DP<S,R> target(TList<S> c) {
        return setValue(basic(this,c));
    }
    
    public DP<S,R> setValue(Function<TPoint2i, R> value) {
        this.value = value;
        return this;
    }
    
    public R value(int i, int rest) {
        return value.apply(p2i(i,rest));
    }
    
    
    public R solve(int capacity) {
        return value(0, capacity);
    }
}
