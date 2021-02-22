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

import java.math.BigDecimal;

/**
 * Operations for Double.
 * @author masao
 */
public class FloatOp implements Op<Float> {
    @Override
    public Float b(int v) {
        return Float.valueOf(v);
    }

    @Override
    public Float b(long v) {
        return Float.valueOf(v);
    }

    @Override
    public Float b(float v) {
        return v;
    }

    @Override
    public Float b(double v) {
        return (float)v;
    }

    @Override
    public Float b(String v) {
        return Float.valueOf(v);
    }
    
    @Override
    public Float b(BigDecimal n) {
        return n.floatValue();
    }

    @Override
    public Float b(Rational n) {
        return n.floatValue();
    }


    @Override
    public Float add(Float v0, Float v1) {
        return v0+v1;
    }

    @Override
    public Float sub(Float v0, Float v1) {
        return v0-v1;
    }

    @Override
    public Float mul(Float v0, Float v1) {
        return v0*v1;
    }

    @Override
    public Float div(Float v0, Float v1) {
        return v0/v1;
    }

    @Override
    public Float negate(Float v0) {
        return -v0;
    }

    @Override
    public Float abs(Float v0) {
        return Math.abs(v0);
    }

    @Override
    public Float zero() {
        return Float.valueOf(0);
    }

    @Override
    public Float one() {
        return Float.valueOf(1);
    }
}
