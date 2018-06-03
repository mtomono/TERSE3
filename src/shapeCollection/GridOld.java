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
import java.util.function.Function;
import orderedSet.Range;
import shape.TPoint2i;
import string.Strings;

/**
 *
 * @author masao
 */
public class GridOld<T> {
    Range<Integer> xRange;
    Range<Integer> yRange;
    TList<TList<T>> space;
    
    public GridOld(Range<Integer> xRange, Range<Integer> yRange, T fill) {
        this.xRange = xRange;
        this.yRange = yRange;
        this.space = TList.range(yRange).map(n->TList.range(xRange).map(m->fill).fix()).fix();
    }
    
    int xInList(int px) {
        return px-xRange.start();
    }
    
    int yInList(int py) {
        return py-yRange.start();
    }
    
    public T get(TPoint2i at) {
        return space.get(yInList(at.y)).get(xInList(at.x));
    }
    
    public GridOld<T> set(TPoint2i at, T v) {
        space.get(yInList(at.y)).set(xInList(at.x), v);
        return this;
    }
    
    public TList<T> getY(int y) {
        return space.get(xInList(y));
    }
    
    public TList<T> getX(int x) {
        return space.map(l->l.get(yInList(x)));
    }
    
    public String toString(Function<T,String> f) {
        return space.reverse().map(l->Strings.concat(l.map(f))).toWrappedString();
    }
    
    @Override
    public String toString() {
        return toString(T::toString);
    }
}
