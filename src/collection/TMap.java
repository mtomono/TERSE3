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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Map wrapper which bridges to TList.
 * @author masao
 * @param <K>
 * @param <V>
 */
public class TMap<K, V> implements Map<K, V> {
    Map<K, V> body;
    
    public static <K,V> TMap<K,V> c() {
        return new TMap<>(new HashMap<>());
    }
   
    public TMap(Map<K, V> body) {
        this.body = body;
    }
        
    public <T> TList<T> map(BiFunction<K,V,T> map) {
        return TList.set(body.entrySet()).map(es->map.apply(es.getKey(),es.getValue()));
    }
    
    public <T> TList<T> map(BiPredicate<K,V> pred, BiFunction<K,V,T> map) {
        return TList.set(body.entrySet()).filter(es->pred.test(es.getKey(), es.getValue())).map(es->map.apply(es.getKey(), es.getValue()));
    }
    
    public void forEach(BiPredicate<K,V> pred, BiConsumer<K,V> consumer) {
        TList.set(body.entrySet()).filter(es->pred.test(es.getKey(), es.getValue())).forEach(es->consumer.accept(es.getKey(), es.getValue()));
    }

    public TList<K> filterKey(BiPredicate<K,V> pred) {
        return map(pred,(k,v)->k);
    }
    
    public TList<V> filterValues(BiPredicate<K,V> pred) {
        return map(pred,(k,v)->v);
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
    public V get(Object key) {
        return body.get(key);
    }

    @Override
    public V put(K key, V value) {
        return body.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return body.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
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
    public Collection<V> values() {
        return body.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return body.entrySet();
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
