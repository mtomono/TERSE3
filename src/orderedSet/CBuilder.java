/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import java.math.BigDecimal;
import math.C2;
import math.C2N;
import math.Context;
import math.ContextBuilder;
import math.ContextOrder;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <C>
 * @param <B>
 */
public class CBuilder<K, C extends Context<K,C>&ContextOrdered<K,C>, B extends ContextBuilder<K,C>&ContextOrder<K,C>> {
    public static CBuilder<Integer,C2<Integer>,C2.Builder<Integer>> intRange2 = b(C2.i);
    public static CBuilder<Long,C2<Long>,C2.Builder<Long>> longRange2 = b(C2.l);
    public static CBuilder<Double,C2<Double>,C2.Builder<Double>> doubleRange2= b(C2.d);
    public static CBuilder<BigDecimal,C2<BigDecimal>,C2.Builder<BigDecimal>> bdRange2= b(C2.bd);
    public static CBuilder<Integer,C2N<Integer>,C2N.Builder<Integer>> intRange2n = b(C2N.i);
    public static CBuilder<Long,C2N<Long>,C2N.Builder<Long>> longRange2n = b(C2N.l);
    public static CBuilder<Double,C2N<Double>,C2N.Builder<Double>> doubleRange2n= b(C2N.d);
    public static CBuilder<BigDecimal,C2N<BigDecimal>,C2N.Builder<BigDecimal>> bdRange2n= b(C2N.bd);
    static public <K, C extends Context<K,C>&ContextOrdered<K,C>, B extends ContextBuilder<K,C>&ContextOrder<K,C>> CBuilder<K,C,B> b(B b) {
        return new CBuilder<>(b);
    }
    final B b;
    public CBuilder(B b) {
        this.b=b;
    }
    public Range<C> r(K start, K end) {
        return new Range<>(b.c(start),b.c(end),b);
    }
    public Range<C> r(C start, C end) {
        return new Range<>(start,end,b);
    }
    public Range<C> inEitherWay(K one, K two) {
        return b.baseOrder().lt(one,two)?r(one,two):r(two,one);
    }
}
