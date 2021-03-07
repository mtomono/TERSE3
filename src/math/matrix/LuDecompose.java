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
import math.NonsingularMatrixException;
import math.PivotingMightBeRequiredException;

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
        LU<K,T> retval=new LU<>(doolittle());
        return retval;
    }
    public TList<CMatrix<K,T>> doolittle() {
        target.assertSquare();
        doolittleWholeMatrix();
        if (!target.nonZeroDiagonal())
            throw new NonsingularMatrixException("non zero element found in diagonal of doolittle result : noticed by LuDecompose.lu()"); 
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        return doolittleFormat();
    }
    public LuDecompose<K,T> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).map(i->target.subMatrixLR(i,i)).forEach(m->doolittleSubMatrix(m));
        return this;
    }
    
    public static <K,T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> doolittleSubMatrix(CMatrix<K,T> m) {
        if (m.get(0,0).isZero())
            throw new PivotingMightBeRequiredException("lu decomposition encountered 0 diagonal element:"+ m.toString() +": notified from CMatrix.doolittleStep");
        CList<K,T>        lcolumn    =m.subRows(1).subColumns(0,1).columns().get(0).reset(cl->cl.scale(m.get(0,0).inv()));
        CList<K,T>        eliminator =m.subRows(0,1).subColumns(1).rows().get(0);
        TList<CList<K,T>> eliminated =m.subMatrixLR(1,1).rows();
        lcolumn.body().pair(eliminated,(l,u)->u.reset(cl->cl.sub(eliminator.scale(l)))).forEach(r->{}); 
        return m;
    }

    public TList<CMatrix<K,T>> doolittleFormat() {
        return TList.sof(doolittleLower(),doolittleUpper());
    }
    public CMatrix<K,T> doolittleLower() {
        return target.fillUpper(target.bb.zero()).fillDiagonal(target.bb.one());
    }
    public CMatrix<K,T> doolittleUpper() {
        return target.fillLower(target.bb.zero());
    }
}
