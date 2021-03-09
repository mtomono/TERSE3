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

import collection.TList;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 * LU decompose using Doolittle method.
 * @author masao
 * @param <K>
 * @param <T>
 */
public class LuDecompose<K,T extends Context<K,T>&ContextOrdered<K,T>> {
    final CMatrix<K,T> target;
    public LuDecompose(CMatrix<K,T> target) {
        this.target=target.sfix();
    }

    public LU<K,T> decompose() {
        LU<K,T> retval=new LU<>(exec());
        return retval;
    }
    /**
     * execute decomposition with doolittle method.
     * @return L and U (get(0) and get(1), respectively)
     */
    public TList<CMatrix<K,T>> exec() {
        target.assertSquare();
        doolittle();
        if (!target.nonZeroDiagonal())
            throw new NonsingularMatrixException("non zero element found in diagonal of doolittle result : noticed by LuDecompose.lu()"); 
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        return TList.sof(target.fillUpper(target.bb.zero()).fillDiagonal(target.bb.one()),target.fillLower(target.bb.zero()));
    }
    /**
     * doolittle method as a whole.
     * @return 
     */
    public CMatrix<K,T> doolittle() {
        doolittleSteps().forEach(m->doolittleStep(m));
        return target;
    }
    public TList<CMatrix<K,T>> doolittleSteps() {
        return TList.range(0,target.minSize()-1).map(i->target.subMatrixLR(i,i));
    }
    /**
     * one step of doolittle method.to complete doolittle method, use LuDecompose.
     * @param <K>
     * @param <T>
     * @param target
     * @return 
     */
    public static <K,T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> doolittleStep(CMatrix<K,T> target){
        if (target.get(0,0).isZero())
            throw new PivotingMightBeRequiredException("lu decomposition encountered 0 diagonal element:"+ target.toString() +": notified from CMatrix.doolittleStep");
        CList<K,T>        lcolumn    =target.subRows(1).subColumns(0,1).columns().get(0).reset(cl->cl.scale(target.get(0,0).inv()));
        CList<K,T>        eliminator =target.subRows(0,1).subColumns(1).rows().get(0);
        TList<CList<K,T>> eliminated =target.subMatrixLR(1,1).rows();
        lcolumn.body().pair(eliminated,(l,u)->u.reset(cl->cl.sub(eliminator.scale(l)))).forEach(r->{}); 
        return target;
    }
}
