/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;

/**
 *
 * @author masao
 * @param <K>
 */
public class DecimalCoordinates<K extends Decimal<K>> implements VectorSpace<DecimalCoordinates<K>,K> {
    TList<K> body;
    public void compatible(DecimalCoordinates<K> other) {
        assert body.size()==other.body.size() : "length have to match";
    }
    public DecimalCoordinates(TList<K> body) {
        this.body=body;
    }
    public DecimalCoordinates(K... body) {
        this(TList.sof(body));
    }
    @Override
    public DecimalCoordinates<K> add(DecimalCoordinates<K> other) {
        compatible(other);
        return new DecimalCoordinates<>(body.pair(other.body, (a,b)->a.add(b)).sfix());
    }

    @Override
    public DecimalCoordinates<K> sub(DecimalCoordinates<K> other) {
        compatible(other);
        return new DecimalCoordinates<>(body.pair(other.body, (a,b)->a.sub(b)).sfix());
    }

    @Override
    public DecimalCoordinates<K> scale(K other) {
        return new DecimalCoordinates<>(body.map(v->v.mul(other)).sfix());
    }
    
}
