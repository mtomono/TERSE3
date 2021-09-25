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

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class Functions {
    public static <S,T> Function<S,T> f(Function<S,T> f) {
        return f;
    }
    public static <T> UnaryOperator<T> tee(Consumer<T> body) {
        return x->{ body.accept(x); return x; };
    }
    public static <T> UnaryOperator<T> id() {
        return x->x;
    }
    public static <T> T incase(T value, Predicate<T> incase, T otherwise) {
        if (incase.test(value))
            return otherwise;
        else
            return value;
    }
}
