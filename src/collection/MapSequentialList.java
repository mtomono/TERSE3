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
import iterator.MapListIterator;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public class MapSequentialList<S, T> extends AbstractSequentialList<T> implements Monitorable {
    List<S> body;
    Function<S, T> map;
    Function<T, S> rmap;
    
    public MapSequentialList(List<S> body, Function<S, T> map, Function<T, S> rmap) {
        this.body = body;
        this.map = map;
        this.rmap = rmap;
    }
    
    public MapSequentialList(List<S> body, Function<S, T> map) {
        this(body, map, e->{throw new RuntimeException("NoReach : ");});
    }
    @Override
    public ListIterator<T> listIterator(int index) {
        return new MapListIterator<>(body.listIterator(index), map, rmap);
    }

    @Override
    public int size() {
        return body.size();
    }
    
    @Override
    public String monitor() {
        return "MapSequentialList of "+body.size()+" elements:\n"+indent(body);
    }
}
