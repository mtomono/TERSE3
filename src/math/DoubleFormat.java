/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author masao
 */
public class DoubleFormat implements Format<Double> {
    @Override
    public Double f(String v) throws ParseException {
        return NumberFormat.getInstance().parse(v).doubleValue();
    }
    
    @Override
    public String toString(Double v) {
        return NumberFormat.getInstance().format(v);
    }
}
