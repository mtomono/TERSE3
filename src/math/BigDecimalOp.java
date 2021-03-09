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

import function.Op;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Operations for BigDecimal.
 * @author masao
 */
public class BigDecimalOp implements Op<BigDecimal>{
    @Override
    public BigDecimal b(int v) {
        return new BigDecimal(v);
    }

    @Override
    public BigDecimal b(long v) {
        return new BigDecimal(v);
    }

    @Override
    public BigDecimal b(float v) {
        return new BigDecimal(v);
    }

    @Override
    public BigDecimal b(double v) {
        return new BigDecimal(v);
    }

    @Override
    public BigDecimal b(String v) {
        return new BigDecimal(v);
    }
    
    @Override
    public BigDecimal b(Rational n) {
        return BigDecimal.valueOf(n.doubleValue());
    }
    
    @Override
    public BigDecimal b(BigDecimal v) {
        return v;
    }

    @Override
    public BigDecimal add(BigDecimal v0, BigDecimal v1) {
        return v0.add(v1);
    }

    @Override
    public BigDecimal sub(BigDecimal v0, BigDecimal v1) {
        return v0.subtract(v1);
    }

    @Override
    public BigDecimal mul(BigDecimal v0, BigDecimal v1) {
        return v0.multiply(v1);
    }

    @Override
    public BigDecimal div(BigDecimal v0, BigDecimal v1) {
        return v0.divide(v1);
    }

    @Override
    public BigDecimal negate(BigDecimal v0) {
        return v0.negate();
    }

    @Override
    public BigDecimal abs(BigDecimal v0) {
        return v0.abs();
    }

    @Override
    public BigDecimal sqrt(BigDecimal v0) {
        return v0.sqrt(new MathContext(v0.scale()));
    }

    @Override
    public BigDecimal zero() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal one() {
        return BigDecimal.ONE;
    }
}
