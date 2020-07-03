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

import java.math.BigDecimal;
import orderedSet.Range;

/**
 *
 * @author masao
 */
public class Interpolate {
    static public double interpolateD(Range<Double> range, double ratio) {
        return interpolateD(range.start(),range.end(),ratio);
    }
    
    static public BigDecimal interpolateB(Range<BigDecimal> range, BigDecimal ratio) {
        return interpolateB(range.start(),range.end(),ratio);
    }
    
    static public double interpolateD(double from, double to,double ratio) {
        return from*(1-ratio)+to*ratio;
    }
    
    static public BigDecimal interpolateB(BigDecimal from, BigDecimal to, BigDecimal ratio) {
        return from.multiply(BigDecimal.ONE.subtract(ratio)).add(to.multiply(ratio));
    }
}
