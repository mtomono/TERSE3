/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.CList;
import math.Context;
import math.ContextBuilder;
import math.ContextOrdered;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class Householder<K, T extends Context<K,T>&ContextOrdered<K,T>> {
    final public ContextBuilder<K,T> builder;
    final public CList<K,T> x;
    final public CList<K,T> y;
    public Householder(ContextBuilder<K,T> builder,CList<K,T> x, CList<K,T> y) {
        assert(x.dot(x).eq(y.dot(y)));
        this.builder=builder;
        this.x=x;
        this.y=y;
    }
    public CList<K,T> u() {
        return x.sub(y);
    }
    public CMatrix<K,T> H() {
        CList<K,T> u=u();
        CMatrix<K,T> U=CMatrix.b(builder).b(TList.sof(u().body()));
        CMatrix<K,T> tUU=U.transpose().mul(U);
        return tUU.i().sub(tUU.scale(u.dot(u).inv().mul(builder.b(2))));
    }
    public CMatrix<K,T> Q(CMatrix<K,T> target) {
        CList<K,T> x=target.columns().get(0);
        CList<K,T> y=x.zero().m(l->l.replaceAt(0, x.dot(x).sqrt()));
        return new Householder(builder,x,y).H();
    }
}
