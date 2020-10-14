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
import static collection.c.i2l;
import static function.ComparePolicy.inc;
import iterator.AbstractBufferedIterator;
import iterator.MergeIterator;
import static java.lang.Math.abs;
import java.util.*;
import java.util.function.Function;
import string.Message;

/**
 * 
 * @author mtomono
 * @param <T>
 */
public class Range<T extends Comparable<? super T>> {
    public final T start;
    public final T end;
    public final Order<? super T> order;
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Range))
            return false;
        return order.eq(this.start, ((Range<T>)o).start) && order.eq(this.end, ((Range<T>)o).end);
    }
    
    public boolean equalsAsPoint(T t) {
        return start.equals(end)&&start.equals(t);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.start);
        hash = 29 * hash + Objects.hashCode(this.end);
        hash = 29 * hash + Objects.hashCode(this.order);
        return hash;
    }
    
    @Override
    public Range<T> clone() {
        return new Range<>(start, end, order);
    }
    
    public Range(T start, T end, Order<? super T> order) {
        assert order.le(start, end) : "start=" + start + ":end = " + end;
        this.start = start;
        this.end = end;
        this.order = order;
    }
    
    public Range(T start, T end) {
        this(start, end, Default.order);
    }
    
    static public <T extends Comparable<? super T>> List<Range<T>> c(Order<? super T> order, T... range) {
        if (range.length == 0) {
            return Collections.<Range<T>>emptyList();
        } else if (range.length % 2 != 0) {
            throw new RuntimeException("Range(T...) illegal number of parameters");
        } else {
            List<Range<T>> retval = new ArrayList<>(range.length / 2);
            for (int i = 0; i < range.length; i += 2) {
                retval.add(new Range<>(range[i], range[i+1], order));
            }
            return retval;
        }
    }

    static public <T extends Comparable<? super T>> List<Range<T>> c(Order<? super T> order, List<T> range) {
        if (range.isEmpty()) {
            return Collections.<Range<T>>emptyList();
        } else if (range.size() % 2 != 0) {
            throw new RuntimeException("Range(T...) illegal number of parameters");
        } else {
            List<Range<T>> retval = new ArrayList<>(range.size() / 2);
            for (int i = 0; i < range.size(); i += 2) {
                retval.add(new Range<>(range.get(i), range.get(i+1), order));
            }
            return retval;
        }
    }

    public boolean isEmpty() {
        return order.eq(start, end);
    }
    
    public boolean startsAt(T value) {
        return order.eq(start, value);
    }
    
    public boolean endsAt(T value) {
        return order.eq(end, value);
    }
    
    /**
     * contains a value
     * @param value
     * @return 
     */
    public boolean contains(T value) {
        return order.le(this.start, value) && order.lt(value, this.end);
    }
    
    /**
     * contains every value in values
     * @param values
     * @return 
     */
    public boolean contains(List<T> values) {
        return values.stream().allMatch(this::contains);
    }
    
    public boolean contains(Range<T> another) {
        return !another.hasLowerThan(start) && !another.hasUpperThan(end);
    }
    
    public boolean overlaps(List<T> values) {
        return values.stream().anyMatch(this::contains);
    }
    
    public boolean overlaps(Range<T> another) {
        return another.hasUpperThan(start) && another.hasLowerThan(end);
    }

    /**
     * the width of a range start to value is not zero
     * @param value
     * @return 
     */
    public boolean hasLowerThan(T value) {
        return order.lt(this.start, value);
    }
    
    /**
     * the width of a range value to end is not zero
     * @param value
     * @return 
     */
    public boolean hasUpperThan(T value) {
        return order.lt(value, this.end);
    }
    
    public boolean startsUpperThan(Range<T> another) {
        return another.hasLowerThan(start);
    }
    
    public boolean endsLowerThan(Range<T> another) {
        return another.hasUpperThan(end);
    }
    
    public boolean isLowerThan(Range<T> another) {
        return !another.hasLowerThan(end);
    }
    
    public boolean isUpperThan(Range<T> another) {
        return another.isLowerThan(this);
    }
    
    public boolean isBelow(T value) {
        return order.le(end, value);
    }
    
    public boolean isAbove(T value) {
        return order.lt(value, start);
    }
    
    public boolean adjoins(Range<T> another) {
        return adjoinsAtStartWith(another) || adjoinsAtEndWith(another);
    }
    
    public boolean adjoinsAtStartWith(Range<T> another) {
        return another.endsAt(start);
    }

    public boolean adjoinsAtEndWith(Range<T> another) {
        return another.startsAt(end);
    }
    
    public Optional<Range<T>> intersect(Range<T> another) {
        return another.getUpper(start).flatMap(r->r.getLower(end));
    }
    
    /**
     * extend the range according to the added point.
     * added point is not necessarily included. specifically this range is below
     * the added point, the point is not included in this range.
     * @param value
     * @return 
     */
    public Range<T> cover(T value) {
        if (!hasUpperThan(value))
            return new Range<>(start, value, order);
        if (!hasLowerThan(value))
            return new Range<>(value, end, order);
        return this;
    }
    
    public Range<T> cover(Range<T> another) {
        return another.cover(start).cover(end);
    }
    
    public Range<T> cover(Optional<Range<T>> another) {
        return another.map(p->cover(p)).orElse(this);
    }
    
    
    public Optional<Range<T>> getLower(T value) {
        if (!hasLowerThan(value))
            return Optional.empty();
        return Optional.of(new Range<>(this.start, hasUpperThan(value) ? value : this.end, order));
    }
    
    public Optional<Range<T>> getUpper(T value) {
        if (!hasUpperThan(value))
            return Optional.empty();
        return Optional.of(new Range<>(hasLowerThan(value) ? value : this.start, this.end, order));
    }
    
    public Optional<Range<T>> getLowerRoomIn(Range<T> another) {
        return another.getLower(start);
    }
    
    public Optional<Range<T>> getUpperRoomIn(Range<T> another) {
        return another.getUpper(end);
    }
    
    public double width(Function<T,Double> f) {
        return abs(f.apply(end)-f.apply(start));
    }
    
    public double interpolate(Function<T,Double> f, double rate) {
        return f.apply(start)*(1-rate)+f.apply(end)*rate;
    }
    
    public static <T extends Comparable<? super T>> Optional<Range<T>> cover(TList<Range<T>> rl) {
        if (rl.isEmpty()) return Optional.empty();
        return Optional.of(new Range<>(rl.map(r->r.start).min((a,b)->a.compareTo(b)).get(),rl.map(r->r.end).max((a,b)->a.compareTo(b)).get()));
    }

    static public <T extends Comparable<? super T>> TList<Range<T>> sortToStart(TList<Range<T>> ranges) {
        return ranges.sortTo(inc(r->r.start()));
    }
    /**
     * remove punches from this range.
     * @param sorted
     * @return is in order without overlapping, thus can form RangeSet.
     */
    public Iterator<Range<T>> negateIteratorIfLucky(Iterator<Range<T>> sorted) {
        return new Negate(sorted,this);
    }
    public TList<Range<T>> negateIfLucky(TList<Range<T>> sorted) {
        return TList.set(i2l(negateIteratorIfLucky(sorted.iterator())));
    }
    public Iterator<Range<T>> negateIterator(TList<Range<T>> punches) {
        return negateIteratorIfLucky(sortToStart(punches).iterator());
    }
    public TList<Range<T>> negate(TList<Range<T>> punches) {
        return negateIfLucky(sortToStart(punches));
    }
    static class Negate<T extends Comparable<? super T>> extends AbstractBufferedIterator<Range<T>> {
        Iterator<Range<T>> iter;
        Optional<Range<T>> rest;
        public Negate(TList<Range<T>> sorted, Range<T> scope) {
            rest = scope.isEmpty()?Optional.empty():Optional.of(scope);
            iter = sorted.iterator();
        }
        public Negate(Iterator<Range<T>> iter, Range<T> scope) {
            rest = scope.isEmpty()?Optional.empty():Optional.of(scope);
            this.iter = iter;
        }
        @Override
        protected void findNext() {
            while (iter.hasNext()) {
                if (rest.isEmpty())
                    return;
                Range<T> next = iter.next();
                if (!next.overlaps(rest.get()))
                    continue;
                Optional<Range<T>> retval = next.getLowerRoomIn(rest.get());
                rest = next.getUpperRoomIn(rest.get());
                if (!retval.isPresent())
                    continue;
                nextFound(retval.get());
                return;
            }
            if (rest.isPresent())
                nextFound(rest.get());
            rest = Optional.empty();
        }
    }
    /**
     * union of punches.
     * @param punches
     * @return is in order without overlapping, thus can form RangeSet.
     */
    public Iterator<Range<T>> unionIteratorIfLucky(Iterator<Range<T>> punches) {
        return negateIteratorIfLucky(negateIteratorIfLucky(punches));
    }
    static public <T extends Comparable<? super T>> TList<Range<T>> unionIfLucky(TList<Range<T>> sorted) {
        return TList.set(i2l(cover(sorted).map(w->w.unionIteratorIfLucky(sorted.iterator())).orElse(TList.<Range<T>>empty().iterator())));
    }
    static public <T extends Comparable<? super T>> Iterator<Range<T>> unionIterator(TList<Range<T>> punches) {
        return cover(punches).map(w->w.unionIteratorIfLucky(sortToStart(punches).iterator())).orElse(TList.<Range<T>>empty().iterator());
    }
    static public <T extends Comparable<? super T>> TList<Range<T>> union(TList<Range<T>> punches) {
        return unionIfLucky(sortToStart(punches));
    }
    /**
     * unary intersect.
     * @param <T>
     * @param punches
     * @return 
     */
    static public <T extends Comparable<? super T>> TList<Optional<Range<T>>> intersectSeq(TList<Range<T>> punches) {
        if (punches.isEmpty()) return TList.empty();
        return punches.accumFromStart(s->Optional.of(s),(o,x)->o.flatMap(r->r.intersect(x)));
    }
    static public <T extends Comparable<? super T>> Optional<Range<T>> intersect(TList<Range<T>> punches) {
        if (punches.isEmpty()) return Optional.empty();
        return intersectSeq(punches).last();
    }
    /**
     * binary intersect.
     * @param a
     * @param b
     * @return 
     */
    public Iterator<Range<T>> intersectIteratorIfLucky(Iterator<Range<T>> a,Iterator<Range<T>>b) {
        return negateIteratorIfLucky(unionIteratorIfLucky(new MergeIterator<>(negateIteratorIfLucky(a),negateIteratorIfLucky(b),(Range<T> x,Range<T>y)->x.start().compareTo(y.start()))));
    }
    static public <T extends Comparable<? super T>> TList<Range<T>> intersectIfLucky(TList<Range<T>> aSorted, TList<Range<T>> bSorted) {
        return TList.set(i2l(cover(aSorted.append(bSorted)).map(w->w.intersectIteratorIfLucky(aSorted.iterator(),bSorted.iterator())).orElse(TList.<Range<T>>empty().iterator())));
    }
    static public <T extends Comparable<? super T>> Iterator<Range<T>> intersectIterator(TList<Range<T>> a, TList<Range<T>> b) {
        return cover(a.append(b)).map(w->w.intersectIteratorIfLucky(sortToStart(a).iterator(),sortToStart(b).iterator())).orElse(TList.<Range<T>>empty().iterator());
    }
    static public <T extends Comparable<? super T>> TList<Range<T>> intersect(TList<Range<T>> a, TList<Range<T>> b) {
        return intersectIfLucky(sortToStart(a),sortToStart(b));
    }
    /**
     * overlap. (binary, because there is no such thing like unaly overlap.)
     * @param a
     * @param b
     * @return 
     */
    public boolean overlapIfLucky(Iterator<Range<T>> a, Iterator<Range<T>> b) {
        return intersectIteratorIfLucky(a,b).hasNext();
    }
    static public <T extends Comparable<? super T>> boolean overlap(TList<Range<T>> a, TList<Range<T>> b) {
        return cover(a.append(b)).map(w->w.overlapIfLucky(sortToStart(a).iterator(),sortToStart(b).iterator())).orElse(false);
    }
        
    /**
     * negate the cover.
     * @param <T>
     * @param punches
     * @return 
     */
    static public <T extends Comparable<? super T>> TList<Range<T>> negateCover(TList<Range<T>> punches) {
        return cover(punches).map(w->w.negate(punches)).orElse(TList.empty());
    }

    @Override
    public String toString() {
        return Message.nl(start).c("<->").c(end).toString();
    }
    
    public T clip(T x) {
        if (order.lt(x, start))
            return start;
        if (order.ge(x, end))
            return end;
        return x;
    }
    
    public T start() {
        return start;
    }
    
    public T end() {
        return end;
    }
    
}
