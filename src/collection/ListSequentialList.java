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

import iterator.ListIteratorIterator;
import iterator.TIterator;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author masao
 * @param <T>
 */
public class ListSequentialList<T> extends AbstractSequentialList<T> {
    @Override
    public ListIterator<T> listIterator(int index) {
        return ListIteratorIterator.create(body, index);
    }
    
    List<List<T>> body;
    
    public ListSequentialList(List<List<T>> body) {
        this.body = body;
    }
    
    P<List<T>, Integer> position(int index) {
        return TIterator.set(body.iterator()).pair(TIterator.set(body.iterator()).map(l->l.size()).accum(0, (a, b)->a+b)).filter(p->p.r()<=index).last();
    }
    
    @Override
    public T get(int index) {
        P<List<T>, Integer> in = position(index);
        return in.l().get(index - in.r());
    }

    @Override
    public int size() {
        return body.stream().mapToInt(l->l.size()).sum();
    }
    
    @Override
    public T remove(int i) {
        P<List<T>, Integer> in = position(i);
        return in.l().remove(i-in.r());
    }
    
    @Override
    public T set(int i, T o) {
        P<List<T>, Integer> in = position(i);
        return in.l().set(i-in.r(), o);
    }
    
    @Override
    public void add(int i, T o) {
        P<List<T>, Integer> in = position(i);
        in.l().add(i-in.r(), o);
    }
    
}
