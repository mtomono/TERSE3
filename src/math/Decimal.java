/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import orderedSet.Range;

/**
 *
 * @author masao
 */
public interface Decimal<K> extends Comparable<K> {
    K zero();
    K one();
    K negate();
    K abs();
    K add(K v);
    K sub(K v);
    K mul(K v);
    K mul(int v);
    K mul(long v);
    K div(K v);
    K div(int v);
    K div(long v);
    default boolean same(K v) {
        return compareTo(v)==0;
    }
    default boolean lt(K v) {
        return compareTo(v)<0;
    }
    default boolean gt(K v) {
        return compareTo(v)>0;
    }
    default boolean isZero() {
        return same(zero());
    }
    default boolean belowZero() {
        return lt(zero());
    }
    default boolean aboveZero() {
        return gt(zero());
    }
    
    public static <K extends Decimal<K>> K width(Range<K> range) {
        return range.end.sub(range.start);
    }
        
}
