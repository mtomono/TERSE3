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

import arithmetic.Arithmetic;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author masao
 */
public class RotateListIterator<T> implements ListIterator<T> {
    List<T> base;
    int index;
    
    public RotateListIterator(List<T> base, int index) {
        this.base = base;
        this.index = Arithmetic.mod.o(index, base.size());
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        T retval = base.get(nextIndex());
        index = index+1<base.size() ? index+1 : 0;
        return retval;
    }

    @Override
    public boolean hasPrevious() {
        return true;
    }

    @Override
    public T previous() {
        T retval = base.get(previousIndex());
        index = previousIndex();
        return retval;
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index==0 ? base.size()-1 : index-1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
