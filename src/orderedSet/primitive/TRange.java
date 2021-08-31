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
public interface TRange<T> {
    TRangeBuilder<T> builder();
    boolean contains(T v);
    boolean contains(TRange<T> other);
    boolean overlaps(TRange<T> other);
    default TRange<T> and(TRange<T> other) {
        return builder().and(this, other);
    }
    default TRange<T> or(TRange<T> other) {
        return builder().and(this, other);
    }
    TRange<T> negate();
}
