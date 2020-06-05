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
 * @param <K>
 * @param <U>
 */
public class Quantity<K extends Decimal<K>, U> {
    final K body;
    final U unit;
    public Quantity(K body, U unit) {
        this.body = body;
        this.unit = unit;
    }
    
    public Quantity<K,U> add(Quantity<K,U> other) {
        compatible(other);
        return new Quantity(body.add(other.body), unit);
    }
    
    public Quantity<K,U> sub(Quantity<K,U> other) {
        compatible(other);
        return new Quantity(body.sub(other.body), unit);
    }
    
    public K scale(Quantity<K,U> other) {
        compatible(other);
        return body.mul(other.body);
    }
    
    public Quantity<K,U> scale(K other) {
        return new Quantity<>(body.div(other),unit);
    }
    
    public <V> Exchange<K,U,V> exchange(Quantity<K,V> other) {
        return new Exchange<>(other.body.div(body), unit, other.unit);
    }
    
    public <V> Quantity<K,V> exchange(Exchange<K,U,V> exchange) {
        assert unit.equals(exchange.from) : "wrong exchange is applied";
        return new Quantity<>(body.mul(exchange.rate), exchange.to);
    }
    
    public void compatible(Quantity<K,U> other) {
        if (!unit.equals(other.unit))
            throw new RuntimeException("incompatible money");
    }
}
