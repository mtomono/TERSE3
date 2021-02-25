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

package collection;

import iterator.TIterator;
import iterator.TListIterator;
import java.util.RandomAccess;
import java.util.function.Predicate;
import orderedSet.Range;

/**
 * 
 * @author masao
 */
public class ThresholdSearch<T> {
    TList<T> target;
    int start;
    int skip;
    Predicate<T> cond;
    int size;
    
    public ThresholdSearch(TList<T> target, Predicate<T> cond, int start, int skip) {
        this.target = target;
        this.cond = cond;
        this.start = start;
        this.skip = skip;
        this.size = target.size();
        assert !target.isEmpty():"target cannot be empty";
        assert 0<=start&&start<size : "start has to point somewhere in target";
    }
    
    public boolean applyToNone() {
        return !cond.test(target.get(0));
    }
    
    public boolean applyToAll() {
        return cond.test(target.get(size-1));
    }
    
    public int findLast() {
        return findLastPair().r();
    }
    
    /**
     * searches the threshold of some condition.
     * on premise of first part of the list contains items which apply cond and latter
     * part contains intems which do not apply cond.
     * @param start
     * @param skip
     * @param cond
     * @return 
     */
    public P<T,Integer> findLastPair() {
        assert !applyToNone() : "cannot find threshold when apply to none";
        if (applyToAll())
            return P.p(target.get(size-1), size-1);
        TList<Integer> index = TList.skipRange(Range.create(start,size),skip).sfix();
        assert !index.isEmpty() : "target is not empty therefore at least index has start";
        if (!index.last().equals(size-1))
            index=index.append(size-1);
        assert index.size()>1 : "start and end of target is different and both are contained in index";
        TListIterator<Integer> iter = index.listIterator();
        TIterator<T> seekup = TIterator.set(iter).map(i->target.get(i)).until(cond.negate());
        seekup.last();
        assert iter.hasPrevious() : "start must apply to condition";
        iter.previous();
        int last = iter.next();
        TList<Integer> rindex = TList.range(last-skip, last).reverse();
        TListIterator<Integer> riter = rindex.listIterator();
        TIterator<T> seekdown = TIterator.set(riter).map(i->target.get(i)).until(cond);
        T retval = seekdown.last();
        assert riter.hasPrevious() : "seek down must proceed at least one";
        riter.previous();
        return P.p(retval,riter.next());
    }
}
