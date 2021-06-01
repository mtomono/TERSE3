/*
 Copyright 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package math.matrix;

import collection.TList;
import function.Holder;
import static function.Functions.tee;
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
        Holder<CMatrix<K,T>> rh=new Holder<>(target.sfix());
        Holder<CMatrix<K,T>> qh=new Holder<>(target.i().sfix());
        TList.range(0,target.y-1).iterator()
                .map(i->rh.get().subMatrixLR(i,i))
                .forEachRemaining(rs->Optional.of(localQ(rs).sfix())
                        .map(tee(lq->rh.get().resetLR(lq.mul(rs).sfix())))
                        .map(tee(lq->qh.set(target.i().sfix().resetLR(lq).mul(qh.get()).sfix()))));
        return new QR<>(TList.sof(qh.get(),rh.get()));
    }
    
    /**
     * demopose() equivalent which is simpler to read.
     * but disadvantageous in the perspective of performance.
     * @return 
     */
    public QR<K,T> decompose_reference() {
        Holder<CMatrix<K,T>> rh=new Holder<>(target.sfix());
        Holder<CMatrix<K,T>> qh=new Holder<>(target.i().sfix());
        TList.range(0,target.y-1).iterator().forEachRemaining(i->{
            var rs=rh.get().subMatrixLR(i,i);
            var lq=target.wrap(target.bb.i(i)).appendDiag(localQ(rs).sfix());
            rh.set(lq.mul(rh.get()).sfix());
            qh.set(lq.mul(qh.get()).sfix());
        });
        return new QR<>(TList.sof(qh.get(),rh.get()));
    }

}
