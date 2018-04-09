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

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author masao
 */
public class MergeListIterator<T> implements ListIterator<T> {
    ListIterator<T> left;
    ListIterator<T> right;
    int index;
    Comparator<T> c;
    
    static public <T> MergeListIterator<T> c(ListIterator<T> left, ListIterator<T> right, Comparator<T> c, int start) {
        MergeListIterator<T> retval = new MergeListIterator<>(left, right, c);
        for (int i=0; i<start; i++)
            retval.next();
        return retval;
    }
    
    public MergeListIterator(ListIterator<T> left, ListIterator<T> right, Comparator<T> c) {
        this.left = left;
        this.right = right;
        this.index = 0;
        this.c = c;
    }
        
    @Override
    public boolean hasNext() {
        return left.hasNext() || right.hasNext();
    }

    @Override
    public T next() {
        if (!left.hasNext() && !right.hasNext())
            throw new NoSuchElementException();
        index++;
        if (!left.hasNext() && right.hasNext())
            return right.next();
        if (left.hasNext() && !right.hasNext())
            return left.next();
        T l = left.next();
        T r = right.next();
        if (c.compare(l, r)<0) {
            right.previous();
            return l;
        }
        left.previous();
        return r;
    }

    @Override
    public boolean hasPrevious() {
        return left.hasPrevious() || right.hasPrevious();
    }

    @Override
    public T previous() {
        if (!left.hasPrevious() && !right.hasPrevious())
            throw new NoSuchElementException();
        index--;
        if (!left.hasPrevious() && right.hasPrevious())
            return right.previous();
        if (left.hasPrevious() && !right.hasPrevious())
            return left.previous();
        T l = left.previous();
        T r = right.previous();
        if (c.compare(l, r)>=0) {
            right.next();
            return l;
        }
        left.next();
        return r;
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index-1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
