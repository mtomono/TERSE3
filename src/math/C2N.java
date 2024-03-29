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
 * Comparable Calculation Context which is made of Number
 * @author masao
 * @param <K>
 */
public class C2N<K extends Number> implements ContextOrdered<K, C2N<K>>,ContextNumber<K,C2N<K>> {
    static public C2N.Builder<Integer> i=new Builder<>(new IntegerOp(), new IntegerFormat(), Order.natural());
    static public C2N.Builder<Long> l=new Builder<>(new LongOp(), new LongFormat(), Order.natural());
    static public C2N.Builder<Float> f=new Builder<>(new FloatOp(), new FloatFormat(), Order.natural());
    static public C2N.Builder<Double> d=new Builder<>(new DoubleOp(), new DoubleFormat(), Order.natural());
    static public C2N.Builder<Double> dc=byComparison(new DoubleOp(), new DoubleFormat(), Order.natural());
    static public C2N.Builder<Double> derr=derr(1e-10);
    static public C2N.Builder<Double> derr(double err) {
        return byComparison(new DoubleOp(), new DoubleFormat(), Order.natural(), err);
    }
    static public C2N.Builder<Rational> r=new Builder<>(new RationalOp(), new RationalFormat(), Order.natural());
    static public C2N.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp(), new BigDecimalFormat(), Order.natural());
    static public C2N.Builder<BigDecimal> bdc=byComparison(new BigDecimalOp(), new BigDecimalFormat(), Order.natural());
    static public C2N.Builder<BigDecimal> bd3=bd(3,RoundingMode.DOWN);
    static public C2N.Builder<BigDecimal> bd3c=bdc(3,RoundingMode.DOWN);
    static public C2N.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(bd.body(),scale,r), new BigDecimalFormat(), Order.natural());
    }
    static public C2N.Builder<BigDecimal> bdc(int scale, RoundingMode r) {
        return byComparison(new BigDecimalOpRounded(bd.body(),scale,r), new BigDecimalFormat(), Order.natural());
    }
    static public <K extends Number> C2N.Builder<K> byComparison(Op<K> body, Format<K> format, Order<K> order) {
        return new Builder<>(body,format,order.map(s->s.body()),ContextOrdered::equalsByComparison);
    }
    static public <K extends Number> C2N.Builder<K> byComparison(Op<K> body, Format<K> format, Order<K> order, K err) {
        return new Builder<>(body,format,order.error(body,err).map(s->s.body()),ContextOrdered::equalsByComparison);
    }
    static public class Builder<K extends Number> implements ContextBuilder<K,C2N<K>> {
        final Op<K> body;
        final Format<K> format;
        final Order<C2N<K>> order;
        final BiPredicate<C2N<K>,Object> equals;
        Builder(Op<K> body, Format<K> format, Order<C2N<K>> order, BiPredicate<C2N<K>,Object> equals) {
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
            return new Builder<>(body,format,order,equals);
        }
        public Order<C2N<K>> order() {
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
        return builder.order();
    }
    @Override
    public String toString() {
        return body().toString();
    }
    @Override
    public boolean equals(Object e) {
        return builder.equals.test(this,e);
    }
}
