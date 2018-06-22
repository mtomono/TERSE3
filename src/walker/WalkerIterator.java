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

package walker;

import java.util.Iterator;
import java.util.function.BiFunction;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 * @param <U>
 */
public class WalkerIterator<S, T, U> implements Iterator<U> {
    Walker<S, T> base;
    BiFunction<S, T, U> func;
    
    public WalkerIterator(Walker<S, T> base, BiFunction<S, T, U> func) {
        this.base = base;
        this.func = func;
    }
    
    @Override
    public boolean hasNext() {
        return base.hasNext();
    }

    @Override
    public U next() {
        base.fore();
        return func.apply(base.left(), base.right());
    }
}
