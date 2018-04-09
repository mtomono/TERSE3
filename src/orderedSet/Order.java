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

/**
 *
 * @author mtomono
 * @param <T>
 */
abstract public class Order<T extends Comparable<T>> {
    abstract public boolean eq(T c1, T c2);
    abstract public boolean eq(double c1, double c2);
    abstract public boolean eq(long c1, long c2);
    abstract public boolean eq(int c1, int c2);
    abstract public boolean lt(T c1, T c2);
    abstract public boolean lt(double c1, double c2);
    abstract public boolean lt(long c1, long c2);
    abstract public boolean lt(int c1, int c2);
    
    public boolean ne(T      c1, T      c2) { return !eq(c1, c2); }
    public boolean ne(double c1, double c2) { return !eq(c1, c2); }
    public boolean ne(long   c1, long   c2) { return !eq(c1, c2); }
    public boolean ne(int    c1, int    c2) { return !eq(c1, c2); }
    
    public boolean gt(T      c1, T      c2) { return  lt(c2, c1); }
    public boolean gt(double c1, double c2) { return  lt(c2, c1); }
    public boolean gt(long   c1, long   c2) { return  lt(c2, c1); }
    public boolean gt(int    c1, int    c2) { return  lt(c2, c1); }

    public boolean ge(T      c1, T      c2) { return !lt(c1, c2); }
    public boolean ge(double c1, double c2) { return !lt(c1, c2); }
    public boolean ge(long   c1, long   c2) { return !lt(c1, c2); }
    public boolean ge(int    c1, int    c2) { return !lt(c1, c2); }

    public boolean le(T      c1, T      c2) { return !gt(c1, c2); }
    public boolean le(double c1, double c2) { return !gt(c1, c2); }
    public boolean le(long   c1, long   c2) { return !gt(c1, c2); }
    public boolean le(int    c1, int    c2) { return !gt(c1, c2); }
    
    public boolean alwaysTrue(T c1, T c2) {
        return true;
    }
    
    public boolean alwaysFalse(T c1, T c2) {
        return !alwaysTrue(c1, c2);
    }
}
