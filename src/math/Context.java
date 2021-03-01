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

import function.Wrapper;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public interface Context<K,T extends Context<K,T>> extends Wrapper<K,T>{
    ContextBuilder<K,T> b();
    @Override
    default T wrap(K v) {
        return b().c(v);
    }
    default T add(T v) {
        return v.wrap(b().body().add(body(), v.body()));
    }
    default T sub(T v) {
        return v.wrap(b().body().sub(body(), v.body()));
    }
    default T mul(T v) {
        return v.wrap(b().body().mul(body(), v.body()));
    }
    default T div(T v) {
        return v.wrap(b().body().div(body(), v.body()));
    }
    default T one() {
        return wrap(b().body().one());
    }
    default T zero() {
        return wrap(b().body().zero());
    }
    default T inv() {
        return one().div(self());
    }
    default T negate() {
        return wrap(b().body().negate(body()));
    }
    default T abs() {
        return wrap(b().body().abs(body()));
    }
    default T interpolate(T rate, T o, T orate) {
        return mul(orate).add(o.mul(rate)).div(rate.add(orate));
    }
    default T interpolate1(T rate, T o) {
        return interpolate(rate,o,one().sub(rate));
    }
    default T interpolate100(T rate, T o) {
        return interpolate(rate,o,b().b(100).sub(rate));
    }
    default String toFormattedString() {
        return b().toString(self());
    }
}
