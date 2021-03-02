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

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public interface ContextNumber<K extends Number, T extends ContextNumber<K,T>> extends Context<K,T>,ChainedCompare<T> {
    default boolean isInteger() {
        return self().eq(intValue());
    }
    default boolean isLong() {
        return self().eq(longValue());
    }
    default T intValue() {
        return b().b(body().intValue());
    }
    default T longValue() {
        return b().b(body().longValue());
    }
    
}
