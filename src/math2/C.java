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

/**
 * Calculation Context.
 * this class intends to allow fluent calculation with number classes like BigDecimal or 
 * Rational.
 * @author masao
 * @param <K>
 */
public class C<K extends Number> {
    public static <K extends Number> Builder<K> b(Op<K> op) {
        return new Builder<>(op);
    }
    static public class Builder<K extends Number> {
        public final Op<K> op;
        public Builder(Op<K> op) {
            this.op=op;
        }
        public C<K> b(K v) {
            return new C<>(op,v);
        }
        public C<K> b(int v) {
            return new C<>(op,op.b(v));
        }
        public C<K> b(long v) {
            return new C<>(op,op.b(v));
        }
        public C<K> b(float v) {
            return new C<>(op,op.b(v));
        }
        public C<K> b(double v) {
            return new C<>(op,op.b(v));
        }
        public C<K> b(String v) {
            return new C<>(op,op.b(v));
        }
        public C<K> one() {
            return new C<>(op,op.one());
        }
        public C<K> zero() {
            return new C<>(op,op.zero());
        }
    }
    
    public final Op<K> op;
    public final K v;

    public C(Op<K> op, K v) {
        this.op=op;
        this.v=v;
    }
    public C<K> v(K v) {
        return new C<>(op,v);
    }
    public K get() {
        return v;
    }
    public C<K> add(C<K> v) {
        return v.v(op.add(this.v, v.v));
    }
    public C<K> sub(C<K> v) {
        return v.v(op.sub(this.v, v.v));
    }
    public C<K> mul(C<K> v) {
        return v.v(op.mul(this.v, v.v));
    }
    public C<K> div(C<K> v) {
        return v.v(op.div(this.v,v.v));
    }
    public C<K> negate() {
        return v(op.negate(v));
    }
    public C<K> abs() {
        return v(op.abs(v));
    }
    public C<K> one() {
        return v(op.one());
    }
    public C<K> zero() {
        return v(op.zero());
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
