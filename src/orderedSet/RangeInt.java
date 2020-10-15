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

import collection.ArrayInt;
import collection.TList;
import static collection.c.i2l;
import static function.ComparePolicy.inc;
import iterator.AbstractBufferedIterator;
import iterator.BufferedIterator;
import iterator.MergeIterator;
import java.util.*;
import static orderedSet.Default.order;
import string.Message;

/**
 * 
 * @author mtomono
 * @param <T>
 */
public class RangeInt {
    public final int start;
    public final int end;
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof RangeInt)) {
            return false;
        }
        RangeInt t = (RangeInt) e;
        return this.start==t.start&&this.end==t.end;
    }
    
    public boolean equalsAsPoint(int t) {
        return start==end&&start==t;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.start);
        hash = 29 * hash + Objects.hashCode(this.end);
        return hash;
    }
    
    @Override
    public RangeInt clone() {
        return new RangeInt(start, end);
    }
    
    public RangeInt(int start, int end) {
        assert start<end : "start=" + start + ":end = " + end;
        this.start = start;
        this.end = end;
    }
        
    static public List<RangeInt> c(int... range) {
        if (range.length == 0) {
            return Collections.<RangeInt>emptyList();
        } else if (range.length % 2 != 0) {
            throw new RuntimeException("RangeInt.c(int...) illegal number of parameters");
        } else {
            List<RangeInt> retval = new ArrayList<>(range.length / 2);
            for (int i = 0; i < range.length; i += 2) {
                retval.add(new RangeInt(range[i], range[i+1]));
            }
            return retval;
        }
    }

    static public List<RangeInt> c(ArrayInt range) {
        if (range.length()==0) {
            return Collections.<RangeInt>emptyList();
        } else if (range.length() % 2 != 0) {
            throw new RuntimeException("RangeInt.c(int...) illegal number of parameters");
        } else {
            List<RangeInt> retval = new ArrayList<>(range.length() / 2);
            for (int i = 0; i < range.length(); i += 2) {
                retval.add(new RangeInt(range.get(i), range.get(i+1)));
            }
            return retval;
        }
    }

    public boolean isEmpty() {
        return start==end;
    }
    
    public boolean startsAt(int value) {
        return start==value;
    }
    
    public boolean endsAt(int value) {
        return end==value;
    }
    
    /**
     * contains a value
     * @param value
     * @return 
     */
    public boolean contains(int value) {
        return this.start<=value&&value<this.end;
    }
    
    /**
     * contains every value in values
     * @param values
     * @return 
     */
    public boolean contains(ArrayInt values) {
        return values.stream().allMatch(this::contains);
    }
    
    public boolean contains(RangeInt another) {
        return !another.hasLowerThan(start) && !another.hasUpperThan(end);
    }
    
    public boolean overlaps(ArrayInt values) {
        return values.stream().anyMatch(this::contains);
    }
    
    public boolean overlaps(RangeInt another) {
        return another.hasUpperThan(start) && another.hasLowerThan(end);
    }

    /**
     * the width of a range start to value is not zero
     * @param value
     * @return 
     */
    public boolean hasLowerThan(int value) {
        return order.lt(this.start, value);
    }
    
    /**
     * the width of a range value to end is not zero
     * @param value
     * @return 
     */
    public boolean hasUpperThan(int value) {
        return order.lt(value, this.end);
    }
    
    public boolean startsUpperThan(RangeInt another) {
        return another.hasLowerThan(start);
    }
    
    public boolean endsLowerThan(RangeInt another) {
        return another.hasUpperThan(end);
    }
    
    public boolean isLowerThan(RangeInt another) {
        return !another.hasLowerThan(end);
    }
    
    public boolean isUpperThan(RangeInt another) {
        return another.isLowerThan(this);
    }
    
    public boolean isBelow(int value) {
        return order.le(end, value);
    }
    
    public boolean isAbove(int value) {
        return order.lt(value, start);
    }
    
    public boolean adjoins(RangeInt another) {
        return adjoinsAtStartWith(another) || adjoinsAtEndWith(another);
    }
    
    public boolean adjoinsAtStartWith(RangeInt another) {
        return another.endsAt(start);
    }

    public boolean adjoinsAtEndWith(RangeInt another) {
        return another.startsAt(end);
    }
    
    public Optional<RangeInt> intersect(RangeInt another) {
        return another.getUpper(start).flatMap(r->r.getLower(end));
    }
    
    /**
     * extend the range according to the added point.
     * added point is not necessarily included. specifically this range is below
     * the added point, the point is not included in this range.
     * @param value
     * @return 
     */
    public RangeInt cover(int value) {
        if (!hasUpperThan(value))
            return new RangeInt(start, value);
        if (!hasLowerThan(value))
            return new RangeInt(value, end);
        return this;
    }
    
    public RangeInt cover(RangeInt another) {
        return another.cover(start).cover(end);
    }
    
    public RangeInt cover(Optional<RangeInt> another) {
        return another.map(p->cover(p)).orElse(this);
    }
    
    
    public Optional<RangeInt> getLower(int value) {
        if (!hasLowerThan(value))
            return Optional.empty();
        return Optional.of(new RangeInt(this.start, hasUpperThan(value) ? value : this.end));
    }
    
    public Optional<RangeInt> getUpper(int value) {
        if (!hasUpperThan(value))
            return Optional.empty();
        return Optional.of(new RangeInt(hasLowerThan(value) ? value : this.start, this.end));
    }
    
    public Optional<RangeInt> getLowerRoomIn(RangeInt another) {
        return another.getLower(start);
    }
    
    public Optional<RangeInt> getUpperRoomIn(RangeInt another) {
        return another.getUpper(end);
    }
    
    public int width() {
        return end-start;
    }
    
    public double interpolate(double rate) {
        return start*(1-rate)+end*rate;
    }
    
    public static Optional<RangeInt> cover(TList<RangeInt> rl) {
        if (rl.isEmpty()) return Optional.empty();
        return Optional.of(new RangeInt(rl.map(r->r.start).min((a,b)->a.compareTo(b)).get(),rl.map(r->r.end).max((a,b)->a.compareTo(b)).get()));
    }

    static public TList<RangeInt> sortToStart(TList<RangeInt> ranges) {
        return ranges.sortTo(inc(r->r.start()));
    }
    /**
     * remove punches from this range.
     * @param sorted
     * @return is in order without overlapping, thus can form RangeSet.
     */
    public Iterator<RangeInt> negateIteratorIfLucky(Iterator<RangeInt> sorted) {
        return new Negate(sorted,this);
    }
    public TList<RangeInt> negateIfLucky(TList<RangeInt> sorted) {
        return TList.set(i2l(negateIteratorIfLucky(sorted.iterator())));
    }
    public Iterator<RangeInt> negateIterator(TList<RangeInt> punches) {
        return negateIteratorIfLucky(sortToStart(punches).iterator());
    }
    public TList<RangeInt> negate(TList<RangeInt> punches) {
        return negateIfLucky(sortToStart(punches));
    }
    static class Negate extends AbstractBufferedIterator<RangeInt> {
        Iterator<RangeInt> iter;
        Optional<RangeInt> rest;
        public Negate(TList<RangeInt> sorted, RangeInt scope) {
            rest = scope.isEmpty()?Optional.empty():Optional.of(scope);
            iter = sorted.iterator();
        }
        public Negate(Iterator<RangeInt> iter, RangeInt scope) {
            rest = scope.isEmpty()?Optional.empty():Optional.of(scope);
            this.iter = iter;
        }
        @Override
        protected void findNext() {
            while (iter.hasNext()) {
                if (rest.isEmpty())
                    return;
                RangeInt next = iter.next();
                if (!next.overlaps(rest.get()))
                    continue;
                Optional<RangeInt> retval = next.getLowerRoomIn(rest.get());
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
    public Iterator<RangeInt> unionIteratorIfLucky(Iterator<RangeInt> punches) {
        return negateIteratorIfLucky(negateIteratorIfLucky(punches));
    }
    static public TList<RangeInt> unionIfLucky(TList<RangeInt> sorted) {
        return TList.set(i2l(cover(sorted).map(w->w.unionIteratorIfLucky(sorted.iterator())).orElse(TList.<RangeInt>empty().iterator())));
    }
    static public Iterator<RangeInt> unionIterator(TList<RangeInt> punches) {
        return cover(punches).map(w->w.unionIteratorIfLucky(sortToStart(punches).iterator())).orElse(TList.<RangeInt>empty().iterator());
    }
    static public TList<RangeInt> union(TList<RangeInt> punches) {
        return unionIfLucky(sortToStart(punches));
    }
    /**
     * unary intersect.
     * @param punches
     * @return 
     */
    static public TList<Optional<RangeInt>> intersectSeq(TList<RangeInt> punches) {
        if (punches.isEmpty()) return TList.empty();
        return punches.accumFromStart(s->Optional.of(s),(o,x)->o.flatMap(r->r.intersect(x)));
    }
    static public Optional<RangeInt> intersect(TList<RangeInt> punches) {
        if (punches.isEmpty()) return Optional.empty();
        return intersectSeq(punches).last();
    }
    /**
     * binary intersect.
     * @param a
     * @param b
     * @return 
     */
    public Iterator<RangeInt> intersectIteratorIfLucky(Iterator<RangeInt> a,Iterator<RangeInt>b) {
        return negateIteratorIfLucky(unionIteratorIfLucky(new MergeIterator<>(negateIteratorIfLucky(a),negateIteratorIfLucky(b),inc(x->x.start()))));
    }
    static public TList<RangeInt> intersectIfLucky(TList<RangeInt> aSorted, TList<RangeInt> bSorted) {
        return TList.set(i2l(cover(aSorted.append(bSorted)).map(w->w.intersectIteratorIfLucky(aSorted.iterator(),bSorted.iterator())).orElse(TList.<RangeInt>empty().iterator())));
    }
    static public Iterator<RangeInt> intersectIterator(TList<RangeInt> a, TList<RangeInt> b) {
        return cover(a.append(b)).map(w->w.intersectIteratorIfLucky(sortToStart(a).iterator(),sortToStart(b).iterator())).orElse(TList.<RangeInt>empty().iterator());
    }
    static public TList<RangeInt> intersect(TList<RangeInt> a, TList<RangeInt> b) {
        return intersectIfLucky(sortToStart(a),sortToStart(b));
    }
    /**
     * overlap. (binary, because there is no such thing like unaly overlap.)
     * @param a
     * @param b
     * @return 
     */
    public boolean overlapIfLucky(Iterator<RangeInt> a, Iterator<RangeInt> b) {
        return intersectIteratorIfLucky(a,b).hasNext();
    }
    static public boolean overlap(TList<RangeInt> a, TList<RangeInt> b) {
        return cover(a.append(b)).map(w->w.overlapIfLucky(sortToStart(a).iterator(),sortToStart(b).iterator())).orElse(false);
    }
    
    static public TList<Optional<RangeInt>> categorize(TList<RangeInt> category, ArrayInt points) {
        if (category.isEmpty()) return TList.empty();
        if (points.isEmpty()) return category.map(c->Optional.empty());
        BufferedIterator<RangeInt> citer=new BufferedIterator(category.iterator());
        ArrayInt.BufferedIterator piter=new ArrayInt.BufferedIterator(points.index().iterator());
        citer.next();
        piter.next();
        TList<Optional<RangeInt>> retval=TList.c();
        int start;
        int end;
        while (true) {
            while (citer.peek().isBelow(points.get(piter.peek()))&&citer.hasNext()) {retval.add(Optional.empty());citer.next();}
            while (citer.peek().isAbove(points.get(piter.peek()))&&piter.hasNext()) piter.next();
            //if (!citer.peek().contains(points.get(piter.peek()))&&(citer.hasNext()||piter.hasNext())) continue;
            if (citer.peek().contains(points.get(piter.peek()))) {
                start=piter.peek();
                end=piter.next();
                while (citer.peek().contains(points.get(piter.peek()))) 
                    if (piter.hasNext()) end=piter.next(); else end=piter.peek()+1;
                retval.add(Optional.of(new RangeInt(start,end)));
            } else {
                retval.add(Optional.empty());
            }
            if (!piter.hasNext())
                while (citer.hasNext()) {retval.add(Optional.empty());citer.next();}
            if (!citer.hasNext())
                return retval;
            else
                citer.next();
        }
    }
    /**
     * negate the cover.
     * @param punches
     * @return 
     */
    static public TList<RangeInt> negateCover(TList<RangeInt> punches) {
        return cover(punches).map(w->w.negate(punches)).orElse(TList.empty());
    }

    @Override
    public String toString() {
        return Message.nl(start).c("<->").c(end).toString();
    }
    
    public int clip(int x) {
        if (x<start)
            return start;
        if (x<=end)
            return end;
        return x;
    }
    
    public int start() {
        return start;
    }
    
    public int end() {
        return end;
    }
    
}
