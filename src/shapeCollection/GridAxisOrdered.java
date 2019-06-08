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
import static function.ComparePolicy.inc;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class GridAxisOrdered extends GridAxis {
    final public TList<Integer> v;
    
    public GridAxisOrdered(int from, int to, TList<Integer> order) {
        super(from,to);
        this.v = order;
    }
    
    public GridAxisOrdered(int from, int to, Function<TList<Integer>, TList<Integer>> order) {
        this(from,to,order.apply(TList.rangeSym(from,to)));
    }
    
    public GridAxisOrdered(GridAxis axis, TList<Integer> order) {
        this(axis.from, axis.to, order);
    }
    
    public GridAxisOrdered(GridAxis axis, Function<TList<Integer>, TList<Integer>> order) {
        this(axis.from, axis.to, order.apply(axis.v()));
    }
    
    @Override
    public TList<Integer> v() {
        return v;
    }
    
    @Override
    public int raddress(int address) {
        return super.raddress(address(super.raddress(address)));
    }
    
    @Override
    public int address(int raddress) {
        return v.get(raddress);
    }
    
    public GridAxis reverseO() {
        return new GridAxisOrdered(from,to,v.reverse());
    }
        
    @Override
    public String toString() {
        return v.toString();
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof GridAxis)) {
            return false;
        }
        if (!(e instanceof GridAxisOrdered)) {
            GridAxis t = (GridAxis) e;
            return TList.rangeSym(t.from,t.to).equals(v);
        }
        GridAxisOrdered t = (GridAxisOrdered) e;
        return t.v.equals(v);
    }
}
