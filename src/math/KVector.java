/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.P;
import collection.TList;
import java.util.Objects;

/**
 *
 * @author masao
 */
public class KVector<K extends Decimal<K>> {
    TList<K> body;
    static public <K extends Decimal<K>> KVector<K> vec(K... v) {
        return new KVector<>(TList.sof(v));
    }
    public KVector(TList<K> body) {
        this.body=body;
    }
    public KVector<K> scale(K scale) {
        return new KVector<K>(body.map(v->v.mul(scale)));
    }
    public KVector<K> add(KVector<K> other) {
        return new KVector<K>(body.pair(other.body,(a,b)->a.add(b)));
    }
    public KVector<K> sub(KVector<K> other) {
        return new KVector<K>(body.pair(other.body,(a,b)->a.sub(b)));
    }
    public KVector<K> eliminator(int i) {
        return scale(body.get(i).inv());
    }
    public KVector<K> eliminate(KVector<K> eliminator, int i) {
        return sub(eliminator.scale(body.get(i)));
    }
    
    public boolean same(KVector<K> other) {
        return body.pair(other.body, (a,b)->a.same(b)).forAll(b->b);
    }

    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KVector)) {
            return false;
        }
        KVector<K> t = (KVector<K>) e;
        return t.body.equals(body);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
