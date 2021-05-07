/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package fiber;

import collection.TList;
import iterator.TIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author masao
 */
public class TCord<T> {
    public static <T> TCord<T> set(TFiber<T>... sources) {
        return set(TList.sof(sources));
    }
    public static <T> TCord<T> set(TList<TFiber<T>> sources) {
        return new TCord(sources);
    }
    final public TList<TFiber<T>> body;
    public TCord(TList<TFiber<T>> body) {
        this.body=body;
    }
    public <R> TCord<R> map(Function<? super T,? extends R> f) {
        return set(body.<TFiber<R>>map(s->s.map(f)).sfix());
    }
    public <R> TCord<R> flatMap(Function<? super T,? extends List<? extends R>>f) {
        return set(body.map(s->s.flatMap(f)).sfix());
    }
    public <R> TCord<R> flatIter(Function<? super T,? extends Iterator<? extends R>>f) {
        return set(body.map(s->s.flatIter(f)).sfix());
    }
    public TCord<T> filter(Predicate<? super T> pred) {
        return set(body.map(s->s.filter(pred)).sfix());
    }
    public <C extends Collection<? super T>> TList<C> sink(TList<C> retval) {
        body.pair(retval,(a,b)->a.sink(b)).sfix();
        return retval;
    }
    public <C extends Collection<? super T>> TList<C> sink(C... retval) {
        return sink(TList.sof(retval));
    }
    public <C extends Collection<? super T>> TList<C> sink(Supplier<C> s) {
        TList<C> retval=TList.c();
        body.iterator().pair(TIterator.generate(s),(a,b)->a.sink(retval.addTee(b))).forEachRemaining(x->{});
        return retval;
    }
}
