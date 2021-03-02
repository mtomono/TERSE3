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
import java.util.List;

/**
 *
 * @author masao
 * @param <K>
 */
public class LU<K extends Comparable<K>> extends TList<CMatrix<K>> {
    public LU(List<CMatrix<K>> body) {
        super(body);
    }
    public CMatrix<K> l() {
        return last(1);
    }
    public CMatrix<K> u() {
        return last(0);
    }
    public CList<K,C2<K>> solve(CList<K,C2<K>> v) {
        CList<K,C2<K>> y=l().forwardSubstitution(v);
        return u().backwardSubstitution(y);
    }
    public CMatrix<K> inv() {
        return u().invUpper().mul(l().invLower());
    }
    public C2<K> det() {
        return u().getDiagonal().stream().reduce(u().bb.one(), (a,b)->a.mul(b));
    }
    public CMatrix<K> restore() {
        return stream().reduce((a,b)->a.mul(b)).get();
    }
}
