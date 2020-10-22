/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.ArrayInt;
import collection.TList;
import debug.Te;
import static java.lang.Integer.min;

/**
 *
 * @author masao
 * @param <K>
 */
public class PluDecompose<K extends Decimal<K>> extends LuDecompose<K> {
    final ArrayInt order;
    public PluDecompose(KMatrix<K> target) {
        super(target);
        order=ArrayInt.range(0, target.body.size()).fix();
    }
    @Override
    public PLU<K> decompose() {
        return new PLU<>(doolittle().startFrom(p()),order);
    }
    public KMatrix<K> pinv() {
        return target.pinv(order.asList());
    }
    public KMatrix<K> p() {
        return pinv().transpose();
    }
    @Override
    public PluDecompose<K> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).forEach(i->target.swap(i,order).subMatrixUR(i,i).doolittleSubMatrix());
        return this;
    }

}
