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
import iterator.AbstractBufferedIterator;
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
    
    /**
     * remove punches from this range.
     * @param punches
     * @return is in order without overlapping, thus can form RangeSet.
     */
    public Iterator<RangeInt> negateIterator(TList<RangeInt> punches) {
        TList<RangeInt> sorted = punches.sortTo((a,b)->a.start-b.start).sfix();
        return new NegateInt(sorted,this);
    }
    /**
     * remove punches from this range.
     * @param punches
     * @return is in order without overlapping, thus can form RangeSet.
     */
    public TList<RangeInt> negate(TList<RangeInt> punches) {
        return TList.set(i2l(negateIterator(punches)));
    }
    
    /**
     * negate the cover.
     * @param <T>
     * @param punches
     * @return 
     */
    static public TList<RangeInt> negateCover(TList<RangeInt> punches) {
        return cover(punches).map(w->w.negate(punches)).orElse(TList.empty());
    }
    /**
     * union the punches.
     * @param <T>
     * @param punches
     * @return is in order without overlapping, thus can form RangeSet.
     */
    static public TList<RangeInt> union(TList<RangeInt> punches) {
        return cover(punches).map(w->w.negate(w.negate(punches))).orElse(TList.empty());
    }
    public static Optional<RangeInt> cover(TList<RangeInt> rl) {
        if (rl.isEmpty()) return Optional.empty();
        return Optional.of(new RangeInt(ArrayInt.extract(rl,r->r.start).min(i->i),ArrayInt.extract(rl,r->r.start).max(i->i)));
    }

    static class NegateInt extends AbstractBufferedIterator<RangeInt> {
        Iterator<RangeInt> iter;
        Optional<RangeInt> rest;
        public NegateInt(TList<RangeInt> sorted, RangeInt scope) {
            rest = scope.isEmpty()?Optional.empty():Optional.of(scope);
            iter = sorted.iterator();
        }
        @Override
        protected void findNext() {
            if (rest.isEmpty())
                return;
            while (iter.hasNext()) {
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
            nextFound(rest.get());
            rest = Optional.empty();
        }
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
