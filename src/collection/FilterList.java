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
import iterator.FilterListIterator;
import static iterator.Iterators.count;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

/**
 *
 * @author masao
 * @param <T>
 */
public class FilterList<T> extends AbstractSequentialList<T> implements Monitorable {
    List<T> base;
    Predicate<T> cond;
    public FilterList(List<T> base, Predicate<T> cond) {
        this.base = base;
        this.cond = cond;
    }
    @Override
    public ListIterator<T> listIterator(int index) {
        ListIterator<T> iter = new FilterListIterator<>(base.listIterator(), cond);
        for (int i = 0; i < index; i++)
            iter.next();
        return iter;
    }

    @Override
    public int size() {
        return count(listIterator());
    }

    @Override
    public String monitor() {
        return "FilterList of "+base.size()+" elements:\n"+indent(base);
    }
}
