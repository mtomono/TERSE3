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
public interface ContextOrdered<K,T extends ContextOrdered<K,T>> extends Context<K,T>,ChainedCompare<T>{
    default boolean isZero() {
        return self().eq(zero());
    }
    default boolean ltZero() {
        return self().lt(zero());
    }
    default boolean leZero() {
        return self().le(zero());
    }
    default boolean gtZero() {
        return self().gt(zero());
    }
    default boolean geZero() {
        return self().ge(zero());
    }
}
