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
import math.CList;
import math.Context;
import math.ContextBuilder;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class Householder<K, T extends Context<K,T>&ContextOrdered<K,T>> {
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>>CMatrix<K,T> mirror(ContextBuilder<K,T> builder, CList<K,T> x, CList<K,T> y) {
        CList<K,T> u=x.sub(y);
        CMatrix<K,T> U=CMatrix.b(builder).b(TList.sof(u.body()));
        CMatrix<K,T> tUU=U.transpose().mul(U);
        return tUU.i().sub(tUU.scale(u.dot(u).inv().mul(builder.b(2))));
    }
    /**
     * matrix which erase first column.
     * except first row.
     * using mirror().
     * @param <K>
     * @param <T>
     * @param target
     * @return 
     */
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> columnEraserLR(CMatrix<K,T> target) {
        CList<K,T> x=target.columns().get(0).sfix();
        CList<K,T> y=x.zero().m(l->l.replaceAt(0, x.get(0).gtZero()?x.dot(x).sqrt().negate():x.dot(x).sqrt())).sfix();
        return mirror(target.bb,x,y);
    }
    
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> columnEraser(CMatrix<K,T>r, int i) {
        return r.wrap(r.bb.i(i)).appendDiag(columnEraserLR(r.subMatrixLR(i,i)).sfix());
    }
}
