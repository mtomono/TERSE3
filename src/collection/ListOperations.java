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

import iterator.ReverseIterator;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author mtomono
 */
public class ListOperations {
    static public <T> T last(List<T> target) {
        return target.get(target.size() - 1);
    }
    
    static public <T> T removeLast(List<T> target) {
        return target.remove(target.size() - 1);
    }
    
    static public <T> T setLast(List<T> target, T e) {
        return target.set(target.size() -1, e);
    }
    
    static public <T> ListIterator<T> endIterator(List<T> target) {
        return target.listIterator(target.size());
    }
    
    /**
     * find an element matches condition
     * if found, next() to the return value would produce the element which
     * satisfy the condition. if no element matches, then return value doesn't
     * have next value.
     * @param <T>
     * @param iter
     * @param pred
     * @return 
     */
    static public <T> ListIterator<T> find(ListIterator<T> iter, Predicate<? super T> pred) {
        while (iter.hasNext())
            if (pred.test(iter.next())) {
                iter.previous();
                break;
            }
        return iter;
    }
    
    static public <T> ListIterator<T> findBackward(ListIterator<T> iter, Predicate<? super T> pred) {
        while (iter.hasPrevious())
            if (pred.test(iter.previous())) {
                iter.next();
                break;
            }
        return iter;
    }
    
    static public <T> Iterator<T> findIterator(ListIterator<T> iter, Predicate<? super T> pred) {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return find(iter, pred).hasNext();
            }
            @Override
            public T next() {
                return find(iter, pred).next();
            }
        };
    }
    
    
    public static <T> ListIterator<T> findNthOccurence(ListIterator<T> iter, Predicate<? super T> pred, int nth) {
        if (nth < 1)
            return iter;
        find(iter, pred);
        for (int i=0; i<nth-1; i++) {
            if (!iter.hasNext())
                break;
            iter.next();
            find(iter, pred);
        }
        return iter;
    }
    
    static public <T> ListIterator<T> trim(ListIterator<T> iter, Predicate<? super T> pred) {
        while (iter.hasNext()) {
            if (pred.test(iter.next()))
                iter.remove();
            else {
                iter.previous();
                break;
            }
        }
        return iter;
    }

    static public <T> ListIterator<T> trimAround(ListIterator<T> iter, Predicate<? super T> pred) {
        trim(new ReverseIterator<>(iter), pred);
        trim(iter, pred);
        return iter;
    }

    static public <T> void trimEdge(List<T> list, Predicate<? super T> pred) {
        trim(list.listIterator(), pred);
        trim(new ReverseIterator<>(endIterator(list)), pred);
    }
    
    /**
     * extract elements that match condition
     * remove those elements from iterator and put it into result list
     * @param <T>
     * @param iter
     * @param pred condition
     * @param result where result is accumulated
     */
    static public <T> void extract(ListIterator<T> iter, Predicate<? super T> pred, List<T> result) {
        while (iter.hasNext()) {
            T e = iter.next();
            if (pred.test(e)) {
                iter.remove();
                result.add(e);
            }
        }
    }
    
    /**
     * replace elements that match condition with values from a supplier
     * @param <T>
     * @param iter
     * @param pred
     * @param sup
     * @param result 
     */
    static public <T> void replace(ListIterator<T> iter, Predicate<? super T> pred, Supplier<T> sup, List<T> result) {
        while (iter.hasNext()) {
            T e = iter.next();
            if (pred.test(e)) {
                iter.set(sup.get());
                result.add(e);
            }
        }
    }
    /**
     * replace elements that match condition with values transformed by a function
     * @param <T>
     * @param iter
     * @param pred
     * @param op
     * @param result 
     */
    static public <T> void replace(ListIterator<T> iter, Predicate<? super T> pred, Function<T, T> op, List<T> result) {
        while (iter.hasNext()) {
            T e = iter.next();
            if (pred.test(e)) {
                iter.set(op.apply(e));
                result.add(e);
            }
        }
    }
    
    static public <T> boolean checkOrdered(List<T> checked, Comparator<T> c) {
        if (checked.isEmpty())
            return true;
        return new PairSequentialList<>(checked, checked.subList(1, checked.size()), (a, b)->c.compare(a, b)).stream().allMatch(i->i<=0);
    }
    
    static public <T> List<T> a2al(T... e) {
        return new ArrayList<>(Arrays.asList(e));
    }
}
