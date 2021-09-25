/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

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

import collection.ArrayInt;
import collection.TList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class PluDecompose<K,T extends Context<K,T>&ContextOrdered<K,T>> extends LuDecompose<K,T> {
    final ArrayInt order;
    public PluDecompose(CMatrix<K,T> target) {
        super(target);
        order=ArrayInt.range(0, target.body.size()).fix();
    }
    @Override
    public PLU<K,T> decompose() {
        return new PLU<>(exec().startFrom(p()),order);
    }
    public CMatrix<K,T> pinv() {
        return target.reorder(order.asList());
    }
    public CMatrix<K,T> p() {
        return pinv().transpose();
    }
    @Override
    public TList<CMatrix<K,T>> doolittleSteps() {
        return TList.range(0,target.minSize()-1).map(i->pivot(i,order).subMatrixLR(i,i));
    }

    /**
     * pivot arrangement at i.
     * @param c target column.
     * @param order stores the result of pivoting.
     * @return 
     */
    CMatrix<K,T> pivot(int c, ArrayInt order) {
        if (target.rows<=1)
            return target;
        int maxRow=TList.range(c,target.rows).max(i->target.get(i,c).abs()).get();
        target.body.swap(c,maxRow);
        if (target.get(c,c).isZero())
            throw new NonsingularMatrixException("diagonal element was 0 even after pivoting, meaning this matrix is not singular : notified from CMatrix.swap()");
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        order.swap(c,maxRow);
        return target;
    }

}
