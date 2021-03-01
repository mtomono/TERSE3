/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author masao
 */
public class BigDecimalFormat implements Format<BigDecimal> {
    final DecimalFormat decimalFormat;
    public BigDecimalFormat() {
        decimalFormat=new DecimalFormat();
        decimalFormat.setParseBigDecimal(true);
    }
    @Override
    public BigDecimal f(String v) throws ParseException {
        return (BigDecimal)decimalFormat.parse(v);
    }
    
    @Override
    public String toString(BigDecimal v) {
        return decimalFormat.format(v);
    }

}
