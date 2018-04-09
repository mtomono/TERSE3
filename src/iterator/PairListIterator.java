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
import java.util.function.BiFunction;

/**
 *
 * @author masao
 */
public class PairListIterator<S, T, U> implements ListIterator<U> {
    ListIterator<S> left;
    ListIterator<T> right;
    BiFunction<S, T, U> map;
    int index;
    
    public PairListIterator(ListIterator<S> left, ListIterator<T> right, BiFunction<S, T, U> map, int index) {
        this.left = left;
        this.right = right;
        this.map = map;
        this.index = index;
    }
    
    @Override
    public boolean hasNext() {
        return left.hasNext() && right.hasNext();
    }

    @Override
    public U next() {
        index++;
        return map.apply(left.next(), right.next());
    }

    @Override
    public boolean hasPrevious() {
        return left.hasPrevious() && right.hasPrevious();
    }

    @Override
    public U previous() {
        index--;
        return map.apply(left.previous(), right.previous());
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
    public void set(U e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(U e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
