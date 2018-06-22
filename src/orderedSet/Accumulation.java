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

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author mtomono
 * @param <T>
 */
public class Accumulation<T extends Comparable<T>> extends ArrayList<RangeSet<T>> {
    
    private RangeSet<T> overlay(ListIterator<RangeSet<T>> iter, RangeSet<T> added) {
        RangeSet<T> body = iter.next();
        RangeSet<T> carriedOver = body.intersect(added);
        iter.set(body.union(added));
        return carriedOver;
    }
    
    private RangeSet<T> peel(ListIterator<RangeSet<T>> iter, RangeSet<T> removed) {
        RangeSet<T> body = iter.previous();
        RangeSet<T> borrowedUnder = body.mask(removed);
        iter.set(body.maskedBy(removed));
        return borrowedUnder;
    }

    public void overlay(RangeSet<T> e) {
        ListIterator<RangeSet<T>> iter = listIterator();
        RangeSet<T> carriedOver = e;
        while (iter.hasNext())
            carriedOver = overlay(iter, carriedOver);
        if (!carriedOver.isEmpty())
            add(carriedOver);
    }
    
    public RangeSet<T> peel(RangeSet<T> e) {
        ListIterator<RangeSet<T>> iter = listIterator(size());
        RangeSet<T> borrowedUnder = e;
        while (iter.hasPrevious()) 
            borrowedUnder = peel(iter, borrowedUnder);
        if (get(size() - 1).isEmpty())
            remove(size() - 1);
        return borrowedUnder;
    }
}
