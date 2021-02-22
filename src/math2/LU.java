/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

import collection.TList;
import java.util.List;

/**
 *
 * @author masao
 * @param <K>
 */
public class LU<K extends Comparable<K>> extends TList<CMatrix<K>> {
    public LU(List<CMatrix<K>> body) {
        super(body);
    }
    public CMatrix<K> l() {
        return last(1);
    }
    public CMatrix<K> u() {
        return last(0);
    }
    public CList<K> solve(CList<K> v) {
        CList<K> y=l().forwardSubstitution(v);
        return u().backwardSubstitution(y);
    }
    public CMatrix<K> inv() {
        return u().invUpper().mul(l().invLower());
    }
    public C<K> det() {
        return u().getDiagonal().stream().reduce(u().b.context.one(), (a,b)->a.mul(b));
    }
    public CMatrix<K> restore() {
        return stream().reduce((a,b)->a.mul(b)).get();
    }
}
