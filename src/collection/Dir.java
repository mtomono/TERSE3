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

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 *
 * @author mtomono
 */
public class Dir<T> {
    Optional<Dir<T>> upper;
    List<Dir<T>> lower;
    T element;
    
    public Dir(List<Dir<T>> lower) {
        this.lower = lower;
    }
    
    public boolean isTop() {
        return !upper.isPresent();
    }
    
    public boolean isLeaf() {
        return lower.isEmpty();
    }
    
    public void add(Dir<T> lower) {
        this.lower.add(lower);
        lower.upper = Optional.of(this);
    }
    
    public void cut() {
        if (!isTop()) {
            upper.get().lower.remove(this);
            upper = Optional.empty();
        }
    }
    
    public void remove() {
        cut();
        if(!isTop())
            lower.stream().forEach(n->upper.get().add(n));
        else
            lower.stream().forEach(n->n.cut());
    }
    
    public <R> R up(R prev, BiFunction<T, R, R> func) {
        R retval = func.apply(element, prev);
        if (!isTop())
            retval = upper.get().up(retval, func);
        return retval;
    }
    
    public void down(Consumer<T> func) {
        func.accept(element);
        lower.stream().forEach(d->d.down(func));
    }
}
