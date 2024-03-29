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

/**
 *
 * @author masao
 */
public class GramSchmidt {
    public static <K, T extends Context<K,T>> TList<CList<K,T>> orthogonalize(TList<CList<K,T>> base) {
        return base.iterator().reduce(TList.c(), (ob,ak)->ob.addOne(expandOrthonormalBasis(ak,ob).sfix()));
    }
    private static <K, T extends Context<K,T>> CList<K,T> expandOrthonormalBasis(CList<K,T>ak, TList<CList<K,T>> orthonormalBase) {
        return orthonormalBase.stream().map(ei->ei.scale(ak.dot(ei))).reduce(ak,(a,ai)->a.sub(ai)).t(x->x.scale(x.dot(x).sqrt().inv()));
    }
}
