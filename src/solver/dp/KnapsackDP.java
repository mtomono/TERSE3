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
import java.util.function.BiPredicate;
import java.util.function.Function;
import shape.TPoint2i;
import static solver.dp.KnapsackDP.EdgeGuarded.composite;

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
        return new KnapsackDP<>(items,initialLine(capacity, 0, -1),
            (u,r,i)->TList.range(0,capacity+1).forEach(j->u.set(j, r.get(j)>=0?i.y:((j-i.x<0||u.get(j-i.x)<=0)?-1:u.get(j-i.x)-1))));
    }
    static public KnapsackDP<Integer, Integer> lis(TList<Integer> items) {
        return new KnapsackDP<>(TList.range(0,items.size()),initialLine(items.size()-1, 1, 1),
            (u,r,i)->TList.range(0,i).forEach(j->u.set(i, items.get(j)<items.get(i)?max(u.get(i),r.get(j)+1):u.get(i))));
    }
    static public <T> KnapsackDP<Integer, Integer> lcs(TList<T> x, TList<T> y) {
        return new KnapsackDP<>(TList.range(1,x.size()+1),initialLine(y.size(), 0, 0),
            (u,r,i)->TList.range(1,y.size()+1).forEach(j->u.set(j, y.get(j-1).equals(x.get(i-1))?TList.sof(r.get(j-1)+1,r.get(j),u.get(j-1)).max(l->l).get():TList.sof(r.get(j),u.get(j-1)).max(l->l).get())));
    }
    static public <T> KnapsackDP<Integer,Integer> levenshtein(TList<T> x,TList<T> y) {
        TList<Integer> initialLine=TList.nCopies(y.size()+1, 0).sfix();
        TList.range(1,y.size()+1).forEach(j->initialLine.set(j, initialLine.get(j-1)+1));
        return new KnapsackDP<>(TList.range(1,x.size()+1),initialLine,
            (u,r,i)->{
                u.set(0, r.get(0)+1);
                TList.range(1,y.size()+1).forEach(j->u.set(j, TList.sof(r.get(j-1)+(x.get(i-1).equals(y.get(j-1))?0:1),r.get(j)+1,u.get(j-1)+1).min(l->l).get()));
            });
    }
    static public <T> KnapsackDP<Integer,Integer> levenshteinStructured(TList<T> x,TList<T> y) {
        BiPredicate<Integer,Integer> match=(i,j)->x.get(i-1).equals(y.get(j-1));
        BiPredicate<Integer,Integer> valid=(i,j)->i>0&&j>0;
        Edge<Integer> insert=new EdgeGuarded<Integer>((p,c,i,j)->c.get(j-1)+1).guard((i,j)->j>0);
        Edge<Integer> delete=new EdgeGuarded<Integer>((p,c,i,j)->p.get(j)+1).guard((i,j)->i>0);
        Edge<Integer> replace=new EdgeGuarded<Integer>((p,c,i,j)->p.get(j-1)+1).guard(valid).guard(match.negate());
        Edge<Integer> forward=new EdgeGuarded<Integer>((p,c,i,j)->p.get(j-1)).guard(valid).guard(match);
        Edge<Integer> all=composite(insert,delete,replace,forward);
        TList<Integer> initialLine = TList.nCopies(y.size()+1,0).sfix();
        TList.range(1,y.size()+1).forEach(j->initialLine.set(j, all.stepGuarded(null,initialLine,0,j).min(l->l).get()));
        return new KnapsackDP<>(TList.range(1,x.size()+1),initialLine,
            (u,r,i)->TList.range(0,y.size()+1).forEach(j->u.set(j, all.stepGuarded(r,u,i,j).min(l->l).get())));
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
    interface Edge<V> {
        V step(TList<V>p,TList<V>c,int i,int j);
        default TList<V> stepGuarded(TList<V>p,TList<V>c,int i,int j) {
            return TList.wrap(step(p,c,i,j));
        }
        default boolean test(int i, int j) {
            return true;
        }
    }
    static class EdgeGuarded<V> implements Edge<V>{
        Edge<V> step;
        BiPredicate<Integer,Integer> guard;
        public EdgeGuarded(Edge<V> step, BiPredicate<Integer,Integer>... guard) {
            this.step=step;
            this.guard=TList.sof(guard).stream().reduce((a,b)->a.and(b)).orElse((i,j)->true);
        }
        public EdgeGuarded<V> guard(BiPredicate<Integer,Integer> guard) {
            return new EdgeGuarded<>(step,this.guard.and(guard));
        }
        @Override
        public V step(TList<V>p,TList<V>c,int i,int j) {
            return step.step(p,c,i,j);
        }
        @Override
        public TList<V> stepGuarded(TList<V>p,TList<V>c,int i,int j) {
            return test(i,j)?TList.wrap(step(p,c,i,j)):TList.empty();
        }
        public boolean test(int i, int j) {
            return guard.test(i, j);
        }
        static public <V> Edge<V> composite(Edge<V>... egs) {
            return new Edge<V>() {
                @Override
                public V step(TList<V> p, TList<V> c, int i, int j) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public TList<V> stepGuarded(TList<V> p, TList<V> c, int i, int j) {
                    return TList.sof(egs).filter(e->e.test(i,j)).map(e->e.step(p,c,i,j));
                }
            };
        }
    }    
}
