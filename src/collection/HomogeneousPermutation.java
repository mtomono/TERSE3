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

import static collection.TList.toTList;

/**
 *
 * @author masao
 */
public class HomogeneousPermutation<T> {
    
    static public <T> TList<TList<T>> calc(int target, T x, int merged, T y, int divides) {
        return merge(Divide.divide(target, x, divides), Divide.divide(merged, y, divides-1, 1));
    }
    
    static public <T> TList<TList<T>> calc(TList<T> target, int merged, T e, int divides) {
        return merge(Divide.divide(target, divides),Divide.divide(merged, e, divides-1, 1));
    }

    static public <T> TList<TList<T>> calc(TList<T> target, TList<T> merged, int divides) {
        return merge(Divide.divide(target, divides), Divide.divide(merged, divides-1, 1));
    }
    
    static <T> TList<TList<T>> merge(TList<TList<TList<T>>> target, TList<TList<TList<T>>> merged) {
        return target.cross(merged, (x,y)->TList.concat(x.iterator().twist(y.iterator()).stream().collect(toTList())));
    }
}
