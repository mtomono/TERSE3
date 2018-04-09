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

import java.util.Comparator;

/**
 *
 * @author mtomono
 */
public class OrderComparator<T extends Comparable<T>> implements Comparator<T> {
    Order<T> body;
    
    public OrderComparator(Order<T> body) {
        this.body = body;
    }
    
    @Override
    public int compare(T t, T t1) {
        if (body.lt(t, t1))
            return -1;
        else if (body.gt(t, t1))
            return 1;
        else
            return 0;
    }
}
