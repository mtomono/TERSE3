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

import java.util.ListIterator;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public class MapListIterator<S, T> implements ListIterator<T> {
    ListIterator<S> body;
    Function<? super S, ? extends T> map;
    Function<? super T, ? extends S> rmap;
    
    public MapListIterator(ListIterator<S> body, Function<? super S, ? extends T> map, Function<? super T, ? extends S> rmap) {
        this.body = body;
        this.map = map;
        this.rmap = rmap;
    }
    
    public MapListIterator(ListIterator<S> body, Function<? super S, ? extends T> map) {
        this(body, map, e->{throw new RuntimeException("NoReach : no rmap specified");});
    }
    
    @Override
    public boolean hasNext() {
        return body.hasNext();
    }

    @Override
    public T next() {
        return map.apply(body.next());
    }

    @Override
    public boolean hasPrevious() {
        return body.hasPrevious();
    }

    @Override
    public T previous() {
        return map.apply(body.previous());
    }

    @Override
    public int nextIndex() {
        return body.nextIndex();
    }

    @Override
    public int previousIndex() {
        return body.previousIndex();
    }

    @Override
    public void remove() {
        body.remove();
    }

    @Override
    public void set(T e) {
        body.set(rmap.apply(e));
    }

    @Override
    public void add(T e) {
        body.add(rmap.apply(e));
    }
    
}
