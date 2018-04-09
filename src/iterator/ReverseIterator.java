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

import java.util.ListIterator;

/**
 *
 * @author mtomono
 */
public class ReverseIterator<T> implements ListIterator<T> {
    ListIterator<T> body;
    
    public ReverseIterator(ListIterator<T> body) {
        this.body = body;
    }
    
    @Override
    public boolean hasNext() {
        return body.hasPrevious();
    }

    @Override
    public T next() {
        return body.previous();
    }

    @Override
    public boolean hasPrevious() {
        return body.hasNext();
    }

    @Override
    public T previous() {
        return body.next();
    }

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        body.remove();
    }

    @Override
    public void set(T e) {
        body.set(e);
    }

    @Override
    public void add(T e) {
        body.add(e);
        body.add(e);
        body.previous();
        body.previous();
        body.remove();
    }
    
}
