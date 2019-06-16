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
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class GridCoord {
    final public TList<GridAxis> axis;
    
    public static TList<Integer> vector(List<Integer> from, List<Integer> to) {
        return TList.set(from).pair(to, (a,b)->b-a);
    }
    
    public static TList<Integer> move(List<Integer> from, List<Integer> vector) {
        return TList.set(from).pair(vector,(a,b)->a+b);
    }
    
    public static TList<List<Integer>> allDirs(int nth) {
        return dirsAlternate(nth);
    }
    
    /**
     * all the unit vectors in n dimension space.
     * @param nth
     * @return 
     */
    public static TList<List<Integer>> dirsAround(int nth) {
        TList<Integer> zero = TList.nCopies(nth,0).fix();
        return TList.concat(TList.range(0,nth).map(i->zero.fix().cset(i, 1)),TList.range(0,nth).map(i->zero.fix().cset(i, -1))).map(p->p);
    }
    
    /**
     * .result is the same as allDirs() when it is considered as a set.the only
 difference is the order of the result.
     * but the order is crucial in testing.
 so this method is depreciated
     * @param nth
     * @return 
     */
    public static TList<List<Integer>> dirsAlternate(int nth) {
        TList<Integer> zero = TList.nCopies(nth,0).fix();
        return TList.range(0,nth).flatMap(i->TList.sof(zero.fix().cset(i, 1), zero.fix().cset(i,-1)));
    }
    
    static public GridCoord gcoord(Integer... fromAndTo) {
        assert fromAndTo.length%2==0 : "number of parameter should be even";
        TList<Integer> parameters = TList.sof(fromAndTo);
        return gcoord(parameters.head(fromAndTo.length/2), parameters.tail(fromAndTo.length/2));
    }
    static public GridCoord gcoord(TList<Integer> from, TList<Integer> to) {
        return new GridCoord(from.pair(to, (a,b)->new GridAxis(a,b)));
    }
    
    public GridCoord(GridAxis... axis) {
        this(TList.sof(axis));
    }
    
    public GridCoord(TList<GridAxis> axis) {
        this.axis = axis;
    }
    
    public TList<List<Integer>> dirsAround() {
        return dirsAround(this.axis.size());
    }
    
    public TList<List<Integer>> dirsAlternate() {
        return dirsAlternate(this.axis.size());
    }
    
    public int size() {
        return volume().stream().reduce((a,b)->a*b).orElse(0);
    }
    
    public GridCoord straight() {
        return new GridCoord(axis.map(a->new GridAxis(a.from,a.to)));
    }
        
    public TList<Integer> baseSize() {
        return axis.seek(-1).accum(1, (a,b)->a*(b.size));
    }
    
    public int index(Integer... address) {
        return index(TList.sof(address));
    }
    
    public int rindex(Integer... address) {
        return rindex(TList.sof(address));
    }
    
    public int index(List<Integer> address) {
        return rindex(axis.pair(address, (a,b)->a.raddress(b)));
    }
    
    public int rindex(List<Integer> raddress) {
        return baseSize().pair(raddress, (a,b)->a*b).sumI(i->i);
    }
    
    public TList<Integer> address(int index) {
        return raddress(index).pair(axis, (a,b)->b.address(a));
    }
    
    public TList<Integer> raddress(int index) {
        return baseSize().reverse().accum(index, (a,b)->a%b).seek(-1).pair(baseSize().reverse(), (a,b)->a/b).reverse();
    }
    
    public <U extends List<Integer>> boolean contains(U address) {
        return axis.pair(address, (a,b)->a.vr.contains(b)).forAll(b->b);
    }
    
    public boolean contains(GridCoord o) {
        return axis.pair(o.axis, (a,b)->a.contains(b)).forAll(b->b);
    }
    
    public TList<Integer> from() {
        return axis.map(a->a.from);
    }
    
    public TList<Integer> to() {
        return axis.map(a->a.to);
    }
    
    public TList<Integer> togo() {
        return axis.map(a->a.togo);
    }
    
    public TList<Integer> sigTogo() {
        return axis.map(a->a.sigTogo);
    }
    
    public TList<Integer> volume() {
        return axis.map(a->a.size);
    }
    
    public GridCoord intersect(GridCoord g) {
        TList<Optional<GridAxis>> og = axis.pair(g.axis, (a,b)->a.intersect(b));
        return og.forAll(o->o.isPresent())?new GridCoord(og.map(o->o.get())) : new GridCoord();
    }
    
    public TList<Integer> exclude(GridCoord g) {
        return TList.range(0,size()).filter(i->!g.contains(address(i)));
    }
    
    public TList<List<Integer>> i2a(TList<Integer> index) {
        return index.map(i->address(i));
    }
    
    public GridCoord reverse(Integer... axis) {
        return reverse(TList.sof(axis));
    }
    
    public GridCoord reverse(TList<Integer> axis) {
        return new GridCoord(TList.range(0,this.axis.size()).map(i->axis.contains(i)).pair(this.axis, (r,a)->r?a.reverse():a));
    }
    
    public GridCoord shift(Integer... vector) {
        return shift(TList.sof(vector));
    }
    
    public GridCoord shift(List<Integer> vector) {
        return new GridCoord(axis.pair(vector, (a,v)->a.shift(v)));
    }
    
    public GridCoord transform(Function<TList<Integer>,TList<Integer>> f, Integer... changed) {
        return transform(f, TList.sof(changed));
    }
    
    public GridCoord transform(Function<TList<Integer>,TList<Integer>> f, TList<Integer> changed) {
        TList<GridAxis> retval = axis.fix();
        changed.forEach(i->retval.set(i, new GridAxisOrdered(retval.get(i), f)));
        return new GridCoord(retval);
    }
    
    public GridCoord transform(TList<Integer> order, TList<Integer> changed) {
        TList<GridAxis> retval = axis.fix();
        changed.forEach(i->retval.set(i, new GridAxisOrdered(retval.get(i), order)));
        return new GridCoord(retval);
    }
    
    @Override
    public String toString() {
        return axis.toString();
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof GridCoord)) {
            return false;
        }
        GridCoord t = (GridCoord) e;
        return t.axis.equals(axis);
    }
}
