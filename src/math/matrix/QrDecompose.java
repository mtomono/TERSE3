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
