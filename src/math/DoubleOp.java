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

/**
 * Operations for Double.
 * @author masao
 */
public class DoubleOp implements Op<Double> {
    @Override
    public Double b(int v) {
        return Double.valueOf(v);
    }

    @Override
    public Double b(long v) {
        return Double.valueOf(v);
    }

    @Override
    public Double b(float v) {
        return Double.valueOf(v);
    }

    @Override
    public Double b(double v) {
        return v;
    }

    @Override
    public Double b(String v) {
        return Double.valueOf(v);
    }
    
    @Override
    public Double b(BigDecimal n) {
        return n.doubleValue();
    }

    @Override
    public Double b(Rational n) {
        return n.doubleValue();
    }

    @Override
    public Double add(Double v0, Double v1) {
        return v0+v1;
    }

    @Override
    public Double sub(Double v0, Double v1) {
        return v0-v1;
    }

    @Override
    public Double mul(Double v0, Double v1) {
        return v0*v1;
    }

    @Override
    public Double div(Double v0, Double v1) {
        return v0/v1;
    }

    @Override
    public Double negate(Double v0) {
        return -v0;
    }

    @Override
    public Double abs(Double v0) {
        return Math.abs(v0);
    }

    @Override
    public Double sqrt(Double v0) {
        return Math.sqrt(v0);
    }

    @Override
    public Double zero() {
        return Double.valueOf(0);
    }

    @Override
    public Double one() {
        return Double.valueOf(1);
    }

    @Override
    public Double sign(Double v0) {
        return Math.signum(v0);
    }

    @Override
    public Double sin(Double v0) {
        return Math.sin(v0);
    }

    @Override
    public Double cos(Double v0) {
        return Math.cos(v0);
    }
    
    @Override
    public Double tan(Double v0) {
        return Math.tan(v0);
    }

    @Override
    public Double log(Double v0) {
        return Math.log(v0);
    }
    
    @Override
    public Double log10(Double v0) {
        return Math.log10(v0);
    }
}
