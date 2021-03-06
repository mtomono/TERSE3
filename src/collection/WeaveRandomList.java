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

/**
 *
 * @author masao
 * @param <T>
 */
public class WeaveRandomList<T> extends AbstractList<T> implements RandomAccess,Monitorable {
    List<List<T>> base;
    
    public WeaveRandomList(List<List<T>> base) {
        assert WeaveSequentialList.weavable(base);
        this.base = base;
    }
    
    @Override
    public T get(int index) {
        return base.get(index%base.size()).get(index/base.size());
    }

    @Override
    public int size() {
        return base.stream().mapToInt(l->l.size()).sum();
    }
    
    @Override
    public String monitor() {
        return "WeaveRandomList:\n"+TList.set(base).map(l->indent(l)).toFlatString();
    }
}
