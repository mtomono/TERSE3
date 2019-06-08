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
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author masao
 * @param <T>
 */
public class GridSimple<T> extends GridAbstract<T> implements GridX<T> {
    final public GridCoord axis;
    final public TList<T> body;
    
    public GridSimple(GridCoord axis, Function<TList<Integer>,T> f) {
        this(axis, TList.range(0,axis.size()).map(i->f.apply(axis.address(i))));
    }
    
    public GridSimple(GridCoord axis, TList<T> body) {
        assert body.size() >= axis.size() : "body has to be bigger than axis";
        this.axis = axis;
        this.body = body;
    }
    
    @Override
    public GridSimple<T> fix() {
        return new GridSimple(axis, body.fix());
    }
    
    @Override
    public GridSimple<T> sfix() {
        return new GridSimple(axis, body.sfix());
    }
    
    @Override
    public GridCoord axis() {
        return axis;
    }
    
    @Override
    public TList<T> body() {
        return body;
    }
    
    @Override
    public T get(List<Integer> address) {
        assert axis().contains(address) : address.toString()+" is out of grid.";
        return body.get(axis().index(address));
    }
    
    
    @Override
    public T set(T v, List<Integer> address) {
        return body.set(axis().index(address), v);
    }
    
        
    @Override
    public TList<T> asList() {
        GridCoord straight = axis().straight();
        return TList.range(0,straight.size()).map(i->get(straight.address(i)));
    }
    
    
    @Override
    public TList<Integer> filterAt(Predicate<T> p) {
        return body.filterAt(p);
    }
    
    @Override
    public <U> GridSimple<U> map(Function<T,U> f) {
        return new GridSimple<>(axis(), body.map(f));
    }
    
    @Override
    public <S,U> GridSimple<U> pair(GridSimple<S> slice, BiFunction<T,S,U>f) {
        GridSimple<S> effectivePart = slice.intersect(axis());
        return new GridSimple<>(effectivePart.axis(), TList.range(0,effectivePart.axis().size()).map(i->f.apply(get(effectivePart.axis().address(i)),effectivePart.body.get(i))));
    }
        
    @Override
    public GridSimple<T> reverse(Integer... axis) {
        return reverse(TList.sof(axis));
    }
    @Override
    public GridSimple<T> reverse(TList<Integer> axis) {
        return new GridSimple<>(axis().reverse(axis), body);
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
    @Override
    public GridSimple<T> reverseYA(Integer... axis) {
        return GridSimple.this.reverseYA(TList.sof(axis));
    }
    @Override
    public GridSimple<T> reverseYA(TList<Integer> axis) {
        return transform(TList::reverse, axis);
    }
    
    @Override
    public GridSimple<T> shift(Integer... vector) {
        return shift(TList.sof(vector));
    }
    
    @Override
    public GridSimple<T> shift(List<Integer> vector) {
        return new GridSimple<>(axis().shift(vector), body);
    }
    
    @Override
    public GridSimple<T> transform(TList<Integer> order, Integer... changed) {
        return transform(order, TList.sof(changed));
    }
    
    @Override
    public GridSimple<T> transform(TList<Integer> order, TList<Integer> changed) {
        return new GridSimple<>(axis().transform(order, changed), body);
    }
    
    @Override
    public GridSimple<T> transform(Function<TList<Integer>,TList<Integer>> f, Integer... changed) {
        return transform(f, TList.sof(changed));
    }

    @Override
    public GridSimple<T> transform(Function<TList<Integer>, TList<Integer>>f, TList<Integer> changed) {
        return new GridSimple<>(axis().transform(f, changed), body);
    }
    
    @Override
    public GridSimple<T> append(GridSimple<T> other) {
        assert axis().axis.size()==other.axis().axis.size() : "in append(), two GridX have to have the same dimension";
        TList<Integer> adjacent = axis().axis.pair(other.axis().axis).filterAt(p->p.l().vr.adjoins(p.r().vr));
        assert adjacent.size()==1 : "in append(), two GridX have to adjoin only in one axis";
        int target = adjacent.get(0);
        assert axis().axis.pair(other.axis().axis,(a,b)->a.vr.equals(b.vr)).pickUp(TList.range(0,axis().axis.size()).filter(i->i!=target)).forAll(b->b) : "in append(), two GridX have the same range for all axis except in which adjoin";
        throw new RuntimeException();
    }
    
    @Override
    public GridSimple<T> flip(TList<Integer> order) {
        return new GridSimple<>(new GridCoordFlipped(axis(), order), body);
    }
    
    @Override
    public GridSimple<T> flip(int from, int to) {
        return new GridSimple<>(new GridCoordFlipped(axis(), from, to), body);
    }

    /**
     * translate to new coordination.
     * new coordination has to be included in the original one.
     * @param slice
     * @return
     */
    @Override
    public GridSimple<T> translate(GridCoord slice) {
        assert (axis().contains(slice)) : "slice is not contained in axis";
        return new GridSimple<>(slice, TList.range(0, slice.size()).map((i) -> get(slice.address(i))));
    }

    public GridSimple<T> intersect(GridCoord x) {
        return translate(axis().intersect(x));
    }
}
