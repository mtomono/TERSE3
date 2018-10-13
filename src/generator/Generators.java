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

package generator;


import collection.OneOrNone;
import collection.TList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author masao
 */
public interface Generators {
    static public String[] brace = {"{", "}"};
    static public String[] bracket = {"[","]"};
    public static class Elements extends TList<Consumer<StringBuilder>> {
        public Elements(List<Consumer<StringBuilder>> body) {
            super(body);
        }
        public Elements(Consumer<StringBuilder>... body) {
            this(TList.sof(body));
        }
        public Elements delimit(String delimitter) {
            return new Elements(delimitByValue(c->c, constant(delimitter)));
        }
        public Elements enclose(String... enclosing) {
            return new Elements(insertAt(0,constant(enclosing[0])).append(constant(enclosing[1])));
        }
        public void congregate(StringBuilder sb) {
            forEach(c->c.accept(sb));
        }
    }
    default public <T> Elements seq(T t, TList<Generator<T>>  cs) {
        return new Elements(cs.map(c->c.curry(t)));
    }
    default public <T> Elements seq(T t, Generator<T>... cs) {
        return seq(t, TList.sof(cs));
    }
    default public <T> Elements rep(Collection<T> ts, Generator<T> c) {
        return new Elements(TList.set(ts).map(t->c.curry(t)));
    }
    default public <T> Elements opt(Optional<T> ot, Generator<T> c) {
        return rep(new OneOrNone<>(ot), c);
    }
    static public Consumer<StringBuilder> constant(String s) {
        return sb->sb.append(s);
    }
}
