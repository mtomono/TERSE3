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
        return scaleR(scale);
    }
    public KVector<K> scaleR(K scale) {
        return new KVector<K>(body.map(v->v.mul(scale)));
    }
    public KVector<K> scaleS(K scale) {
        body.reset(body.map(v->v.mul(scale)));
        return this;
    }
    public KVector<K> inv(K scale) {
        return invR(scale);
    }
    public KVector<K> invR(K scale) {
        return new KVector<K>(body.map(v->v.div(scale)));
    }
    public KVector<K> invS(K scale) {
        body.reset(body.map(v->v.div(scale)));
        return this;
    }
    public KVector<K> add(KVector<K> other) {
        return addR(other);
    }
    public KVector<K> addR(KVector<K> other) {
        return new KVector<K>(body.pair(other.body,(a,b)->a.add(b)));
    }
    public KVector<K> addS(KVector<K> other) {
        body.reset(body.pair(other.body,(a,b)->a.add(b)));
        return this;
    }
    public KVector<K> sub(KVector<K> other) {
        return subR(other);
    }
    public KVector<K> subR(KVector<K> other) {
        return new KVector<K>(body.pair(other.body,(a,b)->a.sub(b)));
    }
    public KVector<K> subS(KVector<K> other) {
        body.reset(body.pair(other.body,(a,b)->a.sub(b)));
        return this;
    }
    public K get(int i) {
        return body.get(i);
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
