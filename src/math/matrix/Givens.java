/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import static java.lang.Math.cos;
import java.util.Comparator;
import math.CList;
import math.Context;
import math.ContextOrdered;

/**
 *
 * @author masao
 */
public class Givens {
    /**
     * Givens Rotation Matrix.
     * @param <K>
     * @param <T>
     * @param target target matrix.
     * @param plane plane which erase belongs to.
     * @return Givens Rotation Matrix
     */
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> spin(CMatrix<K,T> target, TList<Integer> plane, T cos, T sin) {
        assert plane.size()==2 : "plane has to have 2 axis";
        assert target.contains(plane) : "plane has to be a cell in target";
        assert !plane.isUniform() : "plane has to have 2 different axis";
        return target.i().sfix()
                .set(plane.get(0),plane.get(0),cos)
                .set(plane.get(1),plane.get(1),cos)
                .set(plane.get(0),plane.get(1),sin.negate())
                .set(plane.get(1),plane.get(0),sin);
    }
    
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> erase(CMatrix<K,T> target, TList<Integer> plane, TList<Integer> erase) {
        assert erase.size()==2 : "erace cell has to be positioned by row and column";
        assert target.contains(erase) : "erase has to be a cell in target";
        assert erase.get(0)>erase.get(1) : "erase is not supposed to be a part of upper triangle";
        assert plane.contains(erase.get(0)) : "erase row has to be an axis of plane";
        CList<K,T> spin=plane.directToC(v->target.get(v,erase.get(1)),target.bb).sfix();
        T r=spin.dot(spin).sqrt();
        T cos=spin.get(1).div(r);
        T sin=spin.get(0).div(r);
        return spin(target,plane,cos,sin);
    }
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> TList<Integer> plane(CMatrix<K,T> target) {
        if (target.x*target.y<=1)
            return TList.empty();
        return target.lowerNoDiag().max(xy->target.get(xy).abs()).get();
    }
}
