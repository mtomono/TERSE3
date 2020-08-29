/*
 Copyright 2017, 2018 Masao Tomono

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
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author masao
 * @param <K>
 */
public class MathContext<K extends Decimal<K>> {
    public final K ZERO;
    public final K ONE;
    public MathContext(Class<K> clazz) {
        try {
            this.ZERO=(K) clazz.getField("ZERO").get(null);
            this.ONE=(K) clazz.getField("ONE").get(null);
        } catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException ex) {
            throw new RuntimeException("K class has to have static ZERO/ONE field");
        }
    }
    public KMatrix<K> matrix(Integer[][] matrix, Function<Integer,K> f) {
        return matrix(TList.sof(matrix).map(a->TList.sof(a).map(f).sfix()).sfix());
    }
    public KMatrix<K> matrix(TList<TList<K>> body) {
        return new KMatrix<>(body,this);
    }
    public KVector<K> vector(K... v) {
        return vector(TList.sof(v));
    }
    public KVector<K> vector(TList<K> v) {
        return new KVector<>(v,this);
    }
}
