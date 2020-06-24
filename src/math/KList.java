/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <T>
 * @param <K>
 */
public class KList<T,K extends Decimal<K>> {
    DecimalField<K> db;
    TList<T> body;
    public KList(TList<T> body, DecimalField<K> db) {
        this.body=body;
        this.db=db;
    }
    
    public K average(Function<T,K> f) {
        return sigma(f).div(body.size());
    }

    public K sigma(Function<T,K> f) {
        return body.stream().map(f).reduce(db.zero(),(a,b)->a.add(b));
    }
    
    public K pai(Function<T,K> f) {
        return body.stream().map(f).reduce(db.one(),(a,b)->a.mul(b));
    }
    
    public TList<K> add(Function<T,K> f) {
        return body.map(f).accum(db.zero(), (a,b)->a.add(b));
    }

    public TList<K> mul(Function<T,K> f) {
        return body.map(f).accum(db.one(), (a,b)->a.mul(b));
    }
    public TList<T> toT() {
        return body;
    }
}
