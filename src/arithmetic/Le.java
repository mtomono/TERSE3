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

package arithmetic;

import static arithmetic.NaturalOrder.order;

/**
 *
 * @author mtomono
 */
public class Le extends BiPredicate {
    @Override public boolean o(int    a, int    b) { return order.le(a, b); }
    @Override public boolean o(long   a, long   b) { return order.le(a, b); }
    @Override public boolean o(int    a, long   b) { return order.le(a, b); }
    @Override public boolean o(long   a, int    b) { return order.le(a, b); }
    @Override public boolean o(double a, double b) { return order.le(a, b); }
    @Override public boolean o(int    a, double b) { return order.le(a, b); }
    @Override public boolean o(long   a, double b) { return order.le(a, b); }
    @Override public boolean o(double a, int    b) { return order.le(a, b); }
    @Override public boolean o(double a, long   b) { return order.le(a, b); }
}
