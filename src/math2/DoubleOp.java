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
    public Double zero() {
        return Double.valueOf(0);
    }

    @Override
    public Double one() {
        return Double.valueOf(1);
    }
}
