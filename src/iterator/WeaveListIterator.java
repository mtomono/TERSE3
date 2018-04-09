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

import static iterator.Iterators.wind;
import static iterator.ListIterators.rewind;
import java.util.List;
import java.util.ListIterator;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author masao
 */
public class WeaveListIterator<T> implements ListIterator<T> {
    List<ListIterator<T>> body;
    ListIterator<ListIterator<T>> iter;
    ListIterator<T> lastIterator;
    int count;
    
    static public <T> WeaveListIterator<T> create(List<List<T>> src, int count) {
        assert !src.isEmpty();
        List<ListIterator<T>> body = src.stream().map(l->l.listIterator(count/src.size())).collect(toList());
        body.stream().limit(count%src.size()).forEach(i->i.next());
        return new WeaveListIterator<>(body, count);
    }
    
    static public <T> WeaveListIterator<T> createByIterator(List<ListIterator<T>> body, int count) {
        assert !body.isEmpty();
        body.stream().forEach(i->rewind(i));
        body.stream().forEach(i->wind(i, count/body.size()));
        body.stream().limit(count%body.size()).forEach(i->i.next());
        return new WeaveListIterator<>(body, count);
    }
    
    static public <T> WeaveListIterator<T> create(List<List<T>> src) {
        return create(src, 0);
    }
    
    static public <T> WeaveListIterator<T> createByIterator(List<ListIterator<T>> body) {
        return createByIterator(body, 0);
    }
    
    public WeaveListIterator(List<ListIterator<T>> body, int count) {
        assert !body.isEmpty();
        this.body = body;
        this.iter = body.listIterator(count%body.size());
        this.count = count;
        this.lastIterator = body.get(0);
    }
    
    ListIterator<T> getNext() {
        if (!iter.hasNext())
            iter = body.listIterator();
        return iter.next();
    }
    
    ListIterator<T> peekNext() {
        getNext();
        return iter.previous();
    }
    
    ListIterator<T> getPrevious() {
        if (!iter.hasPrevious())
            iter = body.listIterator(body.size());
        return iter.previous();
    }
    
    ListIterator<T> peekPrevious() {
        getPrevious();
        return iter.next();
    }
    
    @Override
    public boolean hasNext() {
        return peekNext().hasNext();
    }

    @Override
    public T next() {
        ListIterator<T> i = getNext();
        T retval = i.next();
        count++;
        lastIterator = i;
        return retval;
    }

    @Override
    public boolean hasPrevious() {
        return peekPrevious().hasPrevious();
    }

    @Override
    public T previous() {
        ListIterator<T> i = getPrevious();
        T retval = i.previous();
        count--;
        lastIterator = i;
        return retval;
    }

    @Override
    public int nextIndex() {
        return count;
    }

    @Override
    public int previousIndex() {
        return count-1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(T e) {
        lastIterator.set(e);
    }

    @Override
    public void add(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
