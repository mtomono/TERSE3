/*
 Copyright 2017, 2018, 2019, 2020 Masao Tomono

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

/**
 *
 * @author masao
 */
public class Chain<T> {
    final public T body;
    public static <T> Chain<T> chain(T body) {
        return new Chain<>(body);
    }
    public Chain(T body) {
        this.body=body;
    }
    /**
     * map to Chain<S>
     * @param <S>
     * @param f
     * @return 
     */
    public <S> Chain<S> f(Function<T,S> f) {
        return new Chain<>(f.apply(body));
    }
    /**
     * apply consumer.
     * @param c
     * @return 
     */
    public Chain<T> c(Consumer<T> c) {
        c.accept(body);
        return this;
    }
    public T get() {
        return body;
    }
}
