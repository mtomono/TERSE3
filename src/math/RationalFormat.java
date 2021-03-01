/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.text.NumberFormat;
import java.text.ParseException;
import static math.LongFormat.longValue;

/**
 *
 * @author masao
 */
public class RationalFormat implements Format<Rational> {
    @Override
    public Rational f(String v) throws ParseException {
        String[] n=v.split("/");
        return n.length==1?
                new Rational(longValue(v),1):
                new Rational(longValue(n[0]),longValue(n[1]));
    }

    @Override
    public String toString(Rational v) {
        return NumberFormat.getInstance().format(v.numerator)+"/"+NumberFormat.getInstance().format(v.denominator);
    }
}
