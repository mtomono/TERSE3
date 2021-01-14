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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Predicate;

/**
 * Calculation Context.
 * this class intends to allow fluent calculation with number classes like BigDecimal or 
 * Rational.
 * @author masao
 * @param <K>
 */
public class C<K> {
    static public C.Builder<Integer> i=C.b(new IntegerOp());
    static public C.Builder<Long> l=C.b(new LongOp());
    static public C.Builder<Float> f=C.b(new FloatOp());
    static public C.Builder<Double> d=C.b(new DoubleOp());
    static public C.Builder<Rational> r=C.b(new RationalOp());
    static public C.Builder<BigDecimal> bd=C.b(new BigDecimalOp());
    static public C.Builder<BigDecimal> bd(int scale, RoundingMode r) {
        return C.b(new BigDecimalOpRounded(scale,r));
    }
    public static <K> Builder<K> b(Op<K> op) {
        return new Builder<>(op);
    }
    static public class Builder<K> {
        public final Op<K> op;
        public Builder(Op<K> op) {
            this.op=op;
        }
        
        public C<K> b(K v) {
            return new C<>(this,v);
        }
        public C<K> b(int v) {
            return b(op.b(v));
        }
        public C<K> b(long v) {
            return b(op.b(v));
        }
        public C<K> b(float v) {
            return b(op.b(v));
        }
        public C<K> b(double v) {
            return b(op.b(v));
        }
        public C<K> b(String v) {
            return b(op.b(v));
        }
        public C<K> b(BigDecimal n) {
            return b(op.b(n));
        }
        public C<K> b(Rational n) {
            return b(op.b(n));
        }
        public C<K> one() {
            return b(op.one());
        }
        public C<K> zero() {
            return b(op.zero());
        }
    }
    
    public final Builder<K> b;
    public final K v;

    public C(Builder<K> b, K v) {
        this.b=b;
        this.v=v;
    }
    public C<K> v(K v) {
        return b.b(v);
    }
    public K get() {
        return v;
    }
    public C<K> add(C<K> v) {
        return v.v(b.op.add(this.v, v.v));
    }
    public C<K> sub(C<K> v) {
        return v.v(b.op.sub(this.v, v.v));
    }
    public C<K> mul(C<K> v) {
        return v.v(b.op.mul(this.v, v.v));
    }
    public C<K> div(C<K> v) {
        return v.v(b.op.div(this.v,v.v));
    }
    public C<K> inv() {
        return one().div(this);
    }
    public C<K> negate() {
        return v(b.op.negate(v));
    }
    public C<K> abs() {
        return v(b.op.abs(v));
    }
    public C<K> one() {
        return v(b.op.one());
    }
    public C<K> zero() {
        return v(b.op.zero());
    }
    @Override
    public String toString() {
        return v.toString();
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
        return v.equals(t.v);
    }
}
