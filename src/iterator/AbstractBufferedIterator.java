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
import java.util.NoSuchElementException;

/**
 * Base implementation of Select
 * Iterator
 * @author mtomono
 * @param <T>
 */
abstract public class AbstractBufferedIterator<T> implements Iterator<T> {
    T buffer;
    boolean has;
    abstract protected void findNext();
    
    public boolean has() {
        return has;
    }
    public T peek() {
        return buffer;
    }

    public void nextFound(T buffered) {
        this.buffer = buffered;
        this.has = true;
    }
    
    public void prepare() {
        if (!has)
            findNext();
    }
    
    @Override
    public boolean hasNext() {
        prepare();
        return has;
    }

    @Override
    public T next() {
        if (!has&&!hasNext())
            throw new NoSuchElementException("AbstractIterator.next() : gone beyond boundary (actual class is " + this.getClass().getName() + ")");
        has = false;
        return buffer;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
