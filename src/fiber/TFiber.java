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
package fiber;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author masao
 */
public class TFiber<T> {
    final public Source<T> body;
    public static <T> TFiber<T> set(Source<T> body) {
        return new TFiber<>(body);
    }
    public TFiber(Source<T> body) {
        this.body=body;
    }
    public <R> TFiber<R> map(Function<T,R> f) {
        Fiber<T,R> retval=new MapFiber<>(f);
        body.add(retval);
        return set(retval);
    }
    public <R> TFiber<R> flatMap(Function<T,List<R>>f) {
        Fiber<T,R> retval=new FlatMapFiber<>(f);
        body.add(retval);
        return set(retval);
    }
    public <R> TFiber<R> flatIter(Function<T,? extends Iterator<R>>f) {
        Fiber<T,R> retval=new FlatIterFiber<>(f);
        body.add(retval);
        return set(retval);
    }
    public TFiber<T> filter(Predicate<T> pred) {
        Fiber<T,T> retval=new FilterFiber<>(pred);
        body.add(retval);
        return set(retval);
    }
    public <C extends Collection<T>> C sink(C retval) {
        CSink<T> sink=new CSink<>(retval);
        body.add(sink);
        return retval;
    }
}
