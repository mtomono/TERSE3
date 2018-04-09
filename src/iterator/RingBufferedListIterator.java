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

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import viewlist.View;

/**
 * a listiterator which only can previous() up to depth times in a row.
 * @author mtomono
 * @param <T>
 */
public class RingBufferedListIterator<T> extends BufferedListIterator<T> {
    PreIterator<T> body;
    View<T> view;
    ListIterator<Optional<T>> observer;
    
    public RingBufferedListIterator(Iterator<T> body, int depth) {
        this(new PreIterator<>(body, depth));
    }
    
    public RingBufferedListIterator(PreIterator<T> body) {
        this.body = body;
        this.view = View.pre(body.pre(), body.getDepth());
        this.observer = view.listIterator();
    }
    
    public Optional<T> pre(int index) {
        return view.get(index);
    }
    
    @Override
    public boolean hasPrevious() {
        if (observer.hasNext()) {
            Optional<T> o = observer.next();
            observer.previous();
            return o.isPresent();
        }
        return false;
    }
    
    @Override
    public T previous() {
        if (!hasPrevious())
            throw new NoSuchElementException();
        return observer.next().get();
    }
    
    @Override
    public boolean hasNext() {
        return observer.hasPrevious() || body.hasNext();
    }

    @Override
    public T next() {
        if (observer.hasPrevious())
            return observer.previous().get();
        else
            return body.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
