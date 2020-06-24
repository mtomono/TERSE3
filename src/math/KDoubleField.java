/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import static math.KDouble.ONE;
import static math.KDouble.ZERO;

/**
 *
 * @author masao
 */
public class KDoubleField implements DecimalField<KDouble> {

    @Override
    public KDouble zero() {
        return ZERO;
    }

    @Override
    public KDouble one() {
        return ONE;
    }

    @Override
    public KDouble k(int i) {
        return new KDouble(i);
    }

    @Override
    public KDouble k(long l) {
        return new KDouble(l);
    }
    
}
