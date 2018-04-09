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

package orderedSet;

import iterator.SingleBufferedListIterator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author mtomono
 * @param <E>
 */
public class AssertOrderListIterator<E> implements ListIterator<E> {
    ListIterator<E> body;
    
    public boolean inOrder(E pre, E now) {
        if (pre instanceof Range)
            return ((Range)pre).isLowerThan((Range)now);
        if (pre instanceof Comparable)
            return ((Comparable)pre).compareTo((Comparable)now) < 0;
        return false;
    }
    
    protected AssertOrderListIterator(ListIterator<E> body) {
        this.body = body;
    }
    
    public AssertOrderListIterator(Iterator<E> body) {
        this.body = new SingleBufferedListIterator<>(body);
    }
    
    public boolean require() {
        if (!body.hasPrevious() || !body.hasNext())
            return true;
        E pre = body.previous();
        body.next();
        E now = body.next();
        body.previous();
        return inOrder(pre, now);
    }
    
    @Override
    public E next() {
        E retval = body.next();
        assert require();
        return retval;
    }
    
    @Override
    public boolean hasNext() {
        return body.hasNext();
    }

    @Override
    public void remove() {
        body.remove();
    }

    @Override
    public E previous() {
        return body.previous();
    }

    @Override
    public boolean hasPrevious() {
        return body.hasPrevious();
    }

    @Override
    public int nextIndex() {
        return body.nextIndex();
    }

    @Override
    public int previousIndex() {
        return body.previousIndex();
    }

    @Override
    public void set(E e) {
        body.set(e);
    }

    @Override
    public void add(E e) {
        body.add(e);
    }
}
