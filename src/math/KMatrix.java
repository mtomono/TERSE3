/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class KMatrix<K extends Decimal<K>> {
    static public <K extends Decimal<K>> KMatrix<K> matrix(Integer[][] matrix, Function<Integer,K> f) {
        return matrix(TList.sof(matrix).map(a->TList.sof(a).map(f)));
    }
    static public <K extends Decimal<K>> KMatrix<K> matrix(TList<TList<K>> matrix) {
        return new KMatrix<>(matrix.map(l->new KVector<>(l)));
    }
    TList<KVector<K>> body;
    public KMatrix(TList<KVector<K>> body) {
        this.body=body;
    }
    public KMatrix<K> eliminate(int i) {
        KVector<K> eliminator=body.get(i).eliminator(i);
        return new KMatrix<>(body.subList(0,i).append(eliminator).append(body.seek(i+1).map(v->v.eliminate(eliminator, i))));
    }
    public KMatrix<K> eliminate() {
        KMatrix<K> retval= this;
        for (int i=0; i<body.size(); i++)
            retval=retval.eliminate(i);
        return retval;
    }
    
    public boolean same(KMatrix<K> other) {
        return body.pair(other.body, (a,b)->a.same(b)).forAll(b->b);
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KMatrix)) {
            return false;
        }
        KMatrix<K> t = (KMatrix<K>) e;
        return t.body.equals(body);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public String toString() {
        return body.toString();
    }

}
