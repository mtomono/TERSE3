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
 * a listiterator which only can previous() limited times at once.
 * @author mtomono
 */
abstract public class BufferedListIterator<T> implements ListIterator<T> {
    public Optional<T> peek() {
        if (!hasNext())
            return Optional.empty();
        T retval = next();
        previous();
        return Optional.of(retval);
    }
    
    public Optional<T> pre(int i) {
        if (i > 0)
            return Optional.empty();
        T retval = previous();
        next();
        return Optional.of(retval);
    }
    
    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
