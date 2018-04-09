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

import collection.RingBuffer;
import java.util.Iterator;

/**
 * an iterator which holds given number of elements.
 * @author mtomono
 * @param <T>
 */
public class PreIterator<T> implements Iterator<T> {
    Iterator<T> body;
    RingBuffer<T> pre;
    
    
    public PreIterator(Iterator<T> body, int depth) {
        this.body = body;
        this.pre = new RingBuffer<>(depth);
    }
    
    public int getDepth() {
        return pre.size();
    }
    
    public int prefetch(int numberOfPrefetch) {
        for (int i = 0; i < numberOfPrefetch; i++) {
            if (this.body.hasNext()) {
                next();
            } else {
                return i;
            }
        }
        return numberOfPrefetch;
    }
    
    public static <T> PreIterator<T> createNextReady(Iterator<T> body, int numberOfPrefetch) {
        PreIterator<T> retval = new PreIterator(body, numberOfPrefetch + 1);
        retval.prefetch(numberOfPrefetch);
        return retval;
    }
        
    public RingBuffer<T> pre() {
        return pre;
    }
    
    /**
     * previous value: -n means the value returned by next() invoked n times before
     * so if you want to get the result of next() right before the last next(), you 
     * should call pre(-1).
     * @param i
     * @return 
     */
    public T pre(int i) {
        return pre.get(getDepth() - 1 + i);
    }
    
    public T now() {
        return pre(0);
    }
    
    public T pop(T t) {
        return pre.pop(t);
    }
    
    @Override
    public boolean hasNext() {
        return this.body.hasNext();
    }

    @Override
    public T next() {
        T retval = this.body.next();
        this.pre.push(retval);
        return retval;
    }
    
    public void load(T o) {
        this.pre.push(o);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
