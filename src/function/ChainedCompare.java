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
 * interface to make comparison as add-on.
 * @author masao
 * @param <S>
 */
public interface ChainedCompare<S> extends Comparable<ChainedCompare<S>>{
    Order<S> order();
    S self();
    default int compareTo(ChainedCompare<S> o) {
        return order().compare(self(),o.self());
    }
    default boolean eq(S v) {
        return order().eq(self(),v);
    }
    default boolean ne(S v) {
        return order().ne(self(),v);
    }
    default boolean gt(S v) {
        return order().gt(self(),v);
    }
    default boolean ge(S v) {
        return order().ge(self(),v);
    }
    default boolean lt(S v) {
        return order().lt(self(),v);
    }
    default boolean le(S v) {
        return order().le(self(),v);
    }
}
