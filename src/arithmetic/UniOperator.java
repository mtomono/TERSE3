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
abstract public class UniOperator {
    abstract public int o(int a);
    abstract public long o(long a);
    abstract public double o(double a);
    public Number o(Number a) {
        assert a != null;
        if (a instanceof Double)
            return o(a.doubleValue());
        else if (a instanceof Long)
            return o(a.longValue());
        else 
            return o(a.intValue());
    }
}
