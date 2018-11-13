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
public interface ChainedFunction<S,T> extends Function<S,T>{
    static public <S,T> ChainedFunction<S,T> f(Function<S,T> func) {
        return x->func.apply(x);
    }
    
    default <U> ChainedFunction<U,T> o(Function<U,S> before) {
        return x->this.compose(before).apply(x);
    }
    
    default <U> ChainedFunction<S,U> a(Function<T,U> after) {
        return x->this.andThen(after).apply(x);
    }
    default <U> ChainedFunction<S,U> m0(BiFunction<T,S,U> merger) {
        return x->merger.apply(apply(x),x);
    }
    default <U> ChainedFunction<S,U> m1(BiFunction<S,T,U> merger) {
        return x->merger.apply(x,apply(x));
    }
}
