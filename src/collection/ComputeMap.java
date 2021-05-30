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
public class ComputeMap {
    /**
     * works like Map#computeIfAbsent() but this allows mappingFunction to change map.
     * it usually happens when mappingFunction is recursive.
     * @param <K>
     * @param <V>
     * @param map
     * @param key
     * @param mappingFunction
     * @return 
     */
    public static <K,V> V computeIfAbsent(Map<K,V> map, K key, Function<? super K, ? extends V> mappingFunction) {
        V value;
        if (!map.containsKey(key)) {
            value=mappingFunction.apply(key);
            map.put(key,value);
        } else
            value=map.get(key);
        return value;
    }
}
