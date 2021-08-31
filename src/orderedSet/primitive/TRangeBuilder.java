/*
 Copyright 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package orderedSet.primitive;

import java.util.Comparator;

/**
 *
 * @author masao
 */
public class TRangeBuilder<T> {

    public static <T extends Comparable<? super T>> TRangeBuilder<T> natural(Class<T> c) {
        return new TRangeBuilder<>(Comparator.naturalOrder());
    }
    
    public final Comparator<? super T> comparator;
    public final None<T> none;
    public final Whole<T> whole;

    public TRangeBuilder(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.none=new None<>(this);
        this.whole=new Whole<>(this);
    }

    public Comparison<T> ge(T v) {
        return new Comparison.GE<>(v, this);
    }

    public Comparison<T> lt(T v) {
        return new Comparison.LT<>(v, this);
    }

    public Comparison<T> gt(T v) {
        return new Comparison.GT<>(v, this);
    }

    public Comparison<T> le(T v) {
        return new Comparison.LE<>(v, this);
    }

    public TRange<T> eq(T v) {
        return and(ge(v),le(v));
    }

    public TRange<T> ne(T v) {
        return eq(v).negate();
    }
    
    public TRange<T> whole() {
        return whole;
    }
    
    public TRange<T> none() {
        return none;
    }
    
    public TRange<T> and(TRange<T> a, TRange<T> b) {
        if (b.contains(a)) return a;
        if (a.contains(b)) return b;
        if (!a.overlaps(b)) return none();
        if (b instanceof And<T> and0) return and(and(a,and0.a),and(a,and0.b));
        if (a instanceof And<T> and0) return and(and(and0.a,b),and(and0.b,b));
        if (b instanceof Or<T> or0) return or(and(a,or0.a),and(a,or0.b));
        if (a instanceof Or<T> or0) return or(and(or0.a,b),and(or0.b,b));
        return comparator.compare(a.representative(),b.representative())<=0?new And<>(a,b):new And<>(b,a);
    }

    public TRange<T> or(TRange<T> a, TRange<T> b) {
        if (a.contains(b)) return a;
        if (b.contains(a)) return b;
        if (!a.overlaps(b)) return comparator.compare(a.representative(),b.representative())<=0?new Or<>(a,b):new Or<>(b,a);
        if (and(a.negate(),b.negate()).equals(none())) return whole();
        if (b instanceof Or<T> or0) return or(or(a,or0.a),or(a,or0.b));
        if (a instanceof Or<T> or0) return or(or(or0.a,b),or(or0.b,b));
        return comparator.compare(a.representative(),b.representative())<=0?new Or<>(a,b):new Or<>(b,a);
    }
    
//    public TRange<T> cover(TRange<T> a, TRange<T> b) {
    
}
