/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class QrDecompose<K,T extends Context<K,T>&ContextOrdered<K,T>> {
    final CMatrix<K,T> target;
    public QrDecompose(CMatrix<K,T> target) {
        this.target=target.sfix();
    }

    public QR<K,T> decompose() {
        QR<K,T> retval=new QR<>(exec());
        return retval;
    }
    
    public CMatrix<K,T> qinv() {
        return target.wrap(GramSchmidt.orthogonalize(target.columns()).map(p->p.body()));
    }
    
    public CMatrix<K,T> r(CMatrix<K,T> qinv) {
        return qinv.mul(target);
    }
    
    public CMatrix<K,T> r() {
        return qinv().mul(target);
    }
    
    public TList<CMatrix<K,T>> exec() {
        target.assertSquare();
        CMatrix<K,T> qinv=qinv();
        CMatrix<K,T> r=r(qinv);
        return TList.sof(qinv,r);
    }
}
