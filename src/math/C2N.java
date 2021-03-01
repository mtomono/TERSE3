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

import function.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import function.NaturalOrder;

/**
 * Comparable Calculation Context which is made of Number
 * @author masao
 * @param <K>
 */
public class C2N<K extends Number> implements ContextOrdered<K, C2N<K>>,ContextNumber<K,C2N<K>> {
    static public C2N.Builder<Integer> i=new Builder<>(new IntegerOp(), new IntegerFormat(), new NaturalOrder<>());
    static public C2N.Builder<Long> l=new Builder<>(new LongOp(), new LongFormat(), new NaturalOrder<>());
    static public C2N.Builder<Float> f=new Builder<>(new FloatOp(), new FloatFormat(), new NaturalOrder<>());
    static public C2N.Builder<Double> d=new Builder<>(new DoubleOp(), new DoubleFormat(), new NaturalOrder<>());
    static public C2N.Builder<Rational> r=new Builder<>(new RationalOp(), new RationalFormat(), new NaturalOrder<>());
    static public C2N.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp(), new BigDecimalFormat(), new NaturalOrder<>());
    static public C2N.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(scale,r), new BigDecimalFormat(), new NaturalOrder<>());
    }
    static public class Builder<K extends Number> implements ContextBuilder<K,C2N<K>>,ContextOrder<K,C2N<K>> {
        final Op<K> body;
        final Format<K> format;
        final Order<K> order;
        Builder(Op<K> body, Format<K> format, Order<K> order) {
            this.body=body;
            this.format=format;
            this.order=order;
        }
        @Override
        public Format<K> format() {
            return format;
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
            return new Builder<>(body,format,order);
        }
        public Order<K> baseOrder() {
            return order;
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
    @Override
    public Order<C2N<K>> order() {
        return builder;
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
