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

import function.TIntSupplier;
import java.util.AbstractList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author mtomono
 * @param <E>
 */
public class View<E> extends AbstractList<Optional<E>> {
    int index;
    List<E> target;
    IntUnaryOperator at;
    TIntSupplier start;
    TIntSupplier end;
    int size;
    /**
     * let the size be 5, initial state will be like this
     * |4 3 2 1 0|
     * |x x x x x x x x x x x x x ...
     * @param <E>
     * @param target
     * @param buflen
     * @return 
     */
    public static <E> View<E> pre(List<E> target, int buflen) {
        return new View(target, buflen).offset().reverse();
    }
    /**
     * let the size be 5, initial state will be like this
     * |0 1 2 3 4|
     * |x x x x x x x x x x x x ...
     * @param <E>
     * @param target
     * @param buflen
     * @return 
     */
    public static <E> View<E> fore(List<E> target, int buflen) {
        return new View(target, buflen);
    }
    
    public View(List<E> target, int size) {
        this.target = target;
        this.index = 0;
        this.at = (int n) -> n + this.index;
        this.start = ()->0;
        this.end = this.target::size;
        this.size = size;
    }
    
    /**
     * t(rue) 
     * element at index is empty or true to Predicate t
     * @param index
     * @param t
     * @return 
     */
    public boolean t(int index, Predicate<E> t) {
        return !get(index).isPresent() || t.test(get(index).get());
    }
    
    /**
     * g(et)
     * get element at index stripped out of Optional 
     * @param index
     * @return 
     */
    public E g(int index) {
        return get(index).get();
    }
    
    public TIntSupplier start() {
        return start;
    }
    
    public TIntSupplier end() {
        return end;
    }
    
    public View<E> offset() {
        return offset(size-1);
    }
    
    /**
     * get the index offset
     * @param offset
     * @return 
     */
    public View<E> offset(int offset) {
        at = at.compose(n->n+offset);
        return this;
    }
    
    /**
     * get the index direction reverse
     * @return 
     */
    public View<E> reverse() {
        at = at.compose(n->-n);
        return this;
    }
    
    /**
     * whether index is valid or not
     * @param index
     * @return 
     */
    public boolean has(int index) {
        return start.getAsInt() <= at.applyAsInt(index) && at.applyAsInt(index) < end.getAsInt();
    }
    
    public int at(int index) {
        return at.applyAsInt(index);
    }
    
    
    public View<E> index(int index) {
        this.index = index;
        return this;
    }
    
    public List<E> values() {
        return this.stream().map(v->v.get()).collect(toList());
    }
    
    @Override
    public Optional<E> get(int index) {
        if (has(index))
            return Optional.ofNullable(target.get(at(index)));
        return Optional.empty();
    }
    
    @Override
    public int size() {
        return size;
    }    
}
