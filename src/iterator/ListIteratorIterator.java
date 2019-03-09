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

package iterator;

import collection.*;
import static collection.ListOperations.endIterator;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import static java.lang.Integer.min;
import java.util.ArrayList;
import static collection.Scale.scale;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ListIterator of ListIterator.
 * 
 * @author masao
 * @param <T>
 */
public class ListIteratorIterator<T> implements ListIterator<T>{
    ListIterator<ListIterator<T>> body;
    ListIterator<T> iter;
    int index;
    
    static public <T> ListIteratorIterator<T> create(List<List<T>> t, int index) {
        List<List<T>> target = new FilterList<>(t, l->!l.isEmpty());
        P<Integer, Integer> focus = TIterable.set(target).map(l->l.size()).accum(0,(a,b)->a+b).filter(i->i<=index).
                pair(scale()).iterator().last();
        List<ListIterator<T>> before = MapList.create(
                target.subList(0, focus.r()), e->endIterator(e));
        List<ListIterator<T>> very = MapList.create(
                target.subList(focus.r(), min(target.size(), focus.r()+1)), e->e.listIterator(index - focus.l()));
        List<ListIterator<T>> after = MapList.create(
                target.subList(min(target.size(), focus.r()+1), target.size()), e->e.listIterator());
        return new ListIteratorIterator<>(new ArrayList<>(new ListRandomList<>(before, very, after)).listIterator(focus.r()), index);
    }
    
    static public <T> ListIteratorIterator<T> create(List<List<T>> t) {
        return create(t, 0);
    }
        
    ListIteratorIterator(ListIterator<ListIterator<T>> body, int index) {
        this.body = body;
        this.iter = body.hasNext()?body.next():Collections.emptyListIterator();
        this.index = index;
    }
    
    public boolean has(Predicate<ListIterator> hasNext, Function<ListIterator<ListIterator<T>>,ListIterator<T>> next) {
        if (hasNext.test(iter))
            return true;
        if (!hasNext.test(body))
            return false;
        ListIterator<T> candidate = next.apply(body);
        if (iter != candidate)
            iter = candidate;
        else if (hasNext.test(body))
            iter = next.apply(body);
        else
            return false;
        return has(hasNext,next);
    }
    
    @Override
    public boolean hasNext() {
        return has(i->i.hasNext(), i->i.next());
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException("ListIteratorIterator: no such element");
        index++;
        return iter.next();
    }

    @Override
    public boolean hasPrevious() {
        return has(i->i.hasPrevious(), i->i.previous());
    }

    @Override
    public T previous() {
        if (!hasPrevious())
            throw new NoSuchElementException("ListIteratorIterator: no such element");
        index--;
        return iter.previous();
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        iter.remove();
        index--;
    }

    @Override
    public void set(T e) {
        iter.set(e);
    }

    @Override
    public void add(T e) {
        iter.add(e);
        index++;
    }
}
