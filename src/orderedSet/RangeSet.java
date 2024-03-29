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

package orderedSet;

import collection.TList;
import java.util.*;
import iterator.Iterators;
import static iterator.Iterators.toStream;
import static java.util.stream.Collectors.toList;
import static collection.TList.concat;
import function.Order;
import java.math.BigDecimal;
import java.util.function.Function;
import math.Context;
import string.Message;

/**
 *
 * @author mtomono
 * @param <T>
 */
public class RangeSet<T> extends AbstractList<Range<T>> {
    public static Builder<Integer> intRanges = b();
    public static Builder<Long> longRanges = b();
    public static Builder<Double> doubleRanges= b();
    public static Builder<BigDecimal> bdRanges= b();
    public static <T> Builder<T> b(Order<T> order) {
        return new Builder<>(order);
    }
    public static <T extends Comparable<? super T>> Builder<T> b() {
        return b(Order.<T>natural());
    }
    public static class Builder<T> {
        final public Order<T> order;
        final public Range.Builder<T> rbuilder;
        public Builder(Order<T> order) {
            this.order=order;
            this.rbuilder=Range.b(order);
        }
        public RangeSet<T> rs(List<Range<T>> ranges) {
            return new RangeSet<>(this, ranges);
        }
        public RangeSet<T> rs(Range<T> range) {
            return rs(Collections.singletonList(range));
        }
        public RangeSet<T> rs(Range<T>... ranges) {
            return rs(TList.sof(ranges));
        }
        public RangeSet<T> rp(List<T> range) {
            return rs(rbuilder.rs(range));
        }
        public RangeSet<T> rp(T... range) {
            return rs(rbuilder.rs(range));
        }
        public RangeSet<T> empty() {
            return rs(Collections.emptyList());
        }
        /**
         * force some list of range into RangeSet by sorting and merging ranges.
         * @param rs
         * @return 
         */
        public RangeSet<T> mergeIntoRangeSet(TList<Range<T>> rs) {
            return rs.sortTo(order.map(r->r.start())).diffChunk((a,b)->!a.overlaps(b)).map(rl->rl.stream().reduce((a,b)->a.cover(b))).filter(or->or.isPresent()).map(or->or.get()).transform(l->rs(l));
        }
        public RangeSet<T> intersectMany(List<RangeSet<T>> many) {
            return many.stream().reduce((a,b)->a.intersect(b)).orElse(empty());
        }

        public RangeSet<T> unionMany(List<RangeSet<T>> many) {
            return many.stream().reduce(empty(), (a,b)->a.union(b));
        }
    }
    public RangeSet(Builder<T> builder, List<Range<T>> elements) {
        super();
        this.builder=builder;
        assert rangesAreInOrder(elements) : "range is not in order"+elements.toString();
        this.elements = elements;
    }
    
    final List<Range<T>> elements;
    final Builder<T> builder;
    
    @Override
    public Range<T> get(int index) {
        return elements.get(index);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean equals(Object e) {
        if (!(e instanceof RangeSet))
            return false;
        return elements.equals(((RangeSet)e).elements);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.elements);
        return hash;
    }

    public final boolean rangesAreInOrder(List<Range<T>> target) {
        if (target.size() <= 1)
            return true;
        return concat(target).diff((l, r)->l.isLowerThan(r)).stream().allMatch(p->p);
    }
   
    public final boolean pointsAreInOrder(List<T> target) {
        if (target.size() <= 1)
            return true;
        return concat(target).pair(target.subList(1, target.size()), (l, r)->builder.order.lt(l, r)).stream().allMatch(p->p);
    }
               
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public List<Range<T>> toFragments() {
        return elements;
    }
    
    public boolean containsPoint(T point) {
        return containsPoints(Collections.singletonList(point));
    }
    
    public boolean containsPoints(List<T> another) {
        assert pointsAreInOrder(another);
        return cover().map(r->r.contains(another) && !new WalkerRp<>(negateIterator(r), another.listIterator()).overlap().hasNext()).orElse(another.isEmpty());
    }
    
    public boolean contains(Range<T> range) {
        return contains(Collections.singletonList(range));
    }
    
    public boolean contains(List<Range<T>> another) {
        assert rangesAreInOrder(another);
        return contains(new RangeSet<>(builder,another));
    }
    
    public boolean contains(RangeSet<T> another) {
        return cover().map(r->another.cover().map(s->r.contains(s) && !new WalkerRr<>(negateIterator(r), another.listIterator()).overlap().hasNext()).orElse(true)).orElse(!another.cover().isPresent());
    }
    
