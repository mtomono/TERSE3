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

package iterator;

import collection.P;
import java.util.Iterator;
import java.util.function.BiFunction;

/**
 * Z(ipping)I(terators)
 * @author masao
 * @param <L>
 * @param <R>
 */
public class ZI<T,S,R> implements Iterator<R> {
    Iterator<T> iterT;
    Iterator<S> iterS;
    BiFunction<T,S,R> f;
    
    public ZI(Iterator<T> iterT, Iterator<S> iterS, BiFunction<T,S,R> f) {
        this.iterT = iterT;
        this.iterS = iterS;
        this.f=f;
    }
        
    @Override
    public boolean hasNext() {
        return iterT.hasNext() && iterS.hasNext();
    }

    @Override
    public R next() {
        return f.apply(iterT.next(), iterS.next());
    }
    
}
