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

import java.util.AbstractList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntUnaryOperator;

/**
 *
 * @author mtomono
 * @param <E>
 */
public class RingBufferView<E> extends AbstractList<Optional<E>> {
    List<E> target;
    IntUnaryOperator at;
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
    public static <E> RingBufferView<E> pre(List<E> target, int buflen) {
        return new RingBufferView(target, buflen, n->(buflen-1)-n);
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
    public static <E> RingBufferView<E> fore(List<E> target, int buflen) {
        return new RingBufferView(target, buflen);
    }
    
    public RingBufferView(List<E> target, int size) {
        this(target, size, n->n);
    }
    
    public RingBufferView(List<E> target, int size, IntUnaryOperator at) {
        this.target = target;
        this.at = at;
        this.size = size;
    }
    
    /**
     * whether index is valid or not
     * @param index
     * @return 
     */
    boolean indexIsValid(int index) {
        return 0 <= at.applyAsInt(index) && at.applyAsInt(index) < target.size();
    }
    
    public int at(int index) {
        return at.applyAsInt(index);
    }
    
    @Override
    public Optional<E> get(int index) {
        if (indexIsValid(index))
            return Optional.ofNullable(target.get(at(index)));
        return Optional.empty();
    }
    
    @Override
    public int size() {
        return size;
    }    
}
