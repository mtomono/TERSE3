/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.quantity;

import java.util.Objects;
import math.Decimal;

/**
 *
 * @author masao
 * @param <K>
 */
public class Exchange<K extends Decimal<K>, U> {
    final public U from;
    final public U to;
    final public K rate;
    public Exchange(K rate, U from, U to) {
        this.rate=rate;
        this.from=from;
        this.to=to;
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Exchange)) {
            return false;
        }
        Exchange t = (Exchange) e;
        return from.equals(t.from)&&to.equals(t.to)&&rate.equals(t.rate);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.from);
        hash = 41 * hash + Objects.hashCode(this.to);
        hash = 41 * hash + Objects.hashCode(this.rate);
        return hash;
    }
    
    public String toString() {
        return "1"+from+"="+rate+to;
    }
}
