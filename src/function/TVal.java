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
package function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author masao
 */
public class TVal<T> {
    T body;
    public TVal(T body) {
        this.body=body;
    }
    
    public <S> TVal<S> map(Function<T,S> func) {
        return new TVal<>(func.apply(body));
    }
    
    public TVal<T> mapIf(Predicate<T> pred, Function<T,T> ifClause) {
        return new TVal<>(pred.test(body)?ifClause.apply(body):body);
    }
    
    public <S> TVal<S> mapIf(Predicate<T> pred, Function<T,S> ifClause, Function<T,S> elseClause) {
        return new TVal<>(pred.test(body)?ifClause.apply(body):elseClause.apply(body));
    }
    
    public TVal<T> tee(Consumer<T> cons) {
        cons.accept(body);
        return this;
    }
    
    public TVal<T> teeIf(Predicate<T> pred, Consumer<T> ifClause) {
        if (pred.test(body))
            ifClause.accept(body);
        return this;
    }
    
    public TVal<T> teeIf(Predicate<T> pred, Consumer<T> ifClause, Consumer<T> elseClause) {
        if (pred.test(body))
            ifClause.accept(body);
        else
            elseClause.accept(body);
        return this;
    }
    
    public T get() {
        return body;
    }
}
