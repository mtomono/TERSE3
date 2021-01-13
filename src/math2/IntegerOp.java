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
 *
 * @author masao
 */
public class IntegerOp implements Op<Integer> {

    @Override
    public Integer b(int v) {
        return v;
    }

    @Override
    public Integer b(long v) {
        return (int)v;
    }

    @Override
    public Integer b(float v) {
        return (int)v;
    }

    @Override
    public Integer b(double v) {
        return (int)v;
    }

    @Override
    public Integer b(String v) {
        return Integer.valueOf(v);
    }
    
    public Integer b(Number n) {
        return n.intValue();
    }

    @Override
    public Integer add(Integer v0, Integer v1) {
        return v0+v1;
    }

    @Override
    public Integer sub(Integer v0, Integer v1) {
        return v0-v1;
    }

    @Override
    public Integer mul(Integer v0, Integer v1) {
        return v0*v1;
    }

    @Override
    public Integer div(Integer v0, Integer v1) {
        return v0/v1;
    }

    @Override
    public Integer negate(Integer v0) {
        return -v0;
    }

    @Override
    public Integer abs(Integer v0) {
        return Math.abs(v0);
    }

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public Integer one() {
        return 1;
    }
    
}
