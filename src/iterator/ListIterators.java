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

package iterator;

import static iterator.Iterators.count;
import static iterator.Iterators.wind;
import java.util.ListIterator;

/**
 *
 * @author masao
 */
public class ListIterators {
    public static int where(ListIterator<?> iter) {
        int count = count(new ReverseIterator(iter));
        wind(iter, count);
        return count;
    }
    
    public static <T> ListIterator<T> rewind(ListIterator<T> iter) {
        while (iter.hasPrevious())
            iter.previous();
        return iter;
    }

}
