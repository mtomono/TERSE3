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
import java.util.Optional;

/**
 * An infinite length view of a ListIterator. 
 * @author mtomono
 * @param <T>
 */
public class OptIterator<T> implements ListIterator<Optional<T>> {
    ListIterator<T> body;
    int next;
    boolean returnedValue;
    
    public OptIterator(ListIterator<T> body) {
        this.body = body;
        this.next = body.nextIndex();
        returnedValue = true;
    }
    
    @Override
    public Optional<T> next() {
        boolean op = operable();
        next++;
        if (!op || !body.hasNext())
            return Optional.empty();
        return Optional.of(body.next());
    }

    @Override
    public Optional<T> previous() {
        boolean op = operable();
        next--;
        if (!op || !body.hasPrevious())
            return Optional.empty();
        return Optional.of(body.previous());
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public boolean hasPrevious() {
        return true;
    }

    @Override
    public int nextIndex() {
        return next;
    }

    @Override
    public int previousIndex() {
        return next - 1;
    }
    
    boolean operable() {
        return next == body.nextIndex();
    }

    @Override
    public void remove() {
        if (operable())
            body.remove();
    }

    @Override
    public void set(Optional<T> e) {
        if (operable())
            body.set(e.get());
    }

    @Override
    public void add(Optional<T> e) {
        if (operable())
            body.add(e.get());
    }
}
