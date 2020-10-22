/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.ArrayInt;
import java.util.List;

/**
 *
 * @author masao
 * @param <K>
 */
public class PLU<K extends Decimal<K>> extends LU<K> {
    ArrayInt order;
    public PLU(List<KMatrix<K>> body, ArrayInt order) {
        super(body);
        this.order=order;
    }
    public KVector<K> porder(KVector<K> v) {
        return l().context.vector(v.body.pickUp(order.asList()));
    } 
    public KMatrix<K> p() {
        return get(0);
    }
    public KMatrix<K> pinv() {
        return p().transpose();
    }
    /**
     * solve with partial pivot.
     * a partial pivot means that it's only changing the order of formulae.
     * so the order of variable xi is not influenced.
     * @param v
     * @return 
     */
    public KVector<K> solve(KVector<K> v) {
        return super.solve(porder(v));
    }
    public KMatrix<K> inv() {
        return super.inv().mul(pinv());
    }
}
