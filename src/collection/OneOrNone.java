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

import java.util.AbstractList;
import java.util.Optional;

/**
 * A wrapper which handles Optional as a List which has an element at most.
 * @author mtomono
 * @param <T>
 */
public class OneOrNone<T> extends AbstractList<T> {
    T body;
    boolean has;
    
    public OneOrNone() {
        this.has=false;
    }
    
    public OneOrNone(T body) {
        this.has = true;
        this.body = body;
    }
    
    public OneOrNone(Optional<T> o) {
        has = o.isPresent();
        o.ifPresent(i->body = i);
    }
    
    public Optional<T> toOptional() {
        return has?Optional.of(body):Optional.empty();
    }
    
    @Override
    public T get(int index) {
        if (index > 0 || !has)
            throw new IndexOutOfBoundsException("OneOrNone#get : no such element");
        return body;
    }

    @Override
    public int size() {
        return has?1:0;
    }
    
    @Override
    public boolean add(T o) {
        if (has)
            return false;
        body = o;
        return has = true;
    }
    
    @Override
    public T remove(int i) {
        if (i > 0 || !has) 
            throw new IndexOutOfBoundsException("OneOrNone#remove : no such element");
        has = false;
        return body;
    }
    
    @Override
    public T set(int i, T o) {
        if (i > 0 || !has)
            throw new IndexOutOfBoundsException("OneOrNone#remove : no such element");
        T pre = body;
        body = o;
        return pre;
    }
}
