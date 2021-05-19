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

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface ChainedBiFunction<S,T,U> extends BiFunction<S,T,U>{
    public static <S,T,U> ChainedBiFunction<S,T,U> b(ChainedBiFunction<S,T,U> f) {
        return f;
    }
    default <V,W> ChainedBiFunction<V,W,U> compose(Function<V,S> l, Function<W,T> r) {
        return (ll,rr)->apply(l.apply(ll),r.apply(rr));
    }
    default <V> Function<V,U> unify(Function<V,S> l, Function<V,T> r) {
        return x->apply(l.apply(x),r.apply(x));
    }
}
