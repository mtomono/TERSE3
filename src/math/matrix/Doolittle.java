/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.ArrayInt;
import collection.TList;
import math.CList;
import math.Context;
import math.ContextBuilder;
import math.ContextOrdered;
import math.NonsingularMatrixException;
import math.PivotingMightBeRequiredException;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class Doolittle<K,T extends Context<K,T>&ContextOrdered<K,T>> extends CMatrix<K,T> {
    CMatrix<K,T> wrapped;
    public Doolittle(ContextBuilder<K,T> bb, TList<TList<T>> body) {
        super(bb,body);
    }
    public Doolittle(CMatrix<K,T> body) {
        this(body.bb, body.body);
    }
    
    @Override
    public Doolittle<K,T> wrap(TList<TList<T>> body) {
        return new Doolittle<>(bb,body);
    }
    @Override
    public Doolittle<K,T> subMatrixLR(int x0, int y0) {
        return (Doolittle<K,T>)super.subMatrixLR(x0, y0);
    }
    public Doolittle<K,T> swap(int c, ArrayInt order) {
        if (body.size()<=1)
            return this;
        int maxRow=TList.range(c,body.size()).max(i->get(i,c).abs()).get();
        body.swap(c,maxRow);
        if (get(c,c).isZero())
            throw new NonsingularMatrixException("diagonal element was 0 even after pivoting, meaning this matrix is not singular : notified from CMatrix.swap()");
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        order.swap(c,maxRow);
        return this;
    }
    public Doolittle<K,T> doolittleSubMatrix() {
        if (get(0,0).isZero())
            throw new PivotingMightBeRequiredException("lu decomposition encountered 0 diagonal element:"+ toString() +": notified from CMatrix.doolittleStep");
        CList<K,T>        lcolumn    =subMatrix(1,0, x,1).columns().get(0).reset(cl->cl.scale(body.get(0).get(0).inv()));
        CList<K,T>        eliminator =subMatrix(0,1, 1,y).rows().get(0);
        TList<CList<K,T>> eliminated =subMatrix(1,1,x,y).rows();
        lcolumn.body().pair(eliminated,(l,u)->u.reset(cl->cl.sub(eliminator.scale(l)))).forEach(r->{}); 
        return this;
    }
}
