/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.P;
import collection.TList;
import function.Holder;
import java.util.Optional;
import math.Context;
import math.ContextOrdered;
import static math.matrix.Householder.localQ;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class QrDecomposeH<K, T extends Context<K,T>&ContextOrdered<K,T>> {
    CMatrix<K,T> target;
    public QrDecomposeH(CMatrix<K,T> target) {
        this.target=target;
    }
    
    public QR<K,T> decompose() {
        Holder<CMatrix> rh=new Holder<>(target);
        Holder<CMatrix> qh=new Holder<>(target.i().sfix());
        TList.range(0,target.y-1).iterator()
                .map(i->rh.get().subMatrixLR(i,i))
                .map(s->P.p(s,localQ(s))).tee(rq->qh.set(Optional.of(qh.get().subMatrixLR(rq.l().x,rq.l().y)).map(qc->qc.reset(rq.r().mul(qc))).get()))
                .map(rq->rq.l().reset(rq.r().mul(rq.l()))).forEachRemaining(v->{});
        return new QR<>(TList.sof(qh.get(),rh.get()));
    }

}
