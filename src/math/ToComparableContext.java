/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.ToComparable;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public interface ToComparableContext<S, T extends Comparable<T>> extends ToComparable<S,T> {
    S self();
    default boolean eq(S v) {
        return eq(self(),v);
    }
    default boolean ne(S v) {
        return ne(self(),v);
    }
    default boolean gt(S v) {
        return gt(self(),v);
    }
    default boolean ge(S v) {
        return ge(self(),v);
    }
    default boolean lt(S v) {
        return lt(self(),v);
    }
    default boolean le(S v) {
        return le(self(),v);
    }
}
