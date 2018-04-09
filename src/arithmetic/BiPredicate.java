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

/**
 *
 * @author mtomono
 */
abstract  public class BiPredicate {
    abstract public boolean o(int a, int b);
    abstract public boolean o(long a, long b);
    abstract public boolean o(int a, long b);
    abstract public boolean o(long a, int b);
    abstract public boolean o(double a, double b);
    abstract public boolean o(int a, double b);
    abstract public boolean o(long a, double b);
    abstract public boolean o(double a, int b);
    abstract public boolean o(double a, long b);
    public boolean o(Number left, Number right) {
        assert left != null;
        assert right != null;
        if (left instanceof Double || right instanceof Double)
            return o(left.doubleValue(), right.doubleValue());
        else if (left instanceof Long || right instanceof Long)
            return o(left.longValue(), right.longValue());
        else 
            return o(left.intValue(), (int)right.intValue());
    }
}
