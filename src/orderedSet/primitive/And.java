/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet.primitive;

/**
 *
 * @author masao
 */
public class And<T> implements TRange<T> {
    final public TRange<T> a;
    final public TRange<T> b;
    public And(TRange<T> a, TRange<T> b) {
        this.a=a;
        this.b=b;
    }
    @Override
    public T representative() {
        return a.representative();
    }
    public TRangeBuilder<T> builder() {
        return a.builder();
    }
    @Override
    public boolean contains(T v) {
        return a.contains(v)&&b.contains(v);
    }
    public boolean contains(TRange<T> other) {
        return a.contains(other)&&b.contains(other);
    }
    public boolean overlaps(TRange<T> other) {
        return a.overlaps(other)&&b.overlaps(other);
    }
    @Override
    public TRange<T> negate() {
        return builder().or(a.negate(),b.negate());
    }
    @Override
    public String toString() {
        return "("+a+"&&"+b+")";
    }
}
