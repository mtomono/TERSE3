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
import java.util.List;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class QR<K,T extends Context<K,T>&ContextOrdered<K,T>> extends TList<CMatrix<K,T>> {
    public QR(List<CMatrix<K, T>> body) {
        super(body);
    }
    public QR(CMatrix<K, T>... body) {
        this(TList.sof(body));
    }
    public CMatrix<K,T> q() {
        return qinv().transpose();
    }
    public CMatrix<K,T> qinv() {
        return get(0);
    }
    public CMatrix<K,T> r() {
        return last(0);
    }
    public CMatrix<K,T> rinv() {
        return r().invUpper();
    }
    public CList<K,T> solve(CList<K,T> v) {
        return r().backwardSubstitution(qinv().mul(v));
    }
}
