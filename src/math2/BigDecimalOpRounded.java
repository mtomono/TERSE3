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
package math2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Operations for BigDecimal.
 * which does rounding at a certain scale when divided.
 * @author masao
 */
public class BigDecimalOpRounded extends BigDecimalOp{
    final public int scale;
    final public RoundingMode round;
    public BigDecimalOpRounded(int scale, RoundingMode round) {
        this.scale=scale;
        this.round=round;
    }
    @Override
    public BigDecimal div(BigDecimal v0, BigDecimal v1) {
        return v0.divide(v1,scale,round);
    }
}
