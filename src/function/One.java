/*
 Copyright 2021 Masao Tomono

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

import collection.P;
import collection.TList;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *This is a wrapper which gives calculation context to any objects.
 * @author masao
 */
public interface One<T> {
    T get();
    public static <T> One<T> of(T body) {
        return ()->body;
    }
    static public <T> T incase(T t, Predicate<? super T> pred, T value) {
        return pred.test(t)?value:t;
    }
    static public <T> T incaseMap(T t, Predicate<? super T> pred, Function<? super T, ? extends T> f) {
        return pred.test(t)?f.apply(t):t;
    }
    default One<T> incase(Predicate<? super T> pred, T value) {
        return ()->incase(get(),pred,value);
    }
    default One<T> incaseMap(Predicate<? super T> pred, Function<? super T, ? extends T> f) {
        return ()->incaseMap(get(),pred,f);
    }
    default One<T> incase(P<Predicate<? super T>, T>... whatifs) {
        return One.this.incase(TList.sof(whatifs));
    }
    default One<T> incaseMap(P<Predicate<? super T>, Function<? super T, ? extends T>>... whatifs) {
        return incaseMap(TList.sof(whatifs));
    }
    default One<T> incase(TList<P<Predicate<? super T>,T>> whatifs) {
        T tested=get();
        return whatifs.stream().filter(w->w.l().test(tested)).findFirst().map(w->of(w.r())).orElse(of(tested));
    }
    default One<T> incaseMap(TList<P<Predicate<? super T>, Function<? super T, ? extends T>>> whatifs) {
        T tested=get();
        return whatifs.stream().filter(w->w.l().test(tested)).findFirst().map(w->of(w.r().apply(tested))).orElse(of(tested));
    }
    default <S> One<S> map(Function<? super T, ? extends S> f) {
        return ()->f.apply(get());
    }
    default One<Optional<T>> filter(Predicate<? super T> pred) {
        return ()->Optional.of(get()).filter(pred);
    }
}
