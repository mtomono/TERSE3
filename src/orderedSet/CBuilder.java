/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import function.Order;
import math.Context;
import math.ContextBuilder;

/**
 *
 * @author masao
 */
public class CBuilder<K, C extends Context<K,C>&Comparable<C>> {
    static public <K, C extends Context<K,C>&Comparable<C>> CBuilder<K,C> b(ContextBuilder<K,C> b, Order<C> order) {
        return new CBuilder<>(b, order);
    }
    static public <K, C extends Context<K,C>&Comparable<C>> CBuilder<K,C> b(ContextBuilder<K,C> b) {
        return new CBuilder<>(b, new NaturalOrder<>());
    }
    final Order<C> order;
    final ContextBuilder<K,C> b;
    public CBuilder(ContextBuilder<K,C> b, Order<C> order) {
        this.order=order;
        this.b=b;
    }
    public Range<C> r(K start, K end) {
        return new Range<>(b.c(start),b.c(end),order);
    }
    public Range<C> r(C start, C end) {
        return new Range<>(start,end,order);
    }
}
