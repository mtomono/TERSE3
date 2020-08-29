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
import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_EVEN;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.function.BinaryOperator;
import string.Strings;

/**
 *
 * @author masao
 */
public class KBigDecimal implements Decimal<KBigDecimal> { 
    final static public KBigDecimal ZERO=new KBigDecimal(BigDecimal.ZERO);
    final static public KBigDecimal ONE=new KBigDecimal(BigDecimal.ONE);
    final static public KBigDecimalField field=new KBigDecimalField();
    static public BigDecimal c(double value) {
        return new BigDecimal(value);
    }
    static public BigDecimal c(double value, int scale, RoundingMode r) {
        return new BigDecimal(value).setScale(scale,r);
    }
    static public BigDecimal c(double value, int scale) {
        return new BigDecimal(value).setScale(scale,HALF_EVEN);
    }
    static public BigDecimal c(String value) {
        return new BigDecimal(value);
    }
    static public KBigDecimal b(BigDecimal value) {
        return new KBigDecimal(value);
    }
    static public KBigDecimal b(KBigDecimal value) {
        return b(value.v);
    }
    static public KBigDecimal b(double value) {
        return b(c(value));
    }
    static public KBigDecimal b(String value) {
        return b(c(value));
    }
    final public BigDecimal v;
    public KBigDecimal(BigDecimal body) {
        this.v=body;
    }
    public KBigDecimal create(BigDecimal v) {
        return new KBigDecimal(v);
    }
    
    public BigDecimal value() {
        return v;
    }
    
    public KBigDecimal add(BigDecimal value) {
        return create(v.add(value));
    }
    @Override
   public KBigDecimal add(KBigDecimal value) {
        return add(value.value());
    }
    public KBigDecimal add(int v) {
        return add(c(v));
    }
    public KBigDecimal add(long v) {
        return add(c(v));
    }
    public KBigDecimal add(String v) {
        return add(c(v));
    }
    public KBigDecimal sub(BigDecimal value) {
        return create(v.subtract(value));
    }
    @Override
    public KBigDecimal sub(KBigDecimal value) {
        return sub(value.v);
    }
    public KBigDecimal sub(int v) {
        return sub(c(v));
    }
    public KBigDecimal sub(long v) {
        return sub(c(v));
    }
    public KBigDecimal sub(String v) {
        return sub(c(v));
    }
    public KBigDecimal mul(BigDecimal value) {
        return create(v.multiply(value));
    }
    @Override
    public KBigDecimal mul(KBigDecimal value) {
        return mul(value.v);
    }
    @Override
    public KBigDecimal mul(int v) {
        return mul(c(v));
    }
    @Override
    public KBigDecimal mul(long v) {
        return mul(c(v));
    }
    public KBigDecimal mul(String v) {
        return mul(c(v));
    }
    public KBigDecimal div(BigDecimal value) {
        return create(v.divide(value));
    }
    public KBigDecimal div(BigDecimal value, int scale, RoundingMode r) {
        return new KBigDecimal(v.divide(value, scale, r));
    }
    @Override
    public KBigDecimal div(KBigDecimal value) {
        return div(value.v);
    }
    public KBigDecimal overrideDiv(BinaryOperator<KBigDecimal> op) {
        return new KBigDecimal(v) {
            @Override
            public KBigDecimal div(KBigDecimal v) {
                return op.apply(this,v);
            }
        };
    }
    public KBigDecimal scaleDiv(int scale, RoundingMode r) {
        return overrideDiv((t,o)->t.div(o.v,scale,r));
    }
    @Override
    public KBigDecimal div(int v) {
        return div(c(v));
    }
    @Override
    public KBigDecimal div(long v) {
        return div(c(v));
    }
    public KBigDecimal div(String v) {
        return div(c(v));
    }
    @Override
    public KBigDecimal abs() {
        return create(v.abs());
    }
    @Override
    public KBigDecimal negate() {
        return create(v.negate());
    }

    public KBigDecimal percent() {
        return div(c(100));
    }
    public KBigDecimal setScale(int scale) {
        return create(v.setScale(scale));
    }
    @Override
    public KBigDecimal zero() {
        return ZERO;
    }
    @Override
    public KBigDecimal one() {
        return ONE;
    }
    @Override
    public boolean isInteger() {
        return c(v.longValue()).equals(v);
    }
    @Override
    public int intPart() {
        return v.intValue();
    }
    @Override
    public long longPart() {
        return v.longValue();
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KBigDecimal)) {
            return false;
        }
        KBigDecimal t = (KBigDecimal) e;
        return t.v.equals(v);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.v);
        return hash;
    }

    public String format() {
        String zero = Strings.n('0', v.scale());
        String dot = v.scale()>0?".":"";
        return new DecimalFormat("#,###"+dot+zero+";-#,###"+dot+zero).format(v);
    }
    
    @Override
    public String toString() {
        return v.toString();
    }

    @Override
    public int compareTo(KBigDecimal o) {
        return v.compareTo(o.v);
    }
}
