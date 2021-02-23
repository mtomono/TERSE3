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

import function.Wrapper;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculation Context.
 * this class intends to allow fluent calculation with number classes like BigDecimal or 
 * Rational.
 * to allow this class to extend to handle complex number, it is not a good idea to make it comparable or 
 * subclass of Number.
 * @author masao
 * @param <K>
 */
public class C<K> implements Context<K,C<K>>,Wrapper<K,C<K>> {
    static public C.Builder<Integer> i=new Builder<>(new IntegerOp());
    static public C.Builder<Long> l=new Builder<>(new LongOp());
    static public C.Builder<Float> f=new Builder<>(new FloatOp());
    static public C.Builder<Double> d=new Builder<>(new DoubleOp());
    static public C.Builder<Rational> r=new Builder<>(new RationalOp());
    static public C.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp());
    static public C.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(scale,r));
    }

    static public class Builder<K> implements ContextBuilder<K,C<K>>{
        public final Op<K> op;
        public Builder(Op<K> op) {
            this.op=op;
        }
        @Override
        public Op<K> body() {
            return op;
        }
        @Override
        public ContextBuilder<K, C<K>> self() {
            return this;
        }
        @Override
        public ContextBuilder<K, C<K>> wrap(Op<K> body) {
            return new Builder(op);
        }
        @Override
        public C<K> c(K v) {
            return new C<>(this,v);
        }
    }
    final Builder<K> b;
    final K v;
    public C(Builder<K> b, K v) {
        this.b=b;
        this.v=v;
    }
    @Override
    public ContextBuilder<K, C<K>> b() {
        return b;
    }
    @Override
    public C<K> wrap(K v) {
        return b.c(v);
    }
    @Override
    public K body() {
        return v;
    }
    @Override
    public C<K> self() {
        return this;
    }
    @Override
    public String toString() {
        return body().toString();
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof C)) {
            return false;
        }
        C t = (C) e;
        return body().equals(t.body());
    }
}
