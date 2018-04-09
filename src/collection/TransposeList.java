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

import static collection.ListOperations.checkOrdered;
import java.util.AbstractList;
import java.util.List;

/**
 *
 * @author masao
 */
public class TransposeList<T> extends AbstractList<List<T>> {
    List<List<T>> body;
    
    public TransposeList(List<List<T>> body) {
        assert checkOrdered(new MapSequentialList<>(body, l->l.size()), (a, b)->b-a);
        this.body = body;
    }
    
    @Override
    public List<T> get(int index) {
        return new MapRandomList<>(new FilterList<>(body, l->l.size()>index), l->l.get(index));
    }

    @Override
    public int size() {
        return body.isEmpty() ? 0 : body.get(0).size();
    }
    
}
