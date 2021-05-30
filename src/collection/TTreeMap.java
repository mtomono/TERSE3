/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
