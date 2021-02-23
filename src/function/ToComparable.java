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

import java.util.function.Function;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public interface ToComparable<S,T extends Comparable<T>> {
    Function<S,T> toComparable();
    default boolean eq(S a, S b) {
        return CompareUtil.eq(toComparable().apply(a),toComparable().apply(b));
    }
    default boolean ne(S a, S b) {
        return CompareUtil.ne(toComparable().apply(a),toComparable().apply(b));
    }
    default boolean gt(S a, S b) {
        return CompareUtil.gt(toComparable().apply(a),toComparable().apply(b));
    }
    default boolean ge(S a, S b) {
        return CompareUtil.ge(toComparable().apply(a),toComparable().apply(b));
    }
    default boolean lt(S a, S b) {
        return CompareUtil.lt(toComparable().apply(a),toComparable().apply(b));
    }
    default boolean le(S a, S b) {
        return CompareUtil.le(toComparable().apply(a),toComparable().apply(b));
    }
    default S max(S a, S b) {
        return toComparable().apply(a).compareTo(toComparable().apply(b))>0?a:b;
    }
    default S min(S a, S b) {
        return toComparable().apply(a).compareTo(toComparable().apply(b))<0?a:b;
    }
}
