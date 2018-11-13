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

package function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface ChainedBiFunction<S,T,U> extends BiFunction<S,T,U> {
    static public <S,T,U> ChainedBiFunction<S,T,U> ff(BiFunction<S,T,U> func) {
        return (x,y)->func.apply(x, y);
    }
    default ChainedFunction<T,U> c0(S s) {
        return x->apply(s,x);
    }

    default ChainedFunction<S,U> c1(T t) {
        return x->apply(x,t);
    }
    default ChainedFunction<S,U> cc0(Function<S,T> func) {
        return x->apply(x, func.apply(x));
    }
    default ChainedFunction<T,U> cc1(Function<T,S> func) {
        return x->apply(func.apply(x),x);
    }
    default <V> ChainedFunction<V,U> cc(Function<V,S> f0,Function<V,T> f1) {
        return x->apply(f0.apply(x),f1.apply(x));
    }
}
