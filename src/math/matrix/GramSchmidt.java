/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.CList;
import math.Context;

/**
 *
 * @author masao
 */
public class GramSchmidt {
    public static <K, T extends Context<K,T>> TList<CList<K,T>> orthogonalize(TList<CList<K,T>> bases) {
        return bases.stream().reduce(TList.c(), (es,ak)->es.addOne(extractE(ak,es).sfix()), (es0,es1)->es0.append(es1));
    }
    private static <K, T extends Context<K,T>> CList<K,T> extractE(CList<K,T>ak, TList<CList<K,T>> e) {
        return e.stream().map(ei->ei.scale(ak.dot(ei))).reduce(ak,(a,ai)->a.sub(ai)).transform(x->x.scale(x.dot(x).sqrt().inv()));
    }
}
