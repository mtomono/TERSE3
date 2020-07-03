/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
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
    default K scale(Q other) {
        compatible(other);
        return other.amount().div(amount());
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
    default Exchange<K,U> exchange(Q other) {
        return new Exchange(other.amount().div(amount()), unit(), other.unit());
    }
    default Q exchange(Exchange<K,U> exchange) {
        assert unit().equals(exchange.from) : "wrong exchange is applied";
        return create(amount().mul(exchange.rate), exchange.to);
    }
}
