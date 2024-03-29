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

import iterator.TIterator;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class Jacobi <K, T extends Context<K,T>&ContextOrdered<K,T>> {
    final CMatrix<K,T> A;
    final CMatrix<K,T> Dinv;
    final CMatrix<K,T> negLU;
    public Jacobi(CMatrix<K,T> A) {
        this.A=A;
        this.Dinv=A.fillAll(A.bb.zero()).fillDiagonal(A.getDiagonal().map(t->t.inv()));
        this.negLU=A.sfix().fillDiagonal(A.bb.zero()).negate();
    }
    public Convergence target(CList<K,T> b) {
        return new Convergence(b);
    }
    public boolean isStrictDiagonallyDominant() {
        return negLU.map(v->v.abs()).rows().map(r->r.sigma()).pair(A.getDiagonal().map(v->v.abs()),(a,b)->a.lt(b)).forAll(t->t);
    }
    public class Convergence {
        final CList<K,T> b;
        Convergence(CList<K,T> b) {
            this.b=b;
        }
        public CList<K,T> next(CList<K,T> v) {
            return Dinv.mul(negLU.mul(v).add(b)).sfix();
        }
        public TIterator<CList<K,T>> conv(CList<K,T> init) {
            assert isStrictDiagonallyDominant() : "Jacobi method will not converge with this matrix";
            return TIterator.iterate(init, vp->next(vp));
        }
        public TIterator<CList<K,T>> conv(CList<K,T> init, T threshold) {
            return conv(init).until((pre,now)->pre.sub(now).l2().lt(threshold));
        }
    }
}
