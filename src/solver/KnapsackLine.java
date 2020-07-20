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

import collection.P;
import collection.TList;
import function.Holder;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import shape.TPoint2i;

/**
 * Non-recursive way of Knapsack solver.
 * in the calculation of KnapsackFlat, spotlighted at the fact that the calculation
 * proceeds item by item. and the calculation is only refering to the item right before
 * the item in focus. that means grid can be reduced to two lines (item at the focus and 
 * the item right before it).
 * those two lines cannot be integrated into one line. if it is the case, somewhere in
 * the line modified in the calculation is referred as previous line's value, thus causes
 * inconsistence.
 * @author masao
 * @param <T>
 */
public class KnapsackLine<T> {
    static public TList<Integer> solve(TList<TPoint2i> items, int capacity) {
        Holder<TList<Integer>> h = new Holder<>(TList.nCopies(capacity+1, 0));
        items.forEach(i->{
            TList<Integer> pre = h.push(h.get().fix());
            TList.range(min(i.x,capacity+1),capacity+1).forEach(j->h.get().set(j, max(pre.get(j),pre.get(j-i.x)+i.y)));
        });
        return h.get();
    }
    
    /**
     * one lined version of solve().
     * to get the Holder into context, making them a pair.
     * little bit slower than solve() and little bit less lucid than solve();
     * @param items
     * @param capacity
     * @return 
     */
    static public TList<Integer> solve0(TList<TPoint2i> items, int capacity) {
        Holder<TList<Integer>> h = new Holder<>(TList.nCopies(capacity+1, 0));
        items.iterator().map(i->P.p(i, h.push(h.get().fix())))
            .forEachRemaining(p->TList.range(min(p.l().x,capacity+1),capacity+1)
                .forEach(j->h.get().set(j, max(p.r().get(j),p.r().get(j-p.l().x)+p.l().y))));
        return h.get();
    }
}
