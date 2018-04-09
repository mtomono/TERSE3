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

import static java.lang.Math.*;

/**
 * This is a ceil for end-exclusion policy.
 * when it comes to the result value, this version of ceil is more consistent 
 * than the ordinary version of ceil. You will see the difference when you 
 * calculate double value.
 * @author mtomono
 */
public class CeilX extends UniOperator{
    @Override
    public int o(int a) { return a + 1; }
    @Override
    public long o(long a) { return a + 1; }
    @Override
    public double o(double a) {
        long retval = (long)ceil(a);
        for (; retval <= a; retval++);
        return retval;
    }
}
