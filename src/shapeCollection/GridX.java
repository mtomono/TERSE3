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

package shapeCollection;

import collection.TList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author masao
 * @param <T>
 */
public class GridX<T> {
    final public GridCoord axis;
    final public TList<T> body;
    
    public GridX(GridCoord axis, Function<TList<Integer>,T> f) {
        this(axis, TList.range(0,axis.size()).map(i->f.apply(axis.address(i))));
    }
    
    public GridX(GridCoord axis, TList<T> body) {
        assert body.size() >= axis.size() : "body has to be bigger than axis";
        this.axis = axis;
        this.body = body;
    }
    
    static public <T> GridX<T> empty() {
        return new GridX<>(new GridCoord(TList.empty()), TList.empty());
    }
    
    public GridX<T> fix() {
        return new GridX(axis, body.fix());
    }
    
    public GridX<T> sfix() {
        return new GridX(axis, body.sfix());
    }
    
    public GridX<T> straight() {
        return new GridX(axis.straight(), asList());
    }
    
    public T get(Integer... address) {
        return get(TList.sof(address));
    }
    public T get(List<Integer> address) {
        assert axis.contains(address) : address.toString()+" is out of grid.";
        return body.get(axis.index(address));
    }
    
    public T set(T v, Integer... address) {
        return set(v, TList.sof(address));
    }
    public T set(T v, List<Integer> address) {
        return body.set(axis.index(address), v);
    }
    
    /**
     * c(hained)set.set method for chained method.
     * @param v
     * @param address
     * @return
     */
    public GridX<T> cset(T v, Integer... address) {
        return cset(v, TList.sof(address));
    }
    public GridX<T> cset(T v, List<Integer> address) {
        set(v, address);
        return this;
    }
    
    public GridX<T> multiCSet(Collection<List<Integer>> at, Function<List<Integer>,T> f) {
        at.forEach(p->set(f.apply(p),p));
        return this;
    }
    
    public boolean contains(List<Integer> p) {
        return axis.contains(p);
    }

    public TList<T> asList() {
        GridCoord straight = axis.straight();
        return TList.range(0,straight.size()).map(i->get(straight.address(i)));
    }
    
    /**
     * translate to new coordination.
     * new coordination has to be included in the original one.
     * @param slice
     * @return
     */
    public GridX<T> translate(GridCoord slice) {
        assert (axis.contains(slice)) : "slice is not contained in axis";
        return new GridX<>(slice, TList.range(0, slice.size()).map((i) -> get(slice.address(i))));
    }

    public GridX<T> intersect(GridCoord x) {
        return translate(axis.intersect(x));
    }

    public TList<Integer> filterAt(Predicate<T> p) {
        return body.filterAt(p);
    }
    
    public <U> GridX<U> map(Function<T,U> f) {
        return new GridX<>(axis, body.map(f));
    }
    
    public <S,U> GridX<U> pair(GridX<S> slice, BiFunction<T,S,U>f) {
        GridX<S> effectivePart = slice.intersect(axis);
        return new GridX<>(effectivePart.axis,TList.range(0, effectivePart.axis.size()).map((i) -> f.apply(get(effectivePart.axis.address(i)), effectivePart.body.get(i))));
    }
        
    public GridX<T> reverse(Integer... axis) {
        return reverse(TList.sof(axis));
    }
    public GridX<T> reverse(TList<Integer> axis) {
        return new GridX<>(this.axis.reverse(axis), body);
    }
    
    /**
     * yet another reverse.
     * 
     * reverse the axis designated in the parameter. 
     * behavior of this implementation looks natural in terms of asList() compared to 
     * that of reverse(). but after all, this implementation is only a small variation
     * of transform in comparison to the radical change of reverse(). 
     * @param axis
     * @return 
     */
    public GridX<T> reverseYA(Integer... axis) {
        return GridX.this.reverseYA(TList.sof(axis));
    }
    public GridX<T> reverseYA(TList<Integer> axis) {
        return transform(TList::reverse, axis);
    }
    
    public GridX<T> shift(Integer... vector) {
        return shift(TList.sof(vector));
    }
    public GridX<T> shift(List<Integer> vector) {
        return new GridX<>(axis.shift(vector), body);
    }
    
    public GridX<T> transform(TList<Integer> order, Integer... changed) {
        return transform(order, TList.sof(changed));
    }
    public GridX<T> transform(TList<Integer> order, TList<Integer> changed) {
        return new GridX<>(axis.transform(order, changed), body);
    }
    
    public GridX<T> transform(Function<TList<Integer>,TList<Integer>> f, Integer... changed) {
        return transform(f, TList.sof(changed));
    }
    public GridX<T> transform(Function<TList<Integer>, TList<Integer>>f, TList<Integer> changed) {
        return new GridX<>(axis.transform(f, changed), body);
    }
    
    public GridX<T> append(GridX<T>... other) {
        return append(TList.sof(other));
    }
    public GridX<T> append(TList<GridX<T>> other) {
        return GridConcatList.concat(other.startFrom(this));
    }
    
    public GridX<T> flip(TList<Integer> order) {
        return new GridX<>(new GridCoordFlipped(axis, order), body);
    }
    
    public GridX<T> flip(int from, int to) {
        return new GridX<>(new GridCoordFlipped(axis, from, to), body);
    }
}
