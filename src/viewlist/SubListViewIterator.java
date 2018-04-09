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

package viewlist;

import function.Holder;
import iterator.AbstractBufferedIterator;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntSupplier;

/**
 * iterates sublists of a list
 * @author mtomono
 */
public class SubListViewIterator<T> extends AbstractBufferedIterator<List<T>> {
    List<T> base;
    IntSupplier fromSupplier;
    IntSupplier toSupplier;
    
    static public IntSupplier incr(int from) {
        Holder<Integer> h = new Holder<>(from);
        return ()->h.push(h.get() + 1);
    }
    
    static public IntSupplier decr(int from) {
        Holder<Integer> h = new Holder<>(from);
        return ()->h.push(h.get() - 1);
    }
    
    public SubListViewIterator(List<T> base, IntSupplier fromSupplier, IntSupplier toSupplier) {
        this.base = base;
        this.fromSupplier = fromSupplier;
        this.toSupplier = toSupplier;
    }
    
    @Override
    protected void findNext() {
        int from = fromSupplier.getAsInt();
        int to = toSupplier.getAsInt();
        if (!(from <= to && 0 <= from && to <= base.size())) 
            return;
        nextFound(base.subList(from, to));
    }

}
