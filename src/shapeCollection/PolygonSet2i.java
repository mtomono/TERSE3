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

import collection.MergedSet;
import collection.TList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import shape.Segment2d;
import shape.TPoint2d;
import shape.TPoint2i;
import shape.TVector2d;

/**
 *
 * @author masao
 */
public class PolygonSet2i implements Set<TPoint2i> {
    TList<TPoint2i> vertices;
    public PolygonSet2i(TList<TPoint2i> vertices) {
        assert vertices.get(0).equals(vertices.last());
        this.vertices = vertices;
    }
    
    TList<Set<TPoint2i>> borders() {
        return vertices.diff((a,b)->new EdgeSet2i(a,b));
    }
    
    public Set<TPoint2i> border() {
        return new MergedSet<>(borders());
    }
    
    public Set<TPoint2i> borderAndBody() {
        return new MergedSet<>(TList.<Set<TPoint2i>>wrap(this).append(borders()));
    }
    
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean contains(Object o) {
        if (!(o instanceof TPoint2i))
            return false;
        TPoint2d t = new TPoint2d((TPoint2i)o);
        Segment2d closest = vertices.map(i->new TPoint2d(i)).diff((a,b)->new Segment2d(a,b)).min(s->s.distance(t)).get();
        return closest.asVector().det(TVector2d.c(closest.start,t))<0;
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
