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

package iterator;

import static collection.c.a2i;
import collection.P;
import collection.RingBuffer;
import collection.TList;
import collection.TMap;
import static collection.c.i2l;
import function.CHolder;
import function.Holder;
import function.IntHolder;
import function.NoReach;
import function.TSupplier;
import static iterator.Iterators.toStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;
import math.Summary;
import math.C;
import math.Context;

/**
 *
 * @author masao
 * @param <T>
 */
public class TIterator<T> implements Iterator<T> {
    final Iterator<T> body;
    
    static public <T> TIterator<T> set(Iterator<T> body) {
        return new TIterator<>(body);
    }
    
    public TIterator(Iterator<T> body) {
        this.body = body;
    }
    
    public Iterator<?> base() {
        return (body instanceof TIterator) ? ((TIterator)body).base() : body;
    }
    
    @Override
    public boolean hasNext() {
        return body.hasNext();
    }
    
    @Override
    public T next() {
        return body.next();
    }
    
    public TIterator<T> tee(Consumer<T> tee) {
        return map(t->{tee.accept(t);return t;});
    }
    
    public TIterator<T> report(int interval,Consumer<T> tee) {
        CHolder<Integer,C<Integer>> h=new CHolder<>(C.i.b(0));
        return tee(t->{if(h.get().body()<interval)h.upx(); else {tee.accept(t);h.setget(C.i.zero());}});
    }
    public <S> S transform(Function<TIterator<T>,S> f) {
        return f.apply(this);
    }
    static public <T> TIterator<T> of(T... t) {
        return set(a2i(t));
    }
    
    static public <T> TIterator<T> generate(Supplier<T> supplier) {
        return set(new SupplierWrapper<>(supplier));
    }
    
    static public <T> TIterator<T> shuffle(List<T> base) {
        return set(new ShuffleIterator<>(base));
    }
    
    static public <T> TIterator<T> random(List<T> base) {
        return randomInteger(base.size()).map(i->base.get(i));
    }
    
    static public TIterator<Integer> randomInteger(int range) {
        Random r=new Random();
        return generate(()->r.nextInt(range));
    }

    static public TIterator<Double> randomDouble(double range) {
        Random r=new Random();
        return generate(()->r.nextDouble()*range);
    }

    static public <T> TIterator<T> iterateOmit0(T init, UnaryOperator<T> op) {
        return set(new OperatorWrapper(init, op));
    }
    
    static public <T> TIterator<T> iterate(T init, UnaryOperator<T> op) {
        return set(OperatorWrapper.fromStart(init, op));
    }
    
    static public TIterator<Integer> range(int start, int end) {
        assert end > start;
        IntHolder h = new IntHolder(start);
        return generate(()->h.push(h.get() + 1)).limit(end - start);
    }
    
    public TIterator<T> append(Iterator<T> added) {
        return set(new IteratorIterator<>(this,added));
    }
    
    public boolean hasMore(int size) {
        assert size>=0;
        if (size==0) return true;
        if (!hasNext()) return false;
        for (int i=1; i<size; i++) {
            next();
            if (!hasNext()) return false;
        }
        return true;
    }
    
    public T last() {
        assert hasNext() : "last() is called when no item is left in this iterator";
        T retval = next();
        for (T t : this.i())
            retval = t;
        return retval;
    }
    
    public List<T> last(int i) {
        RingBuffer<T> retval = new RingBuffer<>(i);
        for (T t: this.i())
            retval.push(t);
        return retval;
    }
    
    public <S> TIterator<S> map(Function<? super T, ? extends S> map) {
        return set(new MapIterator<>(this, map));
    }
    
    public TIterator<T> concat(Iterator<T> iter) {
        return set(new IteratorIterator<>(a2i(this, iter)));
    }
    
    public TIterator<T> filter(Predicate<? super T> pred) {
        return set(new SelectIterator<>(this, pred));
    }
    
    public TIterator<T> skip(BiPredicate<? super T,? super T> pred) {
        return set(new SkipIterator<>(this, pred));
    }
    
    public <S> TIterator<S> flatMap(Function<? super T, ? extends Iterator<? extends S>> map) {
        return set(new IteratorIterator<>((Iterator<Iterator<S>>)new MapIterator<>(this, map)));
    }
    /**
     * flatten for list of list.
     * handier than flatMap for list of list. this can solve nesting recursively.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TIterator<S> flatten(Function<? super T, ? extends List<? extends S>> map) {
        return flatMap(map.andThen(l->l.iterator()));
    }
    
    public <S> TIterator<S> weave(Function<T, Iterator<S>> map) {
        return set(new WeaveIterator<>(i2l(this.map(map))));
    }
    
    public TIterator<T> twist(Iterator<T> iter) {
        return set(new WeaveIterator<>(this, iter));
    }
    
    public TIterator<T> cull(int skip) {
        return set(new CullIterator(this, skip));
    }
    
    public TIterator<T> delimit(Supplier<T> delimiter) {
        return set(new Delimiter<>(this, delimiter));
    }
    
    public TIterator<T> before(Predicate<T> term) {
        return set(new BeforeIterator<>(this, term));
    }
    
    public TIterator<T> until(Predicate<T> term) {
        return set(new UntilIterator<>(this, term));
    }
        
    /**
     * until for differential determination.
     * convergence is a calculation belongs to this.
     * @param cond
     * @return 
     */
    public TIterator<T> until(BiPredicate<T,T> cond) {
        if (!hasNext())
            return this;
        T start=next();
        Holder<T> prev=new Holder<>(start);
        return of(start).append(until(t->cond.test(prev.get(),prev.setget(t))));
    }
    
