/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

import collection.ArrayInt;
import collection.TList;

/**
 *
 * @author masao
 * @param <K>
 */
public class PluDecompose<K extends Comparable<K>> extends LuDecompose<K> {
    final ArrayInt order;
    public PluDecompose(CMatrix<K> target) {
        super(target);
        order=ArrayInt.range(0, target.body.size()).fix();
    }
    @Override
    public PLU<K> decompose() {
        return new PLU<>(doolittle().startFrom(p()),order);
    }
    public CMatrix<K> pinv() {
        return target.pinv(order.asList());
    }
    public CMatrix<K> p() {
        return pinv().transpose();
    }
    @Override
    public PluDecompose<K> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).forEach(i->target.swap(i,order).subMatrixUR(i,i).doolittleSubMatrix());
        return this;
    }

}
