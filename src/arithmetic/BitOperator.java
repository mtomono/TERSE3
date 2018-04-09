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
public class BitOperator {
    static int fullbit = 0xFFFFFFFF;
    int mask;
    public int range;
    public int max;
    
    public BitOperator(int range) {
        this.range = range;
        this.mask = ~(fullbit << range);
        this.max = 1 << range;
    }
        
    public int rot(int target, int n) {
        return ((target << n) & mask) | (target >> range - n);
    }
}
