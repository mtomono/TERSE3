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

import java.util.*;

/**
 * a list which stands for the cache of an iterator.
 * because this is a list, you can take iterators as many as you like.
 * they all refers to the same cache and let it grow as they need.
 * @author mtomono
 * @param <T>
 */
public class IteratorCache<T> extends AbstractList<T> {
    ArrayList<T> cache;
    Iterator<T> body;
    
    public IteratorCache(Iterator<T> body) {
        this.body = body;
        this.cache = new ArrayList<>();
    }
    
    public List<T> dump() {
        return cache;
    }
    
    @Override
    public T get(int index) {
        if (index < cache.size()) {
            return cache.get(index);
        }
        T retval = null;
        for (int i = cache.size(); i <= index; i++) {
            if (!body.hasNext())
                throw new IndexOutOfBoundsException("IteratorCache : index was " + index + " where size is " + i);
            retval = body.next();
            cache.add(retval);
        }
        return retval;
    }

    /**
     * @deprecated
     * this method loads all elements of the body. this call makes this cache meaningless.
     * so this call is not recommended.
     * @return 
     */
    @Override
    public int size() {
        
        while (body.hasNext()) {
            cache.add(body.next());
        }
        return cache.size();
    }
    
    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }
    
    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            IteratorCache<T> target;
            int current;
            
            
            public ListIterator<T> init(IteratorCache<T> target) {
                this.target = target;
                this.current = 0;
                return this;
            }
            
            @Override
            public boolean hasNext() {
                return current < target.cache.size() || target.body.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("IteratorCache");
                return target.get(current++);
            }

            @Override
            public boolean hasPrevious() {
                return current > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious())
                    throw new NoSuchElementException("IteratorCache");
                return target.get(--current);
            }

            @Override
            public int nextIndex() {
                return current;
            }

            @Override
            public int previousIndex() {
                return current - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void set(T e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void add(T e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        
        }.init(this);
    }
    
}
