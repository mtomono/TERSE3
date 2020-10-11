/*
 Copyright 2017, 2018, 2019 Masao Tomono

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
 * @param <U>
 */
@FunctionalInterface
public interface ThrowableFunction<S,T,U extends Exception> {
    T apply(S s) throws U;
    default Function<S,T> uncheck() {
        return s->{
            T retval;
            try {
                retval=apply(s);
            } catch(Exception u) {
                throw new RuntimeException(u);
            }
            return retval;
        };
    }
    static public <S,T> ThrowableFunction<S,T,Exception> c(ThrowableFunction<S,T,Exception> f) {
        return f;
    }
    static public <S,T,U extends Exception> ThrowableFunction<S,T,U> c(ThrowableFunction<S,T,U> f, Class<U> e) {
        return f;
    }
}
