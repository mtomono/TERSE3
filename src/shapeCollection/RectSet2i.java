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
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import orderedSet.Range;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class RectSet2i implements Set<TPoint2i> {
    Range<Integer> x;
    Range<Integer> y;
    
    public RectSet2i(TPoint2i a, TPoint2i b) {
        this.x = new Range<>(min(a.x,b.x),max(a.x,b.x)+1);
        this.y = new Range<>(min(a.y,b.y),max(a.y,b.y)+1);
    }

    @Override
    public int size() {
        return (x.end()-x.start())*(y.end()-y.start());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof TPoint2i))
            return false;
        TPoint2i t = (TPoint2i)o;
        return x.contains(t.x)&&y.contains(t.y);
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        return asList().containsAll(c);
    }

    TList<TPoint2i> asList() {
        return TList.range(y.start(), y.end()).flatMap(yy->TList.range(x.start(), x.end()).map(xx->new TPoint2i(xx,yy)));
    }

    @Override
    public Iterator<TPoint2i> iterator() {
        return asList().iterator();
    }

    @Override
    public Object[] toArray() {
        return asList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return asList().toArray(a);
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
