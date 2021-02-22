/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

import collection.ArrayInt;
import java.util.List;

/**
 *
 * @author masao
 * @param <K>
 */
public class PLU<K extends Comparable<K>> extends LU<K> {
    ArrayInt order;
    public PLU(List<CMatrix<K>> body, ArrayInt order) {
        super(body);
        this.order=order;
    }
    public CList<K> porder(CList<K> v) {
        return v.wrap(v.body.pickUp(order.asList()));
    } 
    public CMatrix<K> p() {
        return get(0);
    }
    public CMatrix<K> pinv() {
        return p().transpose();
    }
    /**
     * solve with partial pivot.
     * a partial pivot means that it's only changing the order of formulae.
     * so the order of variable xi is not influenced.
     * @param v
     * @return 
     */
    public CList<K> solve(CList<K> v) {
        return super.solve(porder(v));
    }
    public CMatrix<K> inv() {
        return super.inv().mul(pinv());
    }
}
