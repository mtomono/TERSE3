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

package test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * wrapper for map to get print out for put and get.
 * wrapper for debugging and testing.
 * @author masao
 */
public class TeeMap<K,V> implements Map<K,V> {
    Map<K,V> body;
    public TeeMap(Map<K,V> body) {
        this.body = body;
    }
    
    @Override
    public V get(Object key) {
        V retval = body.get(key);
        System.out.println("get:"+retval+"@"+key);
        return retval;
    }
    
    @Override
    public V put(K key, V value) {
        V retval = body.put(key, value);
        System.out.println("put:"+retval+"->"+value+"@"+key);
        return retval;
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        return body.entrySet();
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
    
}
