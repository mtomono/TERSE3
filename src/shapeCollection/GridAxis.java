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
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.Integer.signum;
import static java.lang.Math.abs;
import java.util.Optional;
import orderedSet.Range;

/**
 *
 * @author masao
 */
public class GridAxis {
    final public int from;
    final public int to;
    final public int togo;
    final public int size;
    final public int sigTogo;
    final public Range<Integer> vr;
    
    public GridAxis(int from, int to) {
        this.from = from;
        this.to = to;
        this.vr = new Range<>(min(from,to),max(from,to)+1);
        this.togo = to - from;
        this.size = abs(togo)+1;
        this.sigTogo = signum(togo);
    }
    
    public TList<Integer> v() {
        return TList.rangeSym(from, to);
    }
    
    public boolean contains(GridAxis o) {
        return vr.contains(o.vr);
    }
    
    public int raddress(int address) {
        return (address-from)*sigTogo;
    }
        
    public int address(int raddress) {
        return raddress*sigTogo+from;
    }
    
    public Optional<GridAxis> intersect(GridAxis o) {
        Optional<Range<Integer>> or = vr.intersect(o.vr);
        return or.map(r->sigTogo > 0 ? new GridAxis(r.start(), r.end()-1) : new GridAxis(r.end()-1,r.start()));
    }
    
    public GridAxis reverse() {
        return new GridAxis(to, from);
    }
    
    public GridAxis shift(int shift) {
        return new GridAxis(from+shift, to+shift);
    }
    
    @Override
    public String toString() {
        return from + "<->" + to;
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof GridAxis)) {
            return false;
        }
        GridAxis t = (GridAxis) e;
        return t.from==from && t.to==to;
    }
}