    public boolean overlapsPoints(List<T> points) {
        assert pointsAreInOrder(points);
        return new WalkerRp<>(this.listIterator(), points.listIterator()).overlap().hasNext();
    }
    
    public boolean overlaps(List<Range<T>> another) {
        assert rangesAreInOrder(another);
        return new WalkerRr<>(this.listIterator(), another.listIterator()).overlap().hasNext();
    }
    
    public boolean overlaps(RangeSet<T> another) {
        return overlaps(another.elements);
    }
    
    public List<Range<T>> touchPoint(List<T> another) {
        assert pointsAreInOrder(another);
        return toStream(new WalkerRp<>(this.listIterator(), another.listIterator()).touch()).collect(toList());
    }
    
    public List<Range<T>> touch(List<Range<T>> another) {
        assert rangesAreInOrder(another);
        return toStream(new WalkerRr<>(this.listIterator(), another.listIterator()).touch()).collect(toList());
    }
    
    public List<Range<T>> touch(RangeSet<T> another) {
        assert rangesAreInOrder(another);
        return toStream(new WalkerRr<>(this.listIterator(), another.listIterator()).touch()).collect(toList());
    }
    
    public List<T> intersectPoint(List<T> another) {
        assert pointsAreInOrder(another);
        return toStream(new WalkerRp<>(this.listIterator(), another.listIterator()).intersect()).collect(toList());
    }
    
    public List<Range<T>> intersect(List<Range<T>> another) {
        assert rangesAreInOrder(another);
        return toStream(new WalkerRr<>(this.listIterator(), another.listIterator()).intersect()).collect(toList());
    }
    
    public RangeSet<T> intersect(RangeSet<T> another) {
        return new RangeSet<>(builder,intersect(another.elements));
    }
    
    public Iterator<Range<T>> negateIterator(Range<T> target) {
        return new NegateRangeIterator<>(target, listIterator());
    }
    
    public RangeSet<T> negate(Range<T> target) {
        return new RangeSet<>(builder,toStream(negateIterator(target)).collect(toList()));
    }
    
    public Iterator<Range<T>> maskIterator(ListIterator<Range<T>> another, Range<T> range) {
        return new WalkerRr<>(another, negateIterator(range)).intersect();
    }
    
    public Iterator<Range<T>> maskedIterator(ListIterator<Range<T>> another, Range<T> range) {
        return new WalkerRr<>(this.listIterator(), new NegateRangeIterator<>(range, another)).intersect();
    }
    
    public Iterator<Range<T>> maskedIterator(ListIterator<Range<T>> another) {
        return cover().map(r->maskedIterator(another, r)).orElse(Collections.emptyIterator());
    }
    
    public Iterator<Range<T>> maskIterator(ListIterator<Range<T>> another) {
        return cover().map(r->maskIterator(another, r)).orElse(Collections.emptyIterator());
    }
    
    public RangeSet<T> maskedBy(RangeSet<T> another) {
        return new RangeSet<>(builder,toStream(maskedIterator(another.listIterator())).collect(toList()));
    }
    
    public RangeSet<T> mask(RangeSet<T> another) {
        return another.cover().map(r->new RangeSet<>(builder,toStream(maskIterator(another.listIterator(), r)).collect(toList()))).orElse(this);
    }
    
    public Optional<Range<T>> cover() {
        if (elements.isEmpty())
            return Optional.empty();
        if (elements.size() == 1)
            return Optional.of(elements.get(0));
        return Optional.of(elements.get(0).cover(elements.get(elements.size() - 1)));
    }
        
    public Iterator<Range<T>> unionIterator(RangeSet<T> another) {
        return another.cover().map(r->Optional.of(r.cover(cover()))).
            orElse(cover().map(r->r.cover(another.cover()))).
                map(r->buildUnionIterator(another, r)).orElse(Collections.emptyIterator());
    }
    
    private Iterator<Range<T>> buildUnionIterator(RangeSet<T> another, Range<T> r) {
        return new NegateRangeIterator<>(r, Iterators.buffer(new WalkerRr<>(this.negateIterator(r), another.negateIterator(r)).intersect(), 2));
    }
        
    public RangeSet<T> union(RangeSet<T> another) {
        return new RangeSet<>(builder,toStream(unionIterator(another)).collect(toList()));
    }
        
    public boolean covers(T point) {
        if (elements.isEmpty())
            return false;
        return cover().get().contains(point);
    }
    
    public <K extends Comparable<? super K>,C extends Context<K,C>> RangeSet<K> shift(Function<T,C> f, C s) {
        return new RangeSet<>(b(),elements.stream().map(r->r.shift(f,s)).collect(toList()));
    }
    
    @Override
    public String toString() {
        return Message.nl().csv(elements).toString();
    }

}
