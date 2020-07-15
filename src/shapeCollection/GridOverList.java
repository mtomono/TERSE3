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
public class GridOverList<T> {
    final public GridCoord axis;
    final public TList<T> body;
    
    public GridOverList(GridCoord axis, Function<TList<Integer>,T> f) {
        this(axis, TList.range(0,axis.size()).map(i->f.apply(axis.address(i))));
    }
    
    public GridOverList(GridCoord axis, TList<T> body) {
        assert body.size() >= axis.size() : "body has to be bigger than axis";
        this.axis = axis;
        this.body = body;
    }
    
    static public <T> GridOverList<T> empty() {
        return new GridOverList<>(new GridCoord(TList.empty()), TList.empty());
    }
    
    public GridOverList<T> fix() {
        return new GridOverList(axis, body.fix());
    }
    
    public GridOverList<T> sfix() {
        return new GridOverList(axis, body.sfix());
    }
    
    public GridOverList<T> straight() {
        return new GridOverList(axis.straight(), asList());
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
    public GridOverList<T> cset(T v, Integer... address) {
        return cset(v, TList.sof(address));
    }
    public GridOverList<T> cset(T v, List<Integer> address) {
        set(v, address);
        return this;
    }
    
    public GridOverList<T> multiCSet(Collection<List<Integer>> at, Function<List<Integer>,T> f) {
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
    public GridOverList<T> translate(GridCoord slice) {
        assert (axis.contains(slice)) : "slice is not contained in axis";
        return new GridOverList<>(slice, TList.range(0, slice.size()).map((i) -> get(slice.address(i))));
    }

    public GridOverList<T> intersect(GridCoord x) {
        return translate(axis.intersect(x));
    }

    public TList<Integer> filterAt(Predicate<T> p) {
        return body.filterAt(p);
    }
    
    public TList<List<Integer>> addressOf(Predicate<T> p) {
        return axis.i2a(filterAt(p));
    }
    
    public <U> GridOverList<U> map(Function<T,U> f) {
        return new GridOverList<>(axis, body.map(f));
    }
    
    public <S,U> GridOverList<U> pair(GridOverList<S> slice, BiFunction<T,S,U>f) {
        GridOverList<S> effectivePart = slice.intersect(axis);
        return new GridOverList<>(effectivePart.axis,TList.range(0, effectivePart.axis.size()).map((i) -> f.apply(get(effectivePart.axis.address(i)), effectivePart.body.get(i))));
    }
        
    public GridOverList<T> reverse(Integer... axis) {
        return reverse(TList.sof(axis));
    }
    public GridOverList<T> reverse(TList<Integer> axis) {
        return new GridOverList<>(this.axis.reverse(axis), body);
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
    public GridOverList<T> reverseYA(Integer... axis) {
        return GridOverList.this.reverseYA(TList.sof(axis));
    }
    public GridOverList<T> reverseYA(TList<Integer> axis) {
        return transform(TList::reverse, axis);
    }
    
    public GridOverList<T> shift(Integer... vector) {
        return shift(TList.sof(vector));
    }
    public GridOverList<T> shift(List<Integer> vector) {
        return new GridOverList<>(axis.shift(vector), body);
    }
    
    public GridOverList<T> transform(TList<Integer> order, Integer... changed) {
        return transform(order, TList.sof(changed));
    }
    public GridOverList<T> transform(TList<Integer> order, TList<Integer> changed) {
        return new GridOverList<>(axis.transform(order, changed), body);
    }
    
    public GridOverList<T> transform(Function<TList<Integer>,TList<Integer>> f, Integer... changed) {
        return transform(f, TList.sof(changed));
    }
    public GridOverList<T> transform(Function<TList<Integer>, TList<Integer>>f, TList<Integer> changed) {
        return new GridOverList<>(axis.transform(f, changed), body);
    }
    
    public GridOverList<T> append(GridOverList<T>... other) {
        return append(TList.sof(other));
    }
    public GridOverList<T> append(TList<GridOverList<T>> other) {
        return GridConcatList.concat(other.startFrom(this));
    }
    
    public GridOverList<T> flip(TList<Integer> order) {
        return new GridOverList<>(new GridCoordFlipped(axis, order), body);
    }
    
    public GridOverList<T> flip(int from, int to) {
        return new GridOverList<>(new GridCoordFlipped(axis, from, to), body);
    }
    
    public TList<GridOverList<T>> dissolve(Integer axisIndex) {
        GridCoord lower = new GridCoord(axis.axis.hide(TList.sof(axisIndex)));
        return axis.axis.get(axisIndex).v().map(i->new GridOverList<T>(lower, TList.range(0,lower.size()).map(j->get(lower.address(j).insertAt(axisIndex,i)))));
    }

    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof GridOverList)) {
            return false;
        }
        GridOverList t = (GridOverList) e;
        if (!t.axis.contains(axis))
            return false;
        if (!axis.contains(t.axis))
            return false;
        return TList.range(0,axis.size()).map(i->axis.address(i)).forAll(a->get(a).equals(t.get(a)));
    }
    
    public TList<String> toStrings(String indent) {
        if (axis.axis.size()>2)
            return dissolve(axis.axis.size()-1).flatMap(g->g.toStrings(indent)).toIndentedStrings(indent);
        else
            return dissolve(axis.axis.size()-1).map(g->g.asList().toString()).toIndentedStrings(indent);
    }
    
    @Override
    public String toString() {
        if (axis.size()==1)
            return asList().toString();
        return toStrings("  ").toWrappedString();
    }
    
    public String toStringFlat() {
        if (axis.axis.size()>1)
            return dissolve(0).map(g->g.toStringFlat()).toString();
        else
            return asList().toString();
    }
}
