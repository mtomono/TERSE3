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

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * unlike chain, cursor holds the result in place.
 * so that the user can restart from the last application.
 * this is quite the case when someone is crawling in a directory tree
 * through a shell.
 * @author masao
 */
public class Cursor<T> {
    T body;
    public Cursor(T body) {
        this.body=body;
    }
    public static <T> Cursor<T> of(T body) {
        return new Cursor<>(body);
    }
    /**
     * apply.
     * @param f
     * @return 
     */
    public Cursor<T> map(UnaryOperator<T> f) {
        this.body=f.apply(body);
        return this;
    }
    public Cursor<T> tee(Consumer<T> body) {
        return map(Functions.tee(body));
    }
    public <S> Optional<S> opt(Function<T,S> f) {
        return Optional.of(f.apply(body));
    }
    public T get() {
        return body;
    }
    public String toString() {
        return "cursor:"+body;
    }
}
