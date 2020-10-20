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
public class KPivotMatrix<K extends Decimal<K>> extends KMatrix<K> {
    ArrayInt order;
    public KPivotMatrix(TList<TList<K>> body, ArrayInt order, MathContext<K> context) {
        super(body, context);
        this.order=order;
    }
    public KPivotMatrix(TList<TList<K>> body, MathContext<K> context) {
        this(body, ArrayInt.range(0, body.size()).fix(), context);
    }
    public KMatrix<K> sfix() {
        return new KPivotMatrix<>(body.map(r->r.sfix()).sfix(),order,context);
    }
    public KVector<K> porder(KVector<K> v) {
        return context.vector(v.body.pickUp(order.asList()));
    } 
    public KMatrix<K> p() {
        return context.matrix(body.index().map(i->TList.nCopies(body.size(), context.zero()).sfix().cset(order.get(i), context.one())));
    }
    public KMatrix<K> pinv() {
        return p().transpose();
    }
    public class LUP extends KMatrix<K>.LU{
        public LUP(TList<KMatrix<K>> lu) {
            super(lu);
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
    }
    public LUP lu() {
        return new LUP(luMatrices());
    }
    public KMatrix<K> swap(int c) {
        if (body.size()<=1)
            return this;
        int maxRow=body.index().max(i->body.get(i).get(c)).get();
        body.swap(c,maxRow);
        order=order.swap(c, maxRow);
        return this;
    }
    @Override
    public KMatrix<K> doolittle() {
        TList.range(0,min(x,y)-1).forEach(i->swap(i).subMatrix(i,i,x,y).doolittleStep());
        return this;
    }
    
}
