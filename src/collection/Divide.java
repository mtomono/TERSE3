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

package collection;

import static collection.TList.toTList;
import static orderedSet.Builder.intRange;
import orderedSet.Range;

/**
 *
 * @author masao
 * @param <T>
 */
public interface Divide<T> {
    /**
     * division of n things
     * @param target
     * @param divideNum
     * @return 
     */
    static public TList<TList<Integer>> divides(int target, int divideNum) {
        return TList.combinationT(target+1, divideNum);
    }
    
    static public TList<TList<Integer>> dividesAtLeastOne(int target, int divideNum) {
        return divides(target-2, divideNum).map(l->l.map(n->n+1));
    }

    static public TList<TList<Integer>> integerDivide(int target, int divideNum, int min) {
        if (divideNum == 0)
            return TList.of(TList.wrap(target));
        TList<TList<Integer>> retval = TList.c();
        for (int i=min;min<=target-i;i++) {
            final int x = i;
            retval.addAll(integerDivide(target-x, divideNum-1, min).map(l->TList.wrap(x).append(l)));
        }
        return retval;
    }
    
    static public TList<TList<Integer>> dividesAtLeast(int target, int divideNum, int min) {
        return integerDivide(target, divideNum, min).map(l->l.subList(0, l.size()-1).iterator().accum((a,b)->a+b).stream().collect(toTList()));
    }
    
    static public TList<TList<Range<Integer>>> ranges(int target, TList<TList<Integer>> divides) {
        return divides.map(l->TList.wrap(0).append(l).append(TList.wrap(target)).diff((a,b)->intRange.r(a,b)));
    }
    
    static public TList<TList<Integer>> width(int target, TList<TList<Integer>> divides) {
        return divides.map(l->l.isEmpty()?TList.wrap(target):TList.wrap(0).append(l).append(TList.wrap(target)).diff((a, b)->b-a));
    }
    
    static public <T> TList<TList<TList<T>>> divide(TList<T> target, int divides) {
        return Divide.ranges(target.size(), Divide.divides(target.size(), divides)).map(l->l.map(r->target.subList(r)));
    }
    
    static public <T> TList<TList<TList<T>>> divide(TList<T> target, int divides, int min) {
        return Divide.ranges(target.size(), Divide.dividesAtLeast(target.size(), divides, min)).map(l->l.map(r->target.subList(r)));
    }

    static public <T> TList<TList<TList<T>>> divide(int size, T e, int divides, int min) {
        return Divide.integerDivide(size, divides, min).map(l->l.map(n->TList.nCopies(n, e)));
    }
    
    static public <T> TList<TList<TList<T>>> divide(int size, T e, int divides) {
        return Divide.width(size, Divide.divides(size, divides)).map(l->l.map(n->TList.nCopies(n, e)));
    }
}
