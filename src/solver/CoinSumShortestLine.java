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
import function.Holder;
import static java.lang.Integer.min;

/**
 * Non-recursive way of Partial Sum solver.
 * the original of this class is KnapsackLine.
 * @author masao
 * @param <T>
 */
public class CoinSumShortestLine<T> {
    static public TList<Integer> solve(TList<Integer> items, int capacity) {
        Holder<TList<Integer>> h = new Holder<>(TList.nCopies(capacity, -1).startFrom(0));
        items.forEach(i->{
            TList<Integer> pre = h.push(h.get().fix());
            TList.range(min(i,capacity+1),capacity+1).forEach(j->h.get().set(j, TList.sof(h.get().get(j),h.get().get(j-i)+1).filter(x->x>0).min(x->x).orElse(-1)));
        });
        return h.get();
    }
    static public TList<Integer> solve0(TList<Integer> items, int capacity) {
        TList<Integer> line = TList.nCopies(capacity, -1).startFrom(0).fix();
        items.forEach(i->TList.range(min(i,capacity+1),capacity+1).forEach(j->line.set(j, TList.sof(line.get(j),line.get(j-i)+1).filter(x->x>0).min(x->x).orElse(-1))));
        return line;
    }
}
