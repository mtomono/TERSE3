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
import java.util.ListIterator;

/**
 * Z(ip)L(ist)I(terator) base.
 * @author masao
 * @param <S>
 * @param <T>
 */
public class ZLIbase<S, T> implements ListIterator<P<S, T>> {
    ListIterator<S> iterL;
    ListIterator<T> iterR;
    
    public ZLIbase(ListIterator<S> iterL, ListIterator<T> iterR) {
        this.iterL = iterL;
        this.iterR = iterR;
    }
    
    @Override
    public boolean hasNext() {
        return iterL.hasNext() && iterR.hasNext();
    }

    @Override
    public P<S, T> next() {
        if (!hasNext())
            throw new IndexOutOfBoundsException();
        return P.p(iterL.next(), iterR.next());
    }

    @Override
    public boolean hasPrevious() {
        return iterL.hasPrevious() && iterR.hasPrevious();
    }

    @Override
    public P<S, T> previous() {
        if (!hasPrevious())
            throw new IndexOutOfBoundsException();
        return P.p(iterL.previous(), iterR.previous());
    }

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(P<S, T> e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(P<S, T> e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
