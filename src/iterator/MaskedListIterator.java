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

import java.util.BitSet;
import java.util.ListIterator;

/**
 *
 * @author masao
 * @param <T>
 */
public class MaskedListIterator<T> implements ListIterator<T> {
    ListIterator<T> body;
    BitSet mask;
    int index;
    
    public MaskedListIterator(ListIterator<T> body, BitSet mask, int index) {
        this.body = body;
        this.mask = mask;
        this.index = index;
    }
    
    @Override
    public boolean hasNext() {
        return body.hasNext() && mask.nextSetBit(body.nextIndex()) != -1;
    }

    @Override
    public T next() {
        while (!mask.get(body.nextIndex()))
            body.next();
        index++;
        return body.next();
    }

    @Override
    public boolean hasPrevious() {
        return body.hasPrevious() && mask.previousSetBit(body.previousIndex()) != -1;
    }

    @Override
    public T previous() {
        while (!mask.get(body.previousIndex()))
            body.previous();
        index--;
        return body.previous();
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
        body.set(e);
    }

    @Override
    public void add(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
