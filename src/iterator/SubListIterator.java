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
 * ListIterator which is truncated by from and to
 * @author mtomono
 * @param <T>
 */
public class SubListIterator<T> implements ListIterator<T> {
    ListIterator<T> body;
    int from;
    int to;
    
    public SubListIterator(ListIterator<T> body, int length) {
        this(body, 0, body.nextIndex() + length);
    }
    
    public SubListIterator(ListIterator<T> body, int from, int to) {
        assert from <= body.nextIndex();
        assert body.nextIndex() <= to;
        this.body = body;
        this.from = from;
        this.to = to;
    }
    
    @Override
    public boolean hasNext() {
        return body.hasNext() && body.nextIndex() < to;
    }

    @Override
    public T next() {
        return body.next();
    }

    @Override
    public boolean hasPrevious() {
        return body.hasPrevious() && from < body.nextIndex();
    }
    @Override
    public T previous() {
        return body.previous();
    }
    
    @Override
    public int nextIndex() {
        return body.nextIndex() - from;
            }

    @Override
    public int previousIndex() {
        return body.previousIndex() - from;
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
    }
    
}
