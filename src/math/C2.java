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
import function.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import function.Wrapper;
import java.util.function.BiPredicate;

/**
 * Comparable Calculation Context
 * @author masao
 * @param <K>
 */
public class C2<K> implements ContextOrdered<K,C2<K>> {
    static public C2.Builder<String> s=new Builder<>(new StringOp(), new StringFormat(), Order.natural());
    static public C2.Builder<Integer> i=new Builder<>(new IntegerOp(), new IntegerFormat(), Order.natural());
    static public C2.Builder<Long> l=new Builder<>(new LongOp(), new LongFormat(), Order.natural());
    static public C2.Builder<Float> f=new Builder<>(new FloatOp(), new FloatFormat(), Order.natural());
    static public C2.Builder<Double> d=new Builder<>(new DoubleOp(), new DoubleFormat(), Order.natural());
    static public C2.Builder<Double> dc=C2.byComparison(new DoubleOp(), new DoubleFormat(), Order.natural());
    static public C2.Builder<Double> derr=derr(1e-10);
    static public C2.Builder<Double> derr(Double err) {
        return byComparison(new DoubleOp(), new DoubleFormat(), Order.<Double>natural(),err);
    }
    static public C2.Builder<Rational> r=new Builder<>(new RationalOp(), new RationalFormat(), Order.natural());
    static public C2.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp(), new BigDecimalFormat(), Order.natural());
    static public C2.Builder<BigDecimal> bdc=byComparison(new BigDecimalOp(), new BigDecimalFormat(), Order.natural());
    static public C2.Builder<BigDecimal> bd3=bd(3,RoundingMode.DOWN);
    static public C2.Builder<BigDecimal> bd3c=bdc(3,RoundingMode.DOWN);
    static public C2.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(bd.body(),scale,r), new BigDecimalFormat(), Order.natural());
    }
    static public C2.Builder<BigDecimal> bdc(int scale, RoundingMode r) {
        return byComparison(new BigDecimalOpRounded(bd.body(),scale,r), new BigDecimalFormat(), Order.natural());
    }
    static public <K> C2.Builder<K> byComparison(Op<K> body, Format<K> format, Order<K> order) {
        return new Builder<>(body,format,order.map(s->s.body()),ContextOrdered::equalsByComparison);
    }
    static public <K> C2.Builder<K> byComparison(Op<K> body, Format<K> format, Order<K> order, K err) {
        return new Builder<>(body,format,order.error(body,err).map(s->s.body()),ContextOrdered::equalsByComparison);
    }
    static public class Builder<K> implements ContextBuilder<K,C2<K>> {
        final Op<K> body;
        final Format<K> format;
        Order<C2<K>> order;
        final BiPredicate<C2<K>,Object> equals;
        Builder(Op<K> body, Format<K> format, Order<C2<K>> order, BiPredicate<C2<K>,Object> equals) {
            this.body=body;
            this.format=format;
            this.order=order;
            this.equals=equals;
        }
        Builder(Op<K> body, Format<K> format, Order<K> order) {
            this(body,format,order.map(s->s.body()),Wrapper::equalsByBody);
        }
        @Override
        public Format<K> format() {
            return format;
        }
        @Override
        public Builder<K> format(Format<K> format) {
            return new Builder<>(body,format,order,equals);
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
            return new Builder<>(body,format,order,equals);
        }
        public Order<C2<K>> order() {
            return order;
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
    public Order<C2<K>> order() {
        return builder.order();
    }
    @Override
    public String toString() {
        return body().toString();
    }
    @Override
    public boolean equals(Object e) {
        return builder.equals.test(this, e);
    }
}
