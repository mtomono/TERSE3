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

package collection;

import debug.Monitorable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public class MapRandomList<S, T> extends AbstractList<T> implements RandomAccess,Monitorable {
    List<S> base;
    Function<? super S, ? extends T> map;
    Function<? super T, ? extends S> rmap;
    
    public MapRandomList(List<S> base, Function<? super S, ? extends T> map, Function<? super T, ? extends S> rmap) {
        this.base = base;
        this.map = map;
        this.rmap = rmap;
    }
    
    public MapRandomList(List<S> base, Function<? super S, ? extends T> map) {
        this(base, map, e->{throw new RuntimeException("tried to change a member of map result which is unchangeable: "+ e);});
    }
    
    @Override
    public T get(int index) {
        return map.apply(base.get(index));
    }

    @Override
    public int size() {
        return base.size();
    }
    
    @Override
    public T remove(int i) {
        return map.apply(base.remove(i));
    }
    
    @Override
    public T set(int i, T o) {
        return map.apply(base.set(i, rmap.apply(o)));
    }
    
    @Override
    public void add(int i, T o) {
        base.add(i, rmap.apply(o));
    }

    @Override
    public String monitor() {
        return "MapRandomList of "+base.size()+" elements:\n"+indent(base);
    }
}
