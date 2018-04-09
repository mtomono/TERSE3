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

package debug;

import collection.MVMap;
import collection.TList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class CollectDump implements Dump{
    String name;
    Map<String, Object> dumpedObjects;
    MVMap<String, Object> recurringDumpedObjects;
    Map<String, Integer> counter;
    
    public CollectDump(String name) {
        this.name = name;
        dumpedObjects = new HashMap<>();
        recurringDumpedObjects = new MVMap(new HashMap<>(), k->TList.c());
        counter = new HashMap<>();
    }

    @Override
    public <T> T set(String name, T object) {
        dumpedObjects.put(name, object);
        return object;
    }

    @Override
    public <T, S> T set(String name, T object, Function<T, S> func) {
        set(name, func.apply(object));
        return object;
    }
    
    @Override
    public <T> T add(String name, T object) {
        recurringDumpedObjects.getList(name).add(object);
        return object;
    }
    
    public <T, S> T add(String name, T object, Function<T, S> func) {
        add(name, func.apply(object));
        return object;
    }
    
    @Override
    public void count(String name) {
        if (!counter.containsKey(name))
            counter.put(name, 0);
        counter.put(name, counter.get(name)+1);
    }

    @Override
    public void dump() {
        if (!counter.isEmpty()) {
            System.out.println("COUNTER");
            counter.forEach((n,i)->System.out.println(n + ":" + i));
        }
        if (!dumpedObjects.isEmpty()) {
            System.out.println("COLLECTED");
            dumpedObjects.forEach((n,o)->System.out.println(n + ":" + o));
        }
        if (!recurringDumpedObjects.isEmpty()) {
            System.out.println("RECCURING");
            recurringDumpedObjects.forEach((n,l)->{
                System.out.println(n);
                l.forEach(o->System.out.println(o));
            });
        }
    }
    
    public void scopedDump(String scope) {
        if (!counter.isEmpty()) {
            System.out.println("COUNTER");
            counter.entrySet().stream().filter(e->e.getKey().contains(scope)).forEach(e->System.out.println(e.getKey() + ":" + e.getValue()));
        }
        if (!dumpedObjects.isEmpty()) {
            System.out.println("COLLECTED");
            dumpedObjects.entrySet().stream().filter(e->e.getKey().contains(scope)).forEach(e->System.out.println(e.getKey() + ":" + e.getValue()));
        }
        if (!recurringDumpedObjects.isEmpty()) {
            System.out.println("RECCURING");
            recurringDumpedObjects.entrySet().stream().filter(e->e.getKey().contains(scope)).forEach(e->{
                System.out.println(e.getKey());
                e.getValue().forEach(o->System.out.println(o));
            });
        }        
    }
}
