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
public class LongFormat implements Format<Long> {
    static long longValue(String s) throws ParseException {
        return NumberFormat.getInstance().parse(s).longValue();
    }
    @Override
    public Long f(String v) throws ParseException {
        return longValue(v);
    }

    @Override
    public String toString(Long v) {
        return NumberFormat.getInstance().format(v);
    }
}
