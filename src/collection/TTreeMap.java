/*
 Copyright 2021 Masao Tomono

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

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class TTreeMap<K,V> extends TreeMap<K,V> {
    public TTreeMap() {
        super();
    }
    public TTreeMap(Comparator<? super K> comparator) {
        super(comparator);
    }
    public TTreeMap(Map<? extends K,? extends V> m) {
        super(m);
    }
    public TTreeMap(SortedMap<K, ? extends V> m) {
        super(m);
    }
    /**
     * works like TreeMap#computeIfAbsent() but allows to add entry from mappingFunction.
     * @param key
     * @param mappingFunction
     * @return 
     */
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        V value;
        if (!containsKey(key)) {
            value=mappingFunction.apply(key);
            put(key,value);
        } else
            value=get(key);
        return value;
    }
}
