/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import java.util.List;

/**
 *
 * @author masao
 * @param <K>
 */
public class LU<K extends Decimal<K>> extends TList<KMatrix<K>> {
    public LU(List<KMatrix<K>> body) {
        super(body);
    }
    public KMatrix<K> l() {
        return last(1);
    }
    public KMatrix<K> u() {
        return last(0);
    }
    public KVector<K> solve(KVector<K> v) {
        if (!isSingular())
            throw new NonsingularMatrixException("the matrix is not a singular matrix:"+ u().toString() + ": notified from LU.solve()");
        KVector<K> y=l().forwardSubstitution(v);
        return u().backwardSubstitution(y);
    }
    public KMatrix<K> inv() {
        return u().invUpper().mul(l().invLower());
    }
    public boolean isSingular() {
        return u().getDiagonal().stream().allMatch(d->!d.isZero());
    }
    public K det() {
        return u().getDiagonal().stream().reduce(u().context.one(), (a,b)->a.mul(b));
    }
    public KMatrix<K> restore() {
        return stream().reduce((a,b)->a.mul(b)).get();
    }
}
