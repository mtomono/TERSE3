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

import function.ChainedCompare;
import function.Order;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public interface ContextOrdered<K,T extends ContextOrdered<K,T>> extends Context<K,T>,ChainedCompare<T>{
    default boolean isZero() {
        return self().eq(zero());
    }
    default boolean ltZero() {
        return self().lt(zero());
    }
    default boolean leZero() {
        return self().le(zero());
    }
    default boolean gtZero() {
        return self().gt(zero());
    }
    default boolean geZero() {
        return self().ge(zero());
    }
    public static <K,T extends ContextOrdered<K,T>> boolean equalsByComparison(T th, Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof ContextOrdered)) {
            return false;
        }
        T t = (T) e;
        return th.eq(t);
    }
}
