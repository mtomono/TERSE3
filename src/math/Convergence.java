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

package math;

import function.Holder;
import iterator.TIterator;
import static iterator.TIterator.of;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class Convergence {
    public static <S,K,T extends ContextOrdered<K,T>> TIterator<S> diff(TIterator<S> target,Function<S,T> f,T threshold) {
        return converge(target,f,(p,c)->p.sub(c).abs().lt(threshold));
    }
    
    public static <S,K,T extends ContextOrdered<K,T>> TIterator<S> converge(TIterator<S> target,Function<S,T> f,BiPredicate<T,T> cond) {
        TIterator<T> convSeq=target.map(f);
        if (!target.hasNext())
            return target;
        S start=target.next();
        Holder<T> prev=new Holder<>(f.apply(start));
        return of(start).append(target.until(t->{var v=f.apply(t);return cond.test(prev.push(v),v);}));
    }
}
