/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.ChainedCompare;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public interface ContextNumber<K extends Number, T extends ContextNumber<K,T>> extends Context<K,T>,Comparable<T>,ChainedCompare<T> {
    default boolean isInteger() {
        return self().eq(intValue());
    }
    default boolean isLong() {
        return self().eq(longValue());
    }
    default T intValue() {
        return b().b(body().intValue());
    }
    default T longValue() {
        return b().b(body().longValue());
    }
    
}
