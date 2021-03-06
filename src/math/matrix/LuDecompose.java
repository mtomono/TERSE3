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
import math.Context;
import math.ContextOrdered;
import math.NonsingularMatrixException;

/**
 *
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
        return target.doolittleFormat();
    }
    public LuDecompose<K,T> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).forEach(i->target.subMatrixLR(i,i).doolittleSubMatrix());
        return this;
    }
}
