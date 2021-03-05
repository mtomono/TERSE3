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
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class OpWrap<K> implements Op<K> {
    final public Op<K> op;
    final public Function<K,K> f;
    public OpWrap(Op<K> op, Function<K,K> f) {
        this.op=op;
        this.f=f;
    }
    @Override
    public K b(int v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(long v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(float v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(double v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(String v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(BigDecimal v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(Rational v) {
        return f.apply(op.b(v));
    }

    @Override
    public K add(K v0, K v1) {
        return f.apply(op.add(v0,v1));
    }

    @Override
    public K sub(K v0, K v1) {
        return f.apply(op.sub(v0,v1));
    }

    @Override
    public K mul(K v0, K v1) {
        return f.apply(op.mul(v0,v1));
    }

    @Override
    public K div(K v0, K v1) {
        return f.apply(op.div(v0,v1));
    }

    @Override
    public K negate(K v0) {
        return f.apply(op.negate(v0));
    }

    @Override
    public K abs(K v0) {
        return f.apply(op.abs(v0));
    }

    @Override
    public K sqrt(K v0) {
        return f.apply(op.sqrt(v0));
    }

    @Override
    public K zero() {
        return f.apply(op.zero());
    }

    @Override
    public K one() {
        return f.apply(op.one());
    }
    
}
