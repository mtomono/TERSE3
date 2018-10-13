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

package json;

import generator.Generator;
import java.util.Collection;
import java.util.function.Function;
import generator.Generators;
import java.util.Optional;
import string.Strings;

/**
 *
 * @author masao
 */
public interface Jsonize extends Generators{
    static public Jsonize c() {
        return new Jsonize() {};
    }
    default public <T,S> Generator<T> value(Function<T,S> f) {
        return (sb, t)->sb.append(f.apply(t));
    }
    default public <T> Generator<T> string(Function<T,String> f) {
        return simpleString(f);
    }
    default public <T> Generator<T> simpleString(Function<T,String> f) {
        return value(f.andThen(Strings::doubleQuote));
    }
    default public <T> Generator<T> escapedString(Function<T,String> f) {
        return value(f.andThen(Strings::asString));
    }
    default public <T> Generator<T> chara(Function<T,Character> f) {
        return value(f.andThen(Strings::singleQuote));
    }
    default public <T,S> Generator<T> object(Function<T,S> f, Generator<S>... cs) {
        return (sb,t)->seq(f.apply(t),cs).delimit(",").enclose(brace).congregate(sb);
    }
    default public <T> Generator<T> object(Generator<T>... cs) {
        return object(o->o, cs);
    }
    default public <T,S> Generator<T> array(Function<T,Collection<S>> f, Generator<S> cs) {
        return (sb,t)->rep(f.apply(t),cs).delimit(",").enclose(bracket).congregate(sb);
    }
    default public <T> Generator<Collection<T>> array(Generator<T> cs) {
        return array(o->o, cs);
    }
    default public <T> Generator<T> attr(String name, Generator<T> c) {
        return (sb,t)->seq(t, string(x->name), c).delimit(":").congregate(sb);
    }
    default public <T> Generator<Optional<T>> optional(String name, Generator<T> c) {
        return (sb,t)->opt(t, attr(name, c)).congregate(sb);
    }
}
