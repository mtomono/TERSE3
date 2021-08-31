/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet.primitive;

import debug.Te;
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
        if (a.contains(b)) return b;
        if (b.contains(a)) return a;
        if (!a.overlaps(b)) return none();
        if (a instanceof And<T> and0) return and(and(and0.a,b),and(and0.b,b));
        if (b instanceof And<T> and0) return and(and(a,and0.a),and(a,and0.b));
        if (a instanceof Or<T> or0) return or(and(or0.a,b),and(or0.b,b));
        if (b instanceof Or<T> or0) return or(and(a,or0.a),and(a,or0.b));
        return new And<>(a,b);
    }

    public TRange<T> or(TRange<T> a, TRange<T> b) {
        if (a.contains(b)) return a;
        if (b.contains(a)) return b;
        if (!a.overlaps(b)) return new Or<>(a,b);
        if (and(a.negate(),b.negate()).equals(none())) return whole();
        if (a instanceof Or<T> or0) return or(or(or0.a,b),or(or0.b,b));
        if (b instanceof Or<T> or0) return or(or(a,or0.a),or(a,or0.b));
        return new Or<>(a,b);
    }
    
//    public TRange<T> cover(TRange<T> a, TRange<T> b) {
    
}
