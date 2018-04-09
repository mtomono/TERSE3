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

package orderedSet;

import java.util.Iterator;

/**
 *
 * @author masao
 * @param <T>
 */
public class WalkerPp<T extends Comparable<T>> extends WalkerSetOp<T, T, T> {
    public WalkerPp(Iterator<T> left, Iterator<T> right) {
        super(left, right);
    }
    @Override
    public boolean leftIsBehind(T left, T right) {
        return left.compareTo(right) < 0;
    }

    @Override
    public boolean overlaps(T left, T right) {
        return left.compareTo(right) == 0;
    }

    @Override
    public T intersect(T left, T right) {
        return right;
    }
}
