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
import static java.lang.Integer.min;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public class ZipRandomList<S, T> extends AbstractList<P<S, T>> implements RandomAccess,Monitorable {
    List<S> left;
    List<T> right;
    
    public ZipRandomList(List<S> left, List<T> right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public P<S, T> get(int index) {
        return P.p(left.get(index), right.get(index));
    }

    @Override
    public int size() {
        return min(left.size(), right.size());
    }
    
    @Override
    public String monitor() {
        return "ZipRandomList:\n"+indent(left)+indent(right);
    }
}
