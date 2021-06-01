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
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>>CMatrix<K,T> mirror(ContextBuilder<K,T> builder, CList<K,T> x, CList<K,T> y) {
        CList<K,T> u=x.sub(y);
        CMatrix<K,T> U=CMatrix.b(builder).b(TList.sof(u.body()));
        CMatrix<K,T> tUU=U.transpose().mul(U);
        return tUU.i().sub(tUU.scale(u.dot(u).inv().mul(builder.b(2))));
    }
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> localQ(CMatrix<K,T> target) {
        CList<K,T> x=target.columns().get(0).sfix();
        CList<K,T> y=x.zero().m(l->l.replaceAt(0, x.dot(x).sqrt())).sfix();
        return mirror(target.bb,x,y);
    }
}
