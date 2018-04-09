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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ring Buffer based on fixed length ArrayList
 * something pushed goes to the end of the buffer.
 * @author mtomono
 */
public class RingBuffer<T> extends AbstractList<T> {
    List<T> body;
    int size;
    int index;
    
    public RingBuffer(List<T> init) {
        this.body = new ArrayList<>(init);
        this.size = init.size();
        this.index = 0;
    }
    
    public RingBuffer(int size) {
        this(Collections.nCopies(size, null));
    }
    
    int next() {
        return index = at(1);
    }
    
    int prev() {
        return index = at(size - 1);
    }
    
    int at(int i) {
        return (index + i)%size;
    }
    
    public T push(T t) {
        T retval = set(0, t);
        next();
        return retval;
    }
    
    public T pop(T t) {
        prev();
        return set(0, t);
    }
    
    @Override
    public T set(int i, T e) {
        return body.set(at(i), e);
    }
    
    @Override
    public T get(int i) {
        return body.get(at(i));
    }
    
    @Override
    public int size() {
        return size;
    }
}
