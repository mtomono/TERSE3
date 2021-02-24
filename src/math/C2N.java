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
import java.math.RoundingMode;
import java.util.function.Function;

/**
 * Comparable Calculation Context which is made of Number
 * @author masao
 * @param <K>
 */
public class C2N<K extends Number&Comparable<K>> implements Context<K, C2N<K>>,ComparableContext<C2N<K>>,Comparable<C2N<K>> {
    static public C2N.Builder<Integer> i=new Builder<>(new IntegerOp());
    static public C2N.Builder<Long> l=new Builder<>(new LongOp());
    static public C2N.Builder<Float> f=new Builder<>(new FloatOp());
    static public C2N.Builder<Double> d=new Builder<>(new DoubleOp());
    static public C2N.Builder<Rational> r=new Builder<>(new RationalOp());
    static public C2N.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp());
    static public C2N.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(scale,r));
    }
    static public class Builder<K extends Number&Comparable<K>> implements ContextBuilder<K,C2N<K>> {
        final Op<K> body;
        final Function<C2N<K>,K> toComparable=x->x.body();
        Builder(Op<K> body) {
            this.body=body;
        }
        @Override
        public C2N<K> c(K v) {
            return new C2N(this,v);
        }
        @Override
        public Op<K> body() {
            return body;
        }
        @Override
        public ContextBuilder<K, C2N<K>> self() {
            return this;
        }
        @Override
        public ContextBuilder<K, C2N<K>> wrap(Op<K> body) {
            return new Builder<>(body);
        }
    }
    final Builder<K> builder;
    final K body;
    public C2N(Builder<K> builder,K body) {
        this.builder=builder;
        this.body=body;
    }
    @Override
    public ContextBuilder<K, C2N<K>> b() {
        return builder;
    }
    @Override
    public K body() {
        return body;
    }
    @Override
    public C2N<K> self() {
        return this;
    }
    public Function<C2N<K>,K> toComparable() {
        return builder.toComparable;
    }
    public boolean isZero() {
        return eq(zero());
    }
    public boolean isInteger() {
        return self().eq(intValue());
    }
    public boolean isLong() {
        return self().eq(longValue());
    }
    public C2N<K> intValue() {
        return b().b(body().intValue());
    }
    public C2N<K> longValue() {
        return b().b(body().longValue());
    }
    @Override
    public int compareTo(C2N<K> o) {
        return body().compareTo(o.body());
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
        if (!(e instanceof C2N)) {
            return false;
        }
        C2N t = (C2N) e;
        return body().equals(t.body());
    }
}
