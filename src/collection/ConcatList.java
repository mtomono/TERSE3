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

import static collection.c.a2i;
import debug.Monitorable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author masao
 * @param <T>
 */
public class ConcatList<T> extends AbstractList<T> implements Monitorable {
    class SubList implements Monitorable {
        List<T> target;
        SubList prev;
        
        public SubList(List<T> target, SubList prev) {
            this.target = target;
            this.prev = prev;
        }
        
        public SubList(List<T> target) {
            this(target, null);
        }
        
        public SubList choose(int index) {
            if (index >= prevTotalSize())
                return this;
            else 
                return prev.choose(index);
        }
        
        public int index(int x) {
            return x - prevTotalSize();
        }
        
        public T get(int index) {
            return target.get(index(index));
        }
        
        public T remove(int index) {
            T retval = target.remove(index(index));
            if (target.isEmpty() && target instanceof ConcatList)
                target = newEmptyList();
            return retval;
        }
        
        public T set(int index, T element) {
            return target.set(index(index), element);
        }
        
        public void add(int index, T element) {
            target.add(index(index), element);
        }
        
        public int size() {
            return target.size();
        }
        
        public int prevTotalSize() {
            return prev == null ? 0 : prev.totalSize();
        }
        
        public int totalSize() {
            return prevTotalSize() + target.size();
        }
        @Override
        public String monitor() {
            return "ConcatList.SubList:\n"+indent(target);
        }
    }
    
    SubList r;

    protected List<T> newEmptyList() {
        return new ArrayList();
    }
    
    public ConcatList(List<T>... lists) {
        Iterator<List<T>> iter = a2i(lists);
        assert iter.hasNext();
        this.r = new SubList(iter.next());
        while (iter.hasNext()) {
            this.r = new SubList(iter.next(), this.r);
        }
    }
    
    @Override
    public T get(int index) {
        return r.choose(index).get(index);
    }
    
    @Override
    public T remove(int index) {
        return r.choose(index).remove(index);
    }
    
    @Override
    public T set(int index, T element) {
        return r.choose(index).set(index, element);
    }
    
    @Override
    public void add(int index, T element) {
        r.choose(index).add(index, element);
    }

    @Override
    public int size() {
        return r.totalSize();
    }

    @Override
    public String monitor() {
        return "ConcatList:\n"+indent(r);
    }

}
