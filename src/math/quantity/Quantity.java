/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.quantity;

import math.Decimal;

/**
 *
 * @author masao
 * @param <Q>
 * @param <K>
 * @param <U>
 */
public interface Quantity<Q extends Quantity<Q,K,U>,K extends Decimal<K>, U> extends Comparable<Q> {
    Q create(K amount, U unit);
    K amount();
    U unit();
    default void compatible(Q other) {
        assert unit().equals(other.unit()) : "units are incompatible";
    }
    default Q inherit(K other) {
        return create(other, unit());
    }
    @Override
    default int compareTo(Q other) {
        compatible(other);
        return amount().compareTo(other.amount());
    }
    default Q add(Q other) {
        compatible(other);
        return inherit(amount().add(other.amount()));
    }
    default Q sub(Q other) {
        compatible(other);
        return inherit(amount().sub(other.amount()));
    }
    default K div(Q other) {
        return other.amount().div(amount());
    }
    default K scaleDiv(Q other) {
        return div(other);
    }
    default K scale(Q other) {
        compatible(other);
        return scaleDiv(other);
    }
    default Q scale(K other) {
        return inherit(amount().mul(other));
    }
    default Q abs() {
        return inherit(amount().abs());
    }
    default Q negate() {
        return inherit(amount().negate());
    }
    default K exchangeDiv(Q other) {
        return div(other);
    }
    default Exchange<K,U> exchange(Q other) {
        return new Exchange(exchangeDiv(other), unit(), other.unit());
    }
    default Q exchange(Exchange<K,U> exchange) {
        assert unit().equals(exchange.from) : "wrong exchange is applied";
        return create(amount().mul(exchange.rate), exchange.to);
    }
}
