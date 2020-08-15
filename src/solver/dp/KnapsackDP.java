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
import java.util.function.BiFunction;
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
    TList<R> initialLine;
    Traverse<T,R> traverse;
    public KnapsackDP(TList<T> items, TList<R> initialLine, Traverse<T,R> traverse) {
        this.items=items;
        this.initialLine=initialLine;
        this.traverse=traverse;
    }

    static public KnapsackDP<TPoint2i, Integer> knapsack(TList<TPoint2i> items, int capacity) {
        return new KnapsackDP<>(items,initialLine(capacity, 0, 0),
            (u,r,i)->TList.range(min(i.x,capacity+1),capacity+1).forEach(j->u.set(j, max(r.get(j),r.get(j-i.x)+i.y))));
    }
    static public KnapsackDP<Integer, Boolean> cut(TList<Integer> items, int capacity) {
        return new KnapsackDP<>(items,initialLine(capacity, true, false),
            (u,r,i)->TList.range(min(i,capacity+1),capacity+1).forEach(j->u.set(j, r.get(j)||r.get(j-i))));
    }
    static public KnapsackDP<Integer, Integer> count(TList<Integer> items, int capacity) {
        return new KnapsackDP<>(items,initialLine(capacity, 1, 0),
            (u,r,i)->TList.range(min(i,capacity+1),capacity+1).forEach(j->u.set(j, r.get(j)+r.get(j-i))));
    }
    static public KnapsackDP<Integer, Integer> shortest(TList<Integer> items, int capacity) {
        return new KnapsackDP<>(items,initialLine(capacity, 0, -1),
            (u,r,i)->TList.range(min(i,capacity+1),capacity+1).forEach(j->u.set(j, TList.sof(r.get(j),r.get(j-i)+1).filter(x->x>0).min(x->x).orElse(-1))));
    }
    static public KnapsackDP<TPoint2i, Integer> numberOfItemsLeft(TList<TPoint2i> items, int capacity) {
        EdgeDet<TPoint2i,Integer> fulfilled=(p,c,i,j)->p.get(j)>=0;
        Edge<TPoint2i,Integer> remain=(p,c,i,j)->c.get(j-i.x);
        return new KnapsackDP<>(items,initialLine(capacity, 0, -1),
            (u,r,i)->{
                TList.range(0,capacity+1)
                        .filter(j-> fulfilled.go(r,u,i,j)).forEach(j->u.set(j,i.y));
                TList.range(min(i.x,capacity+1),capacity+1)
                        .filter(j->!fulfilled.go(r,u,i,j))
                        .filter(j->remain.go(r,u,i,j)>0).forEach(j->u.set(j, remain.go(r,u,i,j)-1));
            });
    }
    static public KnapsackDP<Integer, Integer> lis(TList<Integer> items) {
        Edge<Integer,Integer> asis=(p,c,i,j)->c.get(i);
        Edge<Integer,Integer> match=(p,c,i,j)->p.get(j)+1;
        return new KnapsackDP<>(TList.range(0,items.size()),initialLine(items.size()-1, 1, 1),
            (u,r,i)->TList.range(0,i).forEach(j->u.set(i, 
                    items.get(j)<items.get(i)?
                            max(asis.go(r,u,i,j),match.go(r,u,i,j))
                               :asis.go(r,u,i,j))));
    }
    static public <T> KnapsackDP<Integer, Integer> lcs(TList<T> x, TList<T> y) {
        Edge<Integer,Integer> asis=(p,c,i,j)->max(p.get(j),c.get(j-1));
        Edge<Integer,Integer> match=(p,c,i,j)->p.get(j-1)+1;
        return new KnapsackDP<>(TList.range(1,x.size()+1),initialLine(y.size(), 0, 0),
            (u,r,i)->TList.range(1,y.size()+1).forEach(j->u.set(j, 
                    y.get(j-1).equals(x.get(i-1))?
                            max(asis.go(r,u,i,j),match.go(r,u,i,j))
                               :asis.go(r,u,i,j))));
    }
    static public <T> KnapsackDP<Integer,Integer> levenshtein(TList<T> x,TList<T> y) {
        Edge<Integer,Integer> insert=(p,c,i,j)->c.get(j-1)+1;
        Edge<Integer,Integer> delete=(p,c,i,j)->p.get(j)+1;
        Edge<Integer,Integer> replace=(p,c,i,j)->p.get(j-1)+1;
        Edge<Integer,Integer> forward=(p,c,i,j)->p.get(j-1);
        TList<Integer> initialLine=TList.nCopies(y.size()+1, 0).sfix();
        TList.range(1,y.size()+1).forEach(j->initialLine.set(j, insert.go(null,initialLine,0,j)));
        return new KnapsackDP<>(TList.range(1,x.size()+1),initialLine,
            (u,r,i)->{
                TList.range(0,         1).forEach(j->u.set(j, 
                        delete.go(r,null,i,j)));
                TList.range(1,y.size()+1).forEach(j->u.set(j, 
                        TList.sof(
                                (x.get(i-1).equals(y.get(j-1))?
                                        forward.go(r,u,i,j):replace.go(r,u,i,j))
                                ,delete.go(r,u,i,j)
                                ,insert.go(r,u,i,j))
                        .min(l->l).get()));
            });
    }
    static public <T> KnapsackDP<Integer,Integer> dpMatching(TList<T> x,TList<T> y, BiFunction<T,T,Integer> cost) {
        return new KnapsackDP<>(TList.range(1,x.size()+1),initialLine(y.size(), 0, 0),
            (u,r,i)->TList.range(1,y.size()+1).forEach(j->u.set(j, TList.sof(r.get(j-1),r.get(j),u.get(j-1)).min(l->l).get()+cost.apply(x.get(i-1), y.get(j-1)))));
    }
    static public <R> TList<R> initialLine(int capacity, R zero, R others) {
        return TList.nCopies(capacity, others).startFrom(zero);
    }
    public TList<R> psolve() {
        Holder<TList<R>> h = new Holder<>(initialLine);
        items.forEach(i->{
            TList<R> pre = h.push(h.get().fix());
            traverse.go(h.get(),pre,i);
        });
        return h.get();
    }
    public TList<R> csolve() {
        TList<R> line = initialLine.fix();
        items.forEach(i->traverse.go(line, line, i));
        return line;
    }
    public TList<TList<R>> psolvew() {
        TList<TList<R>> lines = TList.c();
        lines.add(initialLine);
        items.forEach(i->{
            lines.add(lines.last().sfix());
            traverse.go(lines.last(), lines.last(1), i);
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
            lines.add(lines.last().sfix());
            traverse.go(lines.last(), lines.last(), i);
        });
        return lines;
    }
    @FunctionalInterface
    public interface Traverse<T,R> {
        public void go(TList<R> updated, TList<R> referred, T item);
    }
    @FunctionalInterface
    interface Edge<U,V> {
        V go(TList<V>p,TList<V>c,U i,int j);
    }
    @FunctionalInterface
    interface EdgeDet<U,V> {
        Boolean go(TList<V>p,TList<V>c,U i,int j);
    }
}
