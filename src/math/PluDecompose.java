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

import collection.ArrayInt;
import collection.TList;

/**
 *
 * @author masao
 * @param <K>
 */
public class PluDecompose<K extends Comparable<K>> extends LuDecompose<K> {
    final ArrayInt order;
    public PluDecompose(CMatrix<K> target) {
        super(target);
        order=ArrayInt.range(0, target.body.size()).fix();
    }
    @Override
    public PLU<K> decompose() {
        return new PLU<>(doolittle().startFrom(p()),order);
    }
    public CMatrix<K> pinv() {
        return target.pinv(order.asList());
    }
    public CMatrix<K> p() {
        return pinv().transpose();
    }
    @Override
    public PluDecompose<K> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).forEach(i->target.swap(i,order).subMatrixUR(i,i).doolittleSubMatrix());
        return this;
    }

}
