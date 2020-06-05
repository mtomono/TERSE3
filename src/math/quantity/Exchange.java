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
 */
public class Exchange<K extends Decimal<K>, U, V> {
    final public U from;
    final public V to;
    final public K rate;
    public Exchange(K rate, U from, V to) {
        this.rate=rate;
        this.from=from;
        this.to=to;
    }
}
