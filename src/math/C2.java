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
 * Comparable Calculation Context
 * @author masao
 * @param <K>
 */
public class C2<K extends Comparable<K>> implements Context<K,C2<K>>,ComparableContext<C2<K>>,Comparable<C2<K>> {
    static public C2.Builder<Integer> i=new Builder<>(new IntegerOp());
    static public C2.Builder<Long> l=new Builder<>(new LongOp());
    static public C2.Builder<Float> f=new Builder<>(new FloatOp());
    static public C2.Builder<Double> d=new Builder<>(new DoubleOp());
    static public C2.Builder<Rational> r=new Builder<>(new RationalOp());
    static public C2.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp());
    static public C2.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(scale,r));
    }
    static public class Builder<K extends Comparable<K>> implements ContextBuilder<K,C2<K>> {
        final Op<K> body;
        final Function<C2<K>,K> toComparable=x->x.body();
        Builder(Op<K> body) {
            this.body=body;
        }
        @Override
        public C2<K> c(K v) {
            return new C2(this,v);
        }
        @Override
        public Op<K> body() {
            return body;
        }
        @Override
        public ContextBuilder<K, C2<K>> self() {
            return this;
        }
        @Override
        public ContextBuilder<K, C2<K>> wrap(Op<K> body) {
            return new Builder<>(body);
        }
    }
    final Builder<K> builder;
    final K body;
    public C2(Builder<K> builder,K body) {
        this.builder=builder;
        this.body=body;
    }
    @Override
    public ContextBuilder<K, C2<K>> b() {
        return builder;
    }
    @Override
    public K body() {
        return body;
    }
    @Override
    public C2<K> self() {
        return this;
    }
    public boolean isZero() {
        return eq(zero());
    }
    @Override
    public int compareTo(C2<K> o) {
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
        if (!(e instanceof C2)) {
            return false;
        }
        C2 t = (C2) e;
        return body().equals(t.body());
    }
}
