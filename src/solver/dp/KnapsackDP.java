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

package solver.dp;

import collection.TList;
import function.Holder;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.function.Function;
import shape.TPoint2i;

/**
 *
 * @author masao
 * @param <T> item list type
 * @param <R> referred list type
 */
public class KnapsackDP<T,R> {
    TList<T> items;
    int capacity;
    TList<R> initialLine;
    Update<T,R> update;
    public KnapsackDP(TList<T> items, int capacity, TList<R> initialLine, Update<T,R> update) {
        this.items=items;
        this.capacity=capacity;
        this.initialLine=initialLine;
        this.update=update;
    }

    static public KnapsackDP<TPoint2i, Integer> knapsack(TList<TPoint2i> items, int capacity) {
        return new KnapsackDP<>(items,capacity,initialLine(capacity, 0, 0),
            (u,r,i)->TList.range(min(i.x,capacity+1),capacity+1).forEach(j->u.set(j, max(r.get(j),r.get(j-i.x)+i.y))));
    }
    static public KnapsackDP<Integer, Boolean> cut(TList<Integer> items, int capacity) {
        return new KnapsackDP<>(items,capacity,initialLine(capacity, true, false),
            (u,r,i)->TList.range(min(i,capacity+1),capacity+1).forEach(j->u.set(j, r.get(j)||r.get(j-i))));
    }
    static public KnapsackDP<Integer, Integer> count(TList<Integer> items, int capacity) {
        return new KnapsackDP<>(items,capacity,initialLine(capacity, 1, 0),
            (u,r,i)->TList.range(min(i,capacity+1),capacity+1).forEach(j->u.set(j, r.get(j)+r.get(j-i))));
    }
    static public KnapsackDP<Integer, Integer> shortest(TList<Integer> items, int capacity) {
        return new KnapsackDP<>(items,capacity,initialLine(capacity, 0, -1),
            (u,r,i)->TList.range(min(i,capacity+1),capacity+1).forEach(j->u.set(j, TList.sof(r.get(j),r.get(j-i)+1).filter(x->x>0).min(x->x).orElse(-1))));
    }

    static public <R> TList<R> initialLine(int capacity, R zero, R others) {
        return TList.nCopies(capacity, others).startFrom(zero);
    }
    public TList<R> psolve() {
        Holder<TList<R>> h = new Holder<>(initialLine);
        items.forEach(i->{
            TList<R> pre = h.push(h.get().fix());
            update.update(h.get(),pre,i);
        });
        return h.get();
    }
    public TList<R> csolve() {
        TList<R> line = initialLine.fix();
        items.forEach(i->update.update(line, line, i));
        return line;
    }
    public TList<TList<R>> psolvew() {
        TList<TList<R>> lines = TList.c();
        lines.add(initialLine);
        items.forEach(i->{
            lines.add(lines.last().fix());
            update.update(lines.last(), lines.last(1), i);
        });
        return lines;
    }
    public <S> TList<TList<S>> psolvew(Function<R,S> f) {
        return psolvew().map(l->l.map(f));
    }
    public TList<TList<R>> csolvew() {
        TList<TList<R>> lines = TList.c();
        lines.add(initialLine.fix());
        items.forEach(i->{
            lines.add(lines.last().fix());
            update.update(lines.last(), lines.last(), i);
        });
        return lines;
    }
    @FunctionalInterface
    interface Update<T,R> {
        public void update(TList<R> updated, TList<R> referred, T item);
    }
}
