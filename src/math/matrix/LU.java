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
import java.util.List;
import math.C2;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 */
public class LU<K,T extends Context<K,T>&ContextOrdered<K,T>> extends TList<CMatrix<K,T>> {
    public LU(List<CMatrix<K,T>> body) {
        super(body);
    }
    public CMatrix<K,T> l() {
        return last(1);
    }
    public CMatrix<K,T> u() {
        return last(0);
    }
    public CList<K,T> solve(CList<K,T> v) {
        CList<K,T> y=l().forwardSubstitution(v);
        return u().backwardSubstitution(y);
    }
    public CMatrix<K,T> inv() {
        return u().invUpper().mul(l().invLower());
    }
    public T det() {
        return u().getDiagonal().stream().reduce(u().bb.one(), (a,b)->a.mul(b));
    }
    public CMatrix<K,T> restore() {
        return stream().reduce((a,b)->a.mul(b)).get();
    }
}
