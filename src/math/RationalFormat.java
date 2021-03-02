/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

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

import java.text.NumberFormat;
import java.text.ParseException;
import static math.LongFormat.longValue;

/**
 *
 * @author masao
 */
public class RationalFormat implements Format<Rational> {
    @Override
    public Rational f(String v) throws ParseException {
        String[] n=v.split("/");
        return n.length==1?
                new Rational(longValue(v),1):
                new Rational(longValue(n[0]),longValue(n[1]));
    }

    @Override
    public String toString(Rational v) {
        return NumberFormat.getInstance().format(v.numerator)+"/"+NumberFormat.getInstance().format(v.denominator);
    }
}
