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

package collection;

import java.util.*;

/**
 *
 * @author masao
 * @param <T>
 */
public class CacheList<T> extends AbstractList<T> {
    List<T> body;
    List<Optional<T>> cache;
    
    public CacheList(List<T> body) {
        this.body = body;
        this.cache = new ArrayList<>(Collections.nCopies(body.size(), Optional.empty()));
    }
    
    T cacheIn(int index) {
        T t = body.get(index);
        cache.set(index, Optional.of(t));
        return t;
    }
    
    @Override
    public T get(int index) {
        return cache.get(index).orElseGet(()->cacheIn(index));
    }

    @Override
    public int size() {
        return body.size();
    }
    
}
