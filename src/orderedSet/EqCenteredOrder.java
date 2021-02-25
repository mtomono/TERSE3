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

import function.Order;

/**
 *
 * @author mtomono
 * @param <T>
 */
abstract public class EqCenteredOrder<T extends Comparable<T>> implements Order<T> {
    public boolean lt(T      c1, T      c2) { return (!eq(c1, c2)) && c1.compareTo(c2) < 0; }
    public boolean ne(T      c1, T      c2) { return !eq(c1, c2); }
    public boolean gt(T      c1, T      c2) { return  lt(c2, c1); }
    public boolean ge(T      c1, T      c2) { return !lt(c1, c2); }
    public boolean le(T      c1, T      c2) { return !gt(c1, c2); }
}
