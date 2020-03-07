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

import static collection.c.a2l;
import static collection.c.i2l;
import function.Holder;
import function.TSupplier;
import iterator.*;
import static iterator.Iterators.toStream;
import java.util.Iterator;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * wraps a supplier of iterator as an iterable
 * @author mtomono
 * @param <T>
 */
public class TIterable<T> implements Iterable<T> {
    Supplier<TIterator<T>> supplier;
    
    public static <T> TIterable<T> set(Iterable<T> base) {
        return new TIterable<>(()->TIterator.set(base.iterator()));
    }
    
    public static <T> TIterable<T> of(T... t) {
        return set(a2l(t));
    }
    
    public static <T> TIterable<T> generate(Supplier<Supplier<T>> supplier) {
        return new TIterable<>(()->TIterator.generate(supplier.get()));
    }
    
    public static <T> TIterable<T> iterate(T init, UnaryOperator<T> op) {
        return new TIterable<>(()->TIterator.iterate(init, op));
    }
        
    public static TIterable<Integer> range(int start, int end) {
        return new TIterable<>(()->TIterator.range(start, end));
    }
    
    public TIterable(Supplier<TIterator<T>> supplier) {
        this.supplier = supplier;
    }
    
    @Override
    public TIterator<T> iterator() {
        return TIterator.set(supplier.get());
    }
    
    public TIterable<T> append(TIterable<T> added) {
        return of(this,added).flatMap(i->i);
    }
    
    public TIterable<T> tee(Consumer<Iterable<T>> tee) {
        tee.accept(this);
        return this;
    }
    
    public TIterable<T> concat(Iterable<T> added) {
        return new TIterable(()->iterator().concat(added.iterator()));
    }
    
    public <S> TIterable<S> map(Function<T, S> map) {
        return new TIterable<>(()->iterator().map(map));
    }
    
    public TIterable<T> filter(Predicate<T> pred) {
        return new TIterable<>(()->iterator().filter(pred));
    }
    
    public <S> TIterable<S> flatMap(Function<T, Iterable<S>> map) {
        return new TIterable<>(()->iterator().flatMap(map.andThen(i->i.iterator())));
    }
    
    public <S> TIterable<S> weave(Function<T, Iterable<S>> map) {
        return new TIterable<>(()->iterator().weave(map.andThen(i->i.iterator())));
    }
    
    public TIterable<T> twist(Iterable<T> iter) {
        return new TIterable<>(()->iterator().twist(iter.iterator()));
    }
    
    public TIterable<T> before(Predicate<T> term) {
        return new TIterable<>(()->iterator().before(term));
    }
    
    public TIterable<T> until(Predicate<T> term) {
        return new TIterable<>(()->iterator().until(term));
    }
    
    public TIterable<T> seek(Predicate<T> term) {
        return new TIterable<>(()->iterator().seek(term));
    }
    
    public TIterable<T> seek(int count) {
        return new TIterable<>(()->iterator().seek(count));
    }
    
    public TIterable<T> limit(long limit) {
        return new TIterable<>(()->iterator().limit(limit));
    }
    
    public TIterable<T> delimit(Supplier<Supplier<T>> delimiter) {
        return new TIterable<>(()->iterator().delimit(delimiter.get()));
    }
    
    public <S> TIterator<P<T, S>> diffIterator(Iterator<S> add, int seek) {
        return iterator().diff(add, seek);
    }
    
    public TIterable<P<T, T>> diff() {
        return diff(1);
    }
    
    public TIterable<P<T, T>> diff(int seek) {
        return new TIterable<>(()->diffIterator(iterator(), seek));
    }
    
    public <S> TIterable<P<T, S>> diff(TIterable<S> add) {
        return diff(add, 1);
    }
    
    public <S> TIterable<P<T, S>> diff(TIterable<S> add, int seek) {
        return new TIterable<>(()->diffIterator(add.iterator(), seek));
    }
    
    public <S> TIterable<P<T, S>> pair(Iterable<S> add) {
        return new TIterable<>(()->iterator().pair(add.iterator()));
    }
    
    public <S> TIterable<P<T, S>> pmap(Function<T, S> map) {
        return pair(map(map));
    }
    
    public <S> TIterable<S> accum(S start, BiFunction<S, T, S> map) {
        return new TIterable<>(()->iterator().accum(start, map));
    }
    
    @Deprecated
    public <S> TIterable<S> heap(S start, BiFunction<T, S, S> map) {
        return accum(start, (a,b)->map.apply(b,a));
    }
    
    public Stream<T> stream() {
        return toStream(this.iterator());
    }
    
    public TSupplier<T> repeat() {
        Holder<Iterator<T>> h = new Holder<>(iterator());
        assert h.get().hasNext();
        return TSupplier.f(()->h.get().hasNext() ? h.get().next() : h.set(iterator()).next());
    }
    
    @Override
    public String toString() {
        return i2l(iterator()).toString();
    }
}
