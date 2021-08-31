/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet.primitive;

/**
 *
 * @author masao
 * @param <T>
 */
public class Whole<T> implements TRange<T> {
    final public TRangeBuilder<T> builder;
    public Whole(TRangeBuilder<T> builder) {
        this.builder=builder;
    }
    @Override
    public T representative() {
        throw new UnsupportedOperationException("whole doesn't have representative value");
    }
    @Override
    public TRangeBuilder<T> builder() {
        return builder;
    }
    @Override
    public boolean contains(T v) {
        return true;
    }
    @Override
    public boolean contains(TRange<T> other) {
        return true;
    }
    @Override
    public boolean overlaps(TRange<T> other) {
        return !(other instanceof None);
    }
    @Override
    public TRange<T> negate() {
        return builder.none();
    }
    @Override
    public String toString() {
        return "whole";
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Whole)) {
            return false;
        }
        return true;
    }
}
