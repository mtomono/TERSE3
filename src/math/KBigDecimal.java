/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import debug.Te;
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
    static public KBigDecimal ZERO=new KBigDecimal(BigDecimal.ZERO);
    static public KBigDecimal ONE=new KBigDecimal(BigDecimal.ONE);
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
    public KBigDecimal div(KBigDecimal value) {
        return div(value.v);
    }
    public KBigDecimal overrideDiv(BinaryOperator<KBigDecimal> op) {
        return new KBigDecimal(v) {
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
    public KBigDecimal zero() {
        return ZERO;
    }
    public KBigDecimal one() {
        return ONE;
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