    public TIterator<T> seek(Predicate<T> term) {
        return of(set(new UntilIterator<>(this, term)).last()).concat(this);
    }
    
    public TIterator<T> seek(int count) {
        for (int i = 0; i < count && hasNext(); i++)
            next();
        return this;
    }
    
    public TIterator<T> seek(long count) {
        for (long i=0; i<count&&hasNext(); i++)
            next();
        return this;
    }
       
    public TIterator<P<T, T>> diff() {
        return diff((a,b)->P.p(a, b));
    }
        
    public <R> TIterator<R> diff(BiFunction<T,T,R> f) {
        if (!hasNext())
            return of();
        Holder<T> h = new Holder<>(next());
        return map(e->f.apply(h.push(e), e));
    }
        
    public <S> TIterator<P<T, S>> pair(Iterator<S> add) {
        return pair(add,(t,s)->P.p(t,s));
    }
    
    public <S,R> TIterator<R> pair(Iterator<S> add, BiFunction<T,S,R> f) {
        assert (!(add instanceof TIterator) || base() != ((TIterator)add).base());
        return TIterator.set(new ZI<>(this,add,f));
    }
    
    public <S> TIterator<P<T, S>> diff(Iterator<S> add) {
        return diff(add, 1);
    }
    
    public <S> TIterator<P<T, S>> diff(Iterator<S> add, int seek) {
        return pair(set(add).seek(seek));
    }
    
    public <S> TIterator<P<T, S>> pmap(Function<T, S> map) {
        return map(e->P.p(e, map.apply(e)));
    }
    
    public TIterator<T> limit(long limit) {
        return set(new LimitIterator<>(this, limit));
    }
    
    public TIterator<T> limit(long from, long to) {
        return limit(to).seek(from);
    }
    
    public <S> TIterator<S> accum(S start, BiFunction<S, T, S> bf) {
        Holder<S> h = new Holder<>(start);
        return of(start).concat(map(e->h.setget(bf.apply(h.get(), e))));
    }
    
    @Deprecated
    public <S> TIterator<S> heap(S start, BiFunction<T, S, S> map) {
        return accum(start, (a,b)->map.apply(b, a));
    }
    
    public TIterator<T> accum(BinaryOperator<T> map) {
        if (!hasNext())
            return set(Collections.emptyIterator());
        return accum(next(), map);
    }
    
    @Deprecated
    public TIterator<T> heap(BinaryOperator<T> map) {
        return accum((a,b)->map.apply(b,a));
    }
        
    public <S> S reduce(S s, BiFunction<S,T,S> accumulator) {
        S retval=s;
        while (hasNext()) retval=accumulator.apply(retval,next());
        return retval;
    }
    
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        if (!hasNext()) return Optional.empty();
        return Optional.of(reduce(next(),accumulator));
    }
    
    public TIterator<TList<T>> chunk(Predicate<? super T> pred) {
        return TIterator.set(new ChunkIterator<>(this,pred)).map(l->TList.set(l));
    }
    
    public TIterator<TList<T>> reverseChunk(Predicate<? super T> pred) {
        return TIterator.set(new ChunkReverseIterator<>(this,pred)).map(l->TList.set(l));
    }
    
    public TIterator<TList<T>> envelopChunk(Predicate<? super T> pred) {
        return TIterator.set(new ChunkEnvelopIterator<>(this,pred)).map(l->TList.set(l));
    }
    
    public TIterator<TList<T>> trimmedChunk(Predicate<? super T> pred) {
        return TIterator.set(new ChunkTrimmedIterator<>(this,pred)).map(l->TList.set(l));
    }
    
    public boolean forAll(Predicate<? super T> pred) {
        return !filter(pred.negate()).hasNext();
    }
    public boolean allMatch(Predicate<? super T> pred) {
        return forAll(pred);
    }
    public boolean exists(Predicate<? super T> pred) {
        return filter(pred).hasNext();
    }
    public boolean anyMatch(Predicate<? super T> pred) {
        return exists(pred);
    }
    
    public Stream<T> stream() {
        return toStream(this);
    }
    
    public <R,A> R collect(Collector<? super T,A,R> collector) {
        return stream().collect(collector);
    }
    
    public TMap<T,Long> count() {
        return stream().collect(groupingBy(t->t,TMap::c,counting()));
    }
    
    public <S,K,C extends Context<K,C>> TMap<S,Summary<K,C>> summary(Function<T,S> classify,Function<T,C> value,C zero) {
        return stream().collect(groupingBy(classify,TMap::c,Summary.summary(value,zero)));
    }
    
    public TIterator<T> sink(Consumer<TIterator<T>> sink) {
        sink.accept(this);
        return this;
    }
    
    public Iterable<T> i() {
        return ()->this;
    }
    
    public TList<T> asList() {
        return TList.set(i2l(this));
    }
    
    public TSupplier<T> supplier(Supplier<T> shortage) {
        return TSupplier.f(()->this.hasNext() ? this.next() : shortage.get());
    }
    
    public TSupplier<T> supplier() {
        return TIterator.this.supplier(new NoReach<>());
    }
        
    public int countRemaining() {
        return Iterators.count(this);
    }
}
