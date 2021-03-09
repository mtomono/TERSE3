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
 *
 * @author masao
 */
public class LongOp implements Op<Long> {

    @Override
    public Long b(int v) {
        return (long)v;
    }

    @Override
    public Long b(long v) {
        return v;
    }

    @Override
    public Long b(float v) {
        return (long)v;
    }

    @Override
    public Long b(double v) {
        return (long)v;
    }

    @Override
    public Long b(String v) {
        return Long.valueOf(v);
    }
    
    @Override
    public Long b(BigDecimal n) {
        return n.longValue();
    }

    @Override
    public Long b(Rational n) {
        return n.longValue();
    }

    @Override
    public Long add(Long v0, Long v1) {
        return v0+v1;
    }

    @Override
    public Long sub(Long v0, Long v1) {
        return v0-v1;
    }

    @Override
    public Long mul(Long v0, Long v1) {
        return v0*v1;
    }

    @Override
    public Long div(Long v0, Long v1) {
        return v0/v1;
    }

    @Override
    public Long negate(Long v0) {
        return -v0;
    }

    @Override
    public Long abs(Long v0) {
        return Math.abs(v0);
    }
    
    @Override
    public Long sqrt(Long v0) {
        return (long)Math.round(Math.sqrt(v0));
    }

    @Override
    public Long zero() {
        return 0L;
    }

    @Override
    public Long one() {
        return 1L;
    }
    
}
