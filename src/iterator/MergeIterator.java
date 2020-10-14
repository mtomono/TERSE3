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

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author masao
 * @param <T>
 */
public class MergeIterator<T> implements Iterator<T> {
    BufferedIterator<T> left;
    BufferedIterator<T> right;
    Comparator<T> c;
        
    public MergeIterator(Iterator<T> left, Iterator<T> right, Comparator<T> c) {
        this.left = new BufferedIterator(left);
        this.right = new BufferedIterator(right);
        this.c = c;
    }
        
    @Override
    public boolean hasNext() {
        return left.hasNext() || right.hasNext();
    }

    @Override
    public T next() {
        if (!left.hasNext() && !right.hasNext())
            throw new NoSuchElementException();
        if (!left.hasNext() && right.hasNext())
            return right.next();
        if (left.hasNext() && !right.hasNext())
            return left.next();
        if (c.compare(left.peek(), right.peek())<0)
            return left.next();
        else
            return right.next();
    }    
}
