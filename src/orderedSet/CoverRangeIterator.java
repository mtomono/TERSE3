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

import iterator.AbstractBufferedIterator;
import java.util.Iterator;
import static arithmetic.Arithmetic.*;

/**
 * Translate discreet point list into range.
 * for example, if you have list like {0, 1, 2, 4, 5, 6, 9}, it will be translated as
 * {(0, 3), (4, 7), (9, 10)}
 * So to do that, this class quantizes the target list by base and unit
 * @author mtomono
 * @param <T>
 */
public class CoverRangeIterator<T extends Number & Comparable<T>> extends AbstractBufferedIterator<Range<T>> {
    T unit;
    T base;
    Iterator<T> iter;
    boolean ready;
    T start;
    T end;
    
    final boolean checkBase() {
        this.iter = new AssertOrderListIterator<T>(this.iter) {
            @Override
            public boolean inOrder(T pre, T now) {
                return lt.o(pre, now);
            }
        };
        return true;
    }
    
    public CoverRangeIterator(Iterator<T> iter, T base, T unit) {
        this.base = base;
        this.unit = unit;
        this.iter = iter;
        assert checkBase();
        this.ready = false;
        if (iter.hasNext()) {
            this.ready = true;
            this.start = floor(iter.next());
            this.end = (T)add.o(start, unit);
        }
    }
    
    final T ceil(Number target) {
        return (T)mul.o(ceilx.o(div.o(sub.o(target, base), unit)), unit);
    }
    
    final T floor(Number target) {
        return (T)mul.o(floor.o(div.o(sub.o(target, base), unit)), unit);
    }
    
    @Override
    protected void findNext() {
        if (!ready)
            return;
        T one = null;
        while (true) {
            if (!iter.hasNext()) {
                ready = false;
                break;
            }
            one = iter.next();
            if (ge.o(one, add.o(end, unit)))
                break;
            end = (T)ceil(one);
        }
        nextFound(new Range<>(start, end));
        if (one != null) {
            start = floor(one);
            end = (T)add.o(start, unit);
        }
    }
}
