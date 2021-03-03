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
import java.util.List;
import math.C2;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class PLU<K,T extends Context<K,T>&ContextOrdered<K,T>> extends LU<K,T> {
    ArrayInt order;
    public PLU(List<CMatrix<K,T>> body, ArrayInt order) {
        super(body);
        this.order=order;
    }
    public CList<K,T> porder(CList<K,T> v) {
        return v.wrap(v.body().pickUp(order.asList()));
    } 
    public CMatrix<K,T> p() {
        return get(0);
    }
    public CMatrix<K,T> pinv() {
        return p().transpose();
    }
    /**
     * solve with partial pivot.
     * a partial pivot means that it's only changing the order of formulae.
     * so the order of variable xi is not influenced.
     * @param v
     * @return 
     */
    public CList<K,T> solve(CList<K,T> v) {
        return super.solve(porder(v));
    }
    public CMatrix<K,T> inv() {
        return super.inv().mul(pinv());
    }
}
