/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import static java.lang.Integer.min;

/**
 *
 * @author masao
 * @param <K>
 */
public class LuDecompose<K extends Decimal<K>> {
    final KMatrix<K> target;
    public LuDecompose(KMatrix<K> target) {
        this.target=target.sfix();
    }

    public LU<K> decompose() {
        LU<K> retval=new LU<>(doolittle());
        return retval;
    }
    public TList<KMatrix<K>> doolittle() {
        target.assertSquare();
        doolittleWholeMatrix();
        if (!target.nonZeroDiagonal())
            throw new NonsingularMatrixException("non zero element found in diagonal of doolittle result : "+target+" : noticed by LuDecompose.lu()");
        return target.doolittleFormat();
    }
    public LuDecompose<K> doolittleWholeMatrix() {
        TList.range(0,target.minSize()-1).forEach(i->target.subMatrixUR(i,i).doolittleSubMatrix());
        return this;
    }
}