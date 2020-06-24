/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import static math.KBigDecimal.ONE;
import static math.KBigDecimal.ZERO;
import static math.KBigDecimal.b;

/**
 *
 * @author masao
 */
public class KBigDecimalField implements DecimalField<KBigDecimal> {

    @Override
    public KBigDecimal zero() {
        return ZERO;
    }

    @Override
    public KBigDecimal one() {
        return ONE;
    }

    @Override
    public KBigDecimal k(int i) {
        return b(i);
    }

    @Override
    public KBigDecimal k(long l) {
        return b(l);
    }
    
}
