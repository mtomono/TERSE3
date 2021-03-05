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
import java.util.function.BiPredicate;

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
    static public C.Builder<Integer> i=new Builder<>(new IntegerOp(), new IntegerFormat());
    static public C.Builder<Long> l=new Builder<>(new LongOp(), new LongFormat());
    static public C.Builder<Float> f=new Builder<>(new FloatOp(), new FloatFormat());
    static public C.Builder<Double> d=new Builder<>(new DoubleOp(), new DoubleFormat());
    static public C.Builder<Rational> r=new Builder<>(new RationalOp(), new RationalFormat());
    static public C.Builder<BigDecimal> bd=new Builder<>(new BigDecimalOp(), new BigDecimalFormat());
    static public C.Builder<BigDecimal> bd3=bd(3,RoundingMode.DOWN);
    static public C.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return new Builder<>(new BigDecimalOpRounded(bd.body(),scale,r), new BigDecimalFormat());
    }
    
    static public class Builder<K> implements ContextBuilder<K,C<K>>{
        public final Op<K> body;
        public final Format<K> format;
        final BiPredicate<C<K>,Object> equals;
        public Builder(Op<K> op, Format<K> format, BiPredicate<C<K>,Object> equals) {
            this.body=op;
            this.format=format;
            this.equals=equals;
        }
        public Builder(Op<K> op, Format<K> format) {
            this(op,format,Wrapper::equalsByBody);
        }
        @Override
        public Format<K> format() {
            return format;
        }
        @Override
        public Builder<K> format(Format<K> format) {
            return new Builder<>(body,format);
        }
        @Override
        public Op<K> body() {
            return body;
        }
        @Override
        public ContextBuilder<K, C<K>> self() {
            return this;
        }
        @Override
        public ContextBuilder<K, C<K>> wrap(Op<K> body) {
            return new Builder(this.body,format);
        }
        @Override
        public C<K> c(K v) {
            return new C<>(this,v);
        }
    }
    final Builder<K> builder;
    final K v;
    public C(Builder<K> b, K v) {
        this.builder=b;
        this.v=v;
    }
    @Override
    public ContextBuilder<K, C<K>> b() {
        return builder;
    }
    @Override
    public C<K> wrap(K v) {
        return builder.c(v);
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
        return builder.equals.test(this,e);
    }
}
