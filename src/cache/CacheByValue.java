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

package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Cache which comes with parameter
 * @author masao
 */
public class CacheByValue<K,V> {
    Map<K,V> map;
    Function<K,V> derivation;
    
    public CacheByValue(Function<K,V> derivation) {
        this.derivation = derivation;
        this.map = new HashMap<>();
    }
    
    public V recalc(K key) {
        return calc(key);
    }
    
    public V v(K key) {
        if (map.containsKey(key))
            return map.get(key);
        return calc(key);
    }
    
    public V calc(K key) {
        V retval = derivation.apply(key);
        map.put(key, retval);
        return retval;
    }
    
    public CacheByValue<K,V> recalc() {
        map.keySet().forEach(k->recalc(k));
        return this;
    }
}
