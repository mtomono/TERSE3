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
public class TBigDecimal{ 
    static public BigDecimal c(int value) {
        return new BigDecimal(value);
    }
    static public BigDecimal c(long value) {
        return new BigDecimal(value);
    }
    static public BigDecimal dc(double value) {
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
    final public BigDecimal v;
    public TBigDecimal(BigDecimal body) {
        this.v=body;
    }
    public TBigDecimal add(BigDecimal value) {
        return new TBigDecimal(v.add(value));
    }
    public TBigDecimal sub(BigDecimal value) {
        return new TBigDecimal(v.subtract(value));
    }
    public TBigDecimal mul(BigDecimal value) {
        return new TBigDecimal(v.multiply(value));
    }
    public TBigDecimal div(BigDecimal value) {
        return new TBigDecimal(v.divide(value));
    }
    public TBigDecimal div(BigDecimal value, int scale, RoundingMode r) {
        return new TBigDecimal(v.divide(value, scale, r));
    }
    public TBigDecimal abs() {
        return new TBigDecimal(v.abs());
    }
    public TBigDecimal percent() {
        return div(c(100));
    }
    @Override
    public String toString() {
        return v.toString();
    }
}
