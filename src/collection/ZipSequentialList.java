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
import iterator.ZLI;
import static java.lang.Integer.min;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public class ZipSequentialList<S, T> extends AbstractSequentialList<P<S, T>> implements Monitorable {
    List<S> left;
    List<T> right;
    
    public ZipSequentialList(List<S> left, List<T> right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public ListIterator<P<S, T>> listIterator(int index) {
        return new ZLI<>(left.listIterator(index), right.listIterator(index), index);
    }

    @Override
    public int size() {
        return min(left.size(), right.size());
    }
    
    @Override
    public String monitor() {
        return "ZipSequentialList:\n"+indent(left)+indent(right);
    }
}
