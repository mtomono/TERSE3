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
import iterator.TIterator;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import static solver.dp.KnapsackDP1.Levenshtein.conv;

/**
 *
 * @author masao
 * @param <T> item list type
 * @param <R> referred list type
 */
public class KnapsackDP1<T,R> {
    TList<T> items;
    TList<R> initialLine;
    Traverse<T,R> traverse;
    public KnapsackDP1(TList<T> items, TList<R> initialLine, Traverse<T,R> traverse) {
        this.items=items;
        this.initialLine=initialLine;
        this.traverse=traverse;
    }

    static public <T> KnapsackDP1<Integer,Integer> levenshtein(TList<T> x,TList<T> y) {
        BiPredicate<Integer,Integer> match=(i,j)->x.get(i-1).equals(y.get(j-1));
        BiPredicate<Integer,Integer> valid=(i,j)->i>0&&j>0;
        Levenshtein insert=(p,c,i,j)->c.getOpt(j-1).map(r->r+1);
        Levenshtein delete=(p,c,i,j)->p.getOpt(j).map(r->r+1);delete=delete.cond((i,j)->i>0);
        Levenshtein replace=(p,c,i,j)->p.getOpt(j-1).map(r->r+1);replace=replace.cond(valid).cond(match.negate());
        Levenshtein forward=(p,c,i,j)->p.getOpt(j-1);forward=forward.cond(valid).cond(match);
        Levenshtein all=conv(insert,delete,replace,forward);
        TList<Integer> initialLine = TList.nCopies(y.size()+1,0).sfix();
        return new KnapsackDP1<>(TList.range(0,x.size()+1),initialLine,
            (u,r,i)->TList.range(0,y.size()+1).forEach(j->u.set(j, all.step(r,u,i,j).orElse(0))));
    }
    @FunctionalInterface
    interface Levenshtein {
        Optional<Integer> step(TList<Integer>p,TList<Integer>c,int i,int j);
        default Levenshtein cond(BiPredicate<Integer,Integer> match) {
            return (p,c,i,j)->step(p,c,i,j).filter(r->match.test(i,j));
        }
        static public Levenshtein conv(Levenshtein... ls) {
            return (p,c,i,j)->TList.sof(ls).map(l->l.step(p,c,i,j)).flatMapc(oi->oi.map(x->TList.wrap(x)).orElse(TList.empty())).min(x->x);
        }
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
    @FunctionalInterface
    interface Traverse<T,R> {
        public void go(TList<R> current, TList<R> pre, T item);
    }
}
