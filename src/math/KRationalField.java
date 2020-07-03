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

import static math.KRational.one;
import static math.KRational.zero;

/**
 *
 * @author masao
 */
public class KRationalField implements DecimalField<KRational> {

    @Override
    public KRational zero() {
        return zero;
    }

    @Override
    public KRational one() {
        return one;
    }

    @Override
    public KRational k(int i) {
        return new KRational(i,1);
    }

    @Override
    public KRational k(long l) {
        return new KRational(l,1);
    }
    
}
