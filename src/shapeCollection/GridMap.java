/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class GridMap<T> extends AbstractMap<TPoint2i, T> {
    Grid<T> body;
    
    public GridMap(Grid<T> body) {
        this.body = body;
    }
    
    @Override
    public Set<Entry<TPoint2i, T>> entrySet() {
        return new HashSet<>(body.x.flatMap(xx->body.y.map(yy->new AbstractMap.SimpleEntry<>(new TPoint2i(xx,yy),body.get(xx,yy)))));
    }

    @Override
    public T get(Object at) {
        if (!(at instanceof TPoint2i))
            return null;
        return body.get((TPoint2i)at);
    }
    
    @Override
    public T put(TPoint2i at, T value) {
        return body.set(at, value);
    }
}
