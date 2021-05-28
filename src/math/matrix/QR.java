/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import java.util.List;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class QR<K,T extends Context<K,T>&ContextOrdered<K,T>> extends TList<CMatrix<K,T>> {
    public QR(List<CMatrix<K, T>> body) {
        super(body);
    }
    public CMatrix<K,T> q() {
        return last(1).transpose();
    }
    public CMatrix<K,T> qinv() {
        return last(1);
    }
    public CMatrix<K,T> r() {
        return last(0);
    }
    public CList<K,T> solve(CList<K,T> v) {
        return r().backwardSubstitution(qinv().mul(v));
    }
}
