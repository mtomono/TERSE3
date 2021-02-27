/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import function.NaturalOrder;
import function.Order;
import java.math.BigDecimal;

/**
 *
 * @author masao
 * @param <T>
 */
public class Builder<T extends Comparable<? super T>> {
    public static Builder<Integer> intRange = b();
    public static Builder<Long> longRange = b();
    public static Builder<Double> doubleRange= b();
    public static Builder<BigDecimal> bdRange= b();
    static public <T extends Comparable<? super T>> Builder<T> b() {
        return new Builder<>(new NaturalOrder<T>());
    }
    static public <T extends Comparable<? super T>> Builder<T> b(Order<T> order) {
        return new Builder<>(order);
    }
    final Order<T> order;
    public Builder(Order<T> order) {
        this.order=order;
    }
    public Range<T> r(T from, T to) {
        return new Range<>(from, to, order);
    }
    public Range<T> inEitherWay(T one, T two) {
        return order.lt(one,two)?r(one,two):r(two,one);
    }
}
