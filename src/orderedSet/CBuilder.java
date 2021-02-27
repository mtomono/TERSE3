/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import static arithmetic.NaturalOrder.order;
import math.Context;
import math.ContextBuilder;
import math.ContextComparableBuilder;

/**
 *
 * @author masao
 */
public class CBuilder<K extends Comparable<? super K>, C extends Context<K,C>&Comparable<? super C>> {
    static public <K extends Comparable<? super K>, C extends Context<K,C>&Comparable<? super C>> CBuilder<K,C> b(ContextComparableBuilder<K,C> b) {
        return new CBuilder<>(b);
    }
    final ContextComparableBuilder<K,C> b;
    public CBuilder(ContextComparableBuilder<K,C> b) {
        this.b=b;
    }
    public Range<C> r(K start, K end) {
        return new Range<>(b.c(start),b.c(end),b.order());
    }
    public Range<C> r(C start, C end) {
        return new Range<>(start,end,b.order());
    }
}
