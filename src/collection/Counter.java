/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

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
