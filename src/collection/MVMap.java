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
import java.util.function.Function;

/**
 *Multi-Value Map implementation over any map implementation
 * 
 *  
 * @author masao
 * @param <K>
 * @param <V>
 */
public class MVMap<K, V> implements Map<K, TList<V>> {
    Map<K, TList<V>> body;
    Function<K, TList<V>> newList;
    
    public MVMap(Map<K, TList<V>> body, Function<K, TList<V>> newList) {
        this.newList = newList;
        this.body = body;
    }
    
    public MVMap(Map<K, TList<V>> body) {
        this(body, k->TList.c());
    }
    
    public TList<V> getList(K key) {
        if (!body.containsKey(key))
            body.put(key, newList.apply(key));
        return body.get(key);
    }
    
    @Override
    public int size() {
        return body.size();
    }

    @Override
    public boolean isEmpty() {
        return body.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return body.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return body.containsValue(value);
    }

    @Override
    public TList<V> get(Object key) {
        return body.get(key);
    }

    @Override
    public TList<V> put(K key, TList<V> value) {
        return body.put(key, value);
    }

    @Override
    public TList<V> remove(Object key) {
        return body.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends TList<V>> m) {
        body.putAll(m);
    }

    @Override
    public void clear() {
        body.clear();
    }

    @Override
    public Set<K> keySet() {
        return body.keySet();
    }

    @Override
    public Collection<TList<V>> values() {
        return body.values();
    }

    @Override
    public Set<Entry<K, TList<V>>> entrySet() {
        return body.entrySet();
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
