/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import static math.KBigDecimal.b;
import static math.KRational.one;
import static math.KRational.zero;

/**
 *
 * @author masao
 */
public class KRationalBuilder implements DecimalBuilder<KRational> {

    @Override
    public KRational zero() {
        return zero;
    }

    @Override
    public KRational one() {
        return one;
    }

    @Override
    public KRational k(int i) {
        return new KRational(i,1);
    }

    @Override
    public KRational k(long l) {
        return new KRational(l,1);
    }
    
}
