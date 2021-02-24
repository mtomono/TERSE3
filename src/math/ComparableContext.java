/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.CompareUtil;

/**
 *
 * @author masao
 * @param <S>
 */
public interface ComparableContext<S extends Comparable<S>> {
    S self();
    default boolean eq(S v) {
        return CompareUtil.eq(self(),v);
    }
    default boolean ne(S v) {
        return CompareUtil.ne(self(),v);
    }
    default boolean gt(S v) {
        return CompareUtil.gt(self(),v);
    }
    default boolean ge(S v) {
        return CompareUtil.ge(self(),v);
    }
    default boolean lt(S v) {
        return CompareUtil.lt(self(),v);
    }
    default boolean le(S v) {
        return CompareUtil.le(self(),v);
    }
}
