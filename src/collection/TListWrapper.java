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

import java.util.*;

/**
 * base implementation for TList
 * @author masao
 */
public class TListWrapper<T> implements List<T> {
    List<T> body;
    
    public TListWrapper(List<T> body) {
        this.body = body;
    }

    static public boolean equalityForListSubclass(List<? extends Object> target, Object o) {
        if (!(o instanceof List))
            return false;
        List<? extends Object> e = (List<? extends Object>)o;
        return e.size()==target.size()&&TList.set(target).pair(e, (a,b)->a.equals(b)).stream().allMatch(p->p);
    }

    @Override
    public boolean equals(Object o) {
        return equalityForListSubclass(this, o);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23*hash+Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public ListIterator<T> listIterator() {
        return body.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return body.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return body.subList(fromIndex, toIndex);
    }
    
    //------------ simple wrapping
    @Override
    public int size() {
        return body.size();
    }

    @Override
    public boolean isEmpty() {
        return body.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return body.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return body.iterator();
    }

    @Override
    public Object[] toArray() {
        return body.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return body.toArray(a);
    }

    @Override
    public boolean add(T e) {
        return body.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return body.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return body.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return body.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return body.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return body.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return body.retainAll(c);
    }

    @Override
    public void clear() {
        body.clear();
    }

    @Override
    public T get(int index) {
        return body.get(index);
    }

    @Override
    public T set(int index, T element) {
        return body.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        body.add(index, element);
    }

    @Override
    public T remove(int index) {
        return body.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return body.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return body.lastIndexOf(o);
    }

    @Override
    public String toString() {
        return "T" + body.toString();
    }

}
