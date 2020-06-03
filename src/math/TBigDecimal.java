/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_EVEN;

/**
 *
 * @author masao
 */
public class TBigDecimal implements Decimal<TBigDecimal> { 
    static public TBigDecimal ZERO=new TBigDecimal(BigDecimal.ZERO);
    static public TBigDecimal ONE=new TBigDecimal(BigDecimal.ONE);
    static public BigDecimal c(int value) {
        return new BigDecimal(value);
    }
    static public BigDecimal c(long value) {
        return new BigDecimal(value);
    }
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
    static public TBigDecimal b(BigDecimal value) {
        return new TBigDecimal(value);
    }
    static public TBigDecimal b(TBigDecimal value) {
        return b(value.v);
    }
    static public TBigDecimal b(int value) {
        return b(c(value));
    }
    static public TBigDecimal b(long value) {
        return b(c(value));
    }
    static public TBigDecimal b(String value) {
        return b(c(value));
    }
    final public BigDecimal v;
    public TBigDecimal(BigDecimal body) {
        this.v=body;
    }
    public TBigDecimal add(BigDecimal value) {
        return new TBigDecimal(v.add(value));
    }
   public TBigDecimal add(TBigDecimal value) {
        return add(value.v);
    }
    public TBigDecimal add(int v) {
        return add(c(v));
    }
    public TBigDecimal add(long v) {
        return add(c(v));
    }
    public TBigDecimal add(String v) {
        return add(c(v));
    }
    public TBigDecimal sub(BigDecimal value) {
        return new TBigDecimal(v.subtract(value));
    }
    public TBigDecimal sub(TBigDecimal value) {
        return sub(value.v);
    }
    public TBigDecimal sub(int v) {
        return sub(c(v));
    }
    public TBigDecimal sub(long v) {
        return sub(c(v));
    }
    public TBigDecimal sub(String v) {
        return sub(c(v));
    }
    public TBigDecimal mul(BigDecimal value) {
        return new TBigDecimal(v.multiply(value));
    }
    public TBigDecimal mul(TBigDecimal value) {
        return mul(value.v);
    }
    @Override
    public TBigDecimal mul(int v) {
        return mul(c(v));
    }
    @Override
    public TBigDecimal mul(long v) {
        return mul(c(v));
    }
    public TBigDecimal mul(String v) {
        return mul(c(v));
    }
    public TBigDecimal div(BigDecimal value) {
        return new TBigDecimal(v.divide(value));
    }
    public TBigDecimal div(BigDecimal value, int scale, RoundingMode r) {
        return new TBigDecimal(v.divide(value, scale, r));
    }
     public TBigDecimal div(TBigDecimal value) {
        return div(value.v);
    }
    @Override
    public TBigDecimal div(int v) {
        return div(c(v));
    }
    @Override
    public TBigDecimal div(long v) {
        return div(c(v));
    }
    public TBigDecimal div(String v) {
        return div(c(v));
    }
    public TBigDecimal abs() {
        return new TBigDecimal(v.abs());
    }
    public TBigDecimal percent() {
        return div(c(100));
    }
    public TBigDecimal zero() {
        return ZERO;
    }
    public TBigDecimal one() {
        return ONE;
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof TBigDecimal)) {
            return false;
        }
        TBigDecimal t = (TBigDecimal) e;
        return t.v.equals(v);
    }
    @Override
    public String toString() {
        return v.toString();
    }

    @Override
    public TBigDecimal negate() {
        return new TBigDecimal(v.negate());
    }

    @Override
    public int compareTo(TBigDecimal o) {
        return v.compareTo(o.v);
    }
}
