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
import java.util.List;

/**
 * GridCoord Flipped in its axis.
 * 
 * the structure of this class tells one thing.
 * 
 * to keep the structure in the list in GridOverList even after flipping axis,
 * we need to stick to the order of the axis in base GridCoord, because the 
 * list is ordered in the order of base GridCoord.
 * 
 * in index() case, `address` must be reconstituted to relative address before 
 * GridCoordFlipped#rindex() is called. for this purpose, flipped axis list is 
 * necessary. and in address() case, GridCoordFlipped#result of raddress(), which 
 * is already flipped, should be reconstituted to absolute address using flipped 
 * axis. those parts are left for superclass which has flipped axis and are 
 * realized by GridAxis#raddress() and GridAxis#address() at the bottom.
 * 
 * so this class only includes calculations that figure out relative address and 
 * relative index. 
 * 
 * @author masao
 */
public class GridCoordFlipped extends GridCoord{
    GridCoord body;
    TList<Integer> order;
    
    public GridCoordFlipped(GridCoord body, TList<Integer> order) {
        super(body.axis.pickUp(order));
        assert order.sortTo(inc(i->i)).equals(TList.range(0,body.axis.size())) : "incomplete flipping";
        this.body = body;
        this.order = order;
    }
    
    public GridCoordFlipped(GridCoord body, int from, int to) {
        this(body, TList.range(0,body.axis.size()).flip(from, to));
    }
     
    @Override
    public int rindex(List<Integer> address) {
        return body.rindex(TList.set(address).pickUp(order));
    }
        
    @Override
    public TList<Integer> raddress(int index) {
        return body.raddress(index).pickUp(order);
    }
}
