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

import static collection.ListOperations.find;
import static collection.ListOperations.findBackward;
import static collection.ListOperations.findNthOccurence;
import java.util.ListIterator;
import java.util.function.Predicate;

/**
 *
 * @author masao
 * @param <T>
 */
public class FilterListIterator<T> implements ListIterator<T> {
    ListIterator<T> iter;
    int index = 0;
    Predicate<? super T> pred;
    Predicate<? super T> inputErr;
    boolean setable;
    boolean removable;

    public FilterListIterator(ListIterator<T> iter, Predicate<? super T> pred, int index, Predicate<? super T> inputErr) {
        this.iter = iter;
        this.pred = pred;
        this.inputErr = inputErr;
        this.index = index;
        this.setable = false;
        this.removable = false;
        findNthOccurence(iter, pred, index - 1);
    }

    public FilterListIterator(ListIterator<T> iter, Predicate<? super T> pred, int index) {
        this(iter, pred, index, e->{throw new RuntimeException("input value does not meet the criteria");});
    }

    public FilterListIterator(ListIterator<T> iter, Predicate<? super T> pred) {
        this(iter, pred, 0);
    }

    @Override
    public boolean hasNext() {
        return find(iter, pred).hasNext();
    }

    @Override
    public T next() {
        index++;
        setable = true;
        removable = true;
        return find(iter, pred).next();
    }

    @Override
    public boolean hasPrevious() {
        return findBackward(iter, pred).hasPrevious();
    }

    @Override
    public T previous() {
        index--;
        setable = true;
        removable = true;
        return findBackward(iter, pred).previous();
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        if (!removable)
            throw new IllegalStateException("");
        iter.remove();
        setable = false;
        removable = false;
        index--;
    }

    @Override
    public void set(T e) {
        if (!setable)
            throw new IllegalStateException("");
        if (pred.test(e) || inputErr.test(e))
            iter.set(e);
    }

    @Override
    public void add(T e) {
        if (pred.test(e) || inputErr.test(e)) {
            iter.add(e);
            removable = false;
            setable = false;
            index++;
        }
    }
}
