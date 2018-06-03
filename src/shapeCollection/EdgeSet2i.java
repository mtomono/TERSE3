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

import static java.lang.Math.abs;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class EdgeSet2i implements Set<TPoint2i> {
    TPoint2i from;
    TPoint2i to;
    public EdgeSet2i(TPoint2i from, TPoint2i to) {
        this.from = from;
        this.to = to;
    }
    
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    boolean intersect(double base, double goes, double comparedAt, double target) {
        return abs(comparedAt*goes/base - target) < 0.5;
    }
    
    @Override
    public boolean contains(Object o) {
        if (! new RectSet2i(from,to).contains(o))
            return false;
        if (!(o instanceof TPoint2i))
            return false;
        TPoint2i t = (TPoint2i)o;
        TPoint2i edge = from.to(to);
        TPoint2i relative = from.to(t);
        return intersect(edge.x, edge.y, relative.x-0.5, relative.y)||
               intersect(edge.x, edge.y, relative.x+0.5, relative.y)||
               intersect(edge.y, edge.x, relative.y-0.5, relative.x)||
               intersect(edge.y, edge.x, relative.y+0.5, relative.x);
    }

    @Override
    public Iterator<TPoint2i> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(TPoint2i e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends TPoint2i> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
