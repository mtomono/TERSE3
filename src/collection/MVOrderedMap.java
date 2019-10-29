/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static java.lang.Math.abs;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class MVOrderedMap<K,V> extends MVMap<K,V> {
    Comparator<V> order;

    public MVOrderedMap(Map<K, TList<V>> body, Function<K, TList<V>> newList, Comparator<V> order) {
        super(body, newList);
        this.order=order;
    }
    
    public MVOrderedMap(Map<K, TList<V>> body, Comparator<V> order) {
        super(body);
        this.order=order;
    }
    
    static public <K, V extends Comparable<? super V>> MVOrderedMap<K,V> c(Map<K, TList<V>> body) {
        return new MVOrderedMap<>(body,(a,b)->a.compareTo(b));
    }
    
    public MVOrderedMap add(K key, V v) {
        getList(key).add(abs(Collections.binarySearch(getList(key), v, order)+1), v);
        return this;
    }
}
