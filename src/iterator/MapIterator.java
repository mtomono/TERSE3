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

import java.util.Iterator;
import java.util.function.Function;

/**
 * iterator which maps value
 * @author mtomono
 * @param <E> input
 * @param <F> result
 */
public class MapIterator<E, F> implements Iterator<F> {
    Iterator<E> base;
    Function<? super E, ? extends F> map;
            
    public MapIterator(Iterator<E> base, Function<? super E, ? extends F> map) {
        this.base = base;
        this.map  = map;
    }
    
    
    @Override
    public boolean hasNext() {
        return base.hasNext();
    }

    @Override
    public F next() {
        return map.apply(base.next());
    }
    
}
