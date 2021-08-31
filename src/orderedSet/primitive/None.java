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
public class None<T> implements TRange<T> {
    final public TRangeBuilder<T> builder;
    public None(TRangeBuilder<T> builder) {
        this.builder=builder;
    }
    @Override
    public TRangeBuilder<T> builder() {
        return builder;
    }
    @Override
    public boolean contains(T v) {
        return false;
    }
    @Override
    public boolean contains(TRange<T> other) {
        return other instanceof None;
    }
    @Override
    public boolean overlaps(TRange<T> other) {
        return false;
    }
    @Override
    public TRange<T> negate() {
        return builder.whole();
    }
    @Override
    public String toString() {
        return "none";
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof None)) {
            return false;
        }
        return true;
    }
}
