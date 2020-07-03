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
package math;

import static math.KDouble.ONE;
import static math.KDouble.ZERO;

/**
 *
 * @author masao
 */
public class KDoubleField implements DecimalField<KDouble> {

    @Override
    public KDouble zero() {
        return ZERO;
    }

    @Override
    public KDouble one() {
        return ONE;
    }

    @Override
    public KDouble k(int i) {
        return new KDouble(i);
    }

    @Override
    public KDouble k(long l) {
        return new KDouble(l);
    }
    
}
