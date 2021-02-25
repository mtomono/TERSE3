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
package function;

/**
 *
 * @author masao
 * @param <T>
 */
public interface Order<T extends Comparable<? super T>> {
    default T min(T a, T b) { return CompareUtil.min(a, b); }
    default T max(T a, T b) { return CompareUtil.max(a, b); }
    default boolean eq(T a, T b) { return CompareUtil.eq(a, b); }
    default boolean ne(T a, T b) { return CompareUtil.ne(a, b); }
    default boolean lt(T a, T b) { return CompareUtil.lt(a, b); }
    default boolean le(T a, T b) { return CompareUtil.le(a, b); }
    default boolean gt(T a, T b) { return CompareUtil.gt(a, b); }
    default boolean ge(T a, T b) { return CompareUtil.ge(a, b); }
}
