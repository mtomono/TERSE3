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
    /**
     * apply.
     * @param f
     * @return 
     */
    public Cursor<T> a(UnaryOperator<T> f) {
        this.body=f.apply(body);
        return this;
    }
    /**
     * map
     * @param <S>
     * @param f
     * @return 
     */
    public <S> S m(Function<T,S> f) {
        return f.apply(body);
    }
    public T get() {
        return body;
    }
    /**
     * chain
     * @return 
     */
    public Chain<T> c() {
        return new Chain(body);
    }
    public String toString() {
        return "cursor:"+body;
    }
}
