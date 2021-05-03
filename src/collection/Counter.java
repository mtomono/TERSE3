/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 *
 * @author masao
 */
public class Counter<T> {
    Map<T,Integer> body;
    public Counter() {
        this.body=new HashMap<>();
    }
    public Counter(Collection<T> body) {
        this();
        addAll(body);
    }
    public Counter<T> add(T x) {
        if (!body.containsKey(x)) body.put(x, 0);
        body.put(x,body.get(x)+1);
        return this;
    }
    public Counter<T> addAll(Collection<T> body) {
        body.forEach(t->add(t));
        return this;
    }
    public Integer get(T x) {
        if (!body.containsKey(x)) return 0;
        return body.get(x);
    }
    public TList<T> filter(BiPredicate<T,Integer> pred) {
        return TList.set(body.entrySet()).filter(es->pred.test(es.getKey(), es.getValue())).map(es->es.getKey());
    }
}
