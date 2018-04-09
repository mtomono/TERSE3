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
import static java.lang.Math.abs;

/**
 *
 * @author mtomono
 * @param <T>
 */
public class ErroredOrder<T extends Comparable<T>> extends EqCenteredOrder<T> {
    double eps;
    
    public ErroredOrder(double eps) {
        this.eps = eps;
    }
    
    @Override
    public boolean eq(T c1, T c2) {
        if (c1 instanceof Double || c2 instanceof Double) {
            return abs(((Double)c1) - ((Double)c2)) < eps;
        } else {
            return c1.compareTo(c2) == 0;
        }
    }

    @Override public boolean eq(double c1, double c2) { return abs(c1 - c2) < eps; }
    @Override public boolean eq(long   c1, long   c2) { return abs(c1 - c2) < eps; }
    @Override public boolean eq(int    c1, int    c2) { return abs(c1 - c2) < eps; }
}
