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
import java.util.List;

/**
 *
 * @author masao
 * @param <K>
 */
public class PLU<K extends Comparable<K>> extends LU<K> {
    ArrayInt order;
    public PLU(List<CMatrix<K>> body, ArrayInt order) {
        super(body);
        this.order=order;
    }
    public CList<K,C2<K>> porder(CList<K,C2<K>> v) {
        return v.wrap(v.body.pickUp(order.asList()));
    } 
    public CMatrix<K> p() {
        return get(0);
    }
    public CMatrix<K> pinv() {
        return p().transpose();
    }
    /**
     * solve with partial pivot.
     * a partial pivot means that it's only changing the order of formulae.
     * so the order of variable xi is not influenced.
     * @param v
     * @return 
     */
    public CList<K,C2<K>> solve(CList<K,C2<K>> v) {
        return super.solve(porder(v));
    }
    public CMatrix<K> inv() {
        return super.inv().mul(pinv());
    }
}
