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

import collection.P;
import static iterator.ListIterators.where;
import java.util.ListIterator;

/**
 *
 * @author masao
 */
public class ZLI<S, T> extends ZLIbase<S, T> {
    int index;

    public ZLI(ListIterator<S> iterL, ListIterator<T> iterR, int index) {
        super(iterL, iterR);
        this.index = index;
    }
    
    public ZLI(ListIterator<S> iterL, ListIterator<T> iterR) {
        this(iterL, iterR, where(new ZLIbase(iterL, iterR)));
    }
    
    @Override
    public P<S, T> next() {
        index++;
        return super.next();
    }
    
    @Override
    public P<S, T> previous() {
        index--;
        return super.previous();
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
        iterL.remove();
        iterR.remove();
        index--;
    }

    @Override
    public void set(P<S, T> e) {
        iterL.set(e.l());
        iterR.set(e.r());
    }

    @Override
    public void add(P<S, T> e) {
        iterL.add(e.l());
        iterR.add(e.r());
        index++;
    }
    
}
