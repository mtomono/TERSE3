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
package math;

import collection.TList;

/**
 *
 * @author masao
 * @param <K>
 */
public class LuDecompose<K extends Comparable<K>> {
    final CMatrix<K> target;
    public LuDecompose(CMatrix<K> target) {
        this.target=target.sfix();
    }

    public LU<K> decompose() {
        LU<K> retval=new LU<>(doolittle());
        return retval;
    }
    public TList<CMatrix<K>> doolittle() {
        target.assertSquare();
        doolittleWholeMatrix();
        if (!target.nonZeroDiagonal())
            throw new NonsingularMatrixException("non zero element found in diagonal of doolittle result : noticed by LuDecompose.lu()"); 
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        return target.doolittleFormat();
    }
    public LuDecompose<K> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).forEach(i->target.subMatrixUR(i,i).doolittleSubMatrix());
        return this;
    }
}
