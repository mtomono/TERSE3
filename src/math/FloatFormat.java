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
public class FloatFormat implements Format<Float> {
    @Override
    public Float f(String v) throws ParseException {
        return NumberFormat.getInstance().parse(v).floatValue();
    }
    
    @Override
    public String toString(Float v) {
        return NumberFormat.getInstance().format(v);
    }
}
