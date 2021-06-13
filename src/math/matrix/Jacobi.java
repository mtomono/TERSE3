/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import iterator.TIterator;
import java.util.Iterator;
import math.Context;
import math.ContextBuilder;
import math.ContextOrdered;

/**
 * Calculate eigenvalue of symmetric matrix.
 * @author masao
 */

public class Jacobi<K, T extends Context<K,T>&ContextOrdered<K,T>> {
    /**
     * erase one non-diag element.
     * 
     * here's the calculation
     * 
     * B=tPAP
     * bpq=apq*(cos^2(x)-sin^2(x))+(app-aqq)*sin(x)*cos(x)=apq*cos(2x)+((app-aqq)/2)*sin(2x)=0  (1)
     *      using:  cos(2x)=con^2(x)-sin^2(x)
     *              sin(2x)=2*sin(x)*cos(x)
     * alpha:=(app-aqq)/2
     * beta:=-apq
     * (1) is translated as
     * beta*cos(2x)=alpha*sin(2x)
     * 
     * (alpha^2+beta^2)cos^2(2x)=alpha^2
     *      using:  (alpha*sin(2x))^2+(alpha*cos(2x))~2=0
     * when cos(2x)>=0
     * cos(2x)=sqrt(alpha^2/(alpha^2+beta^2))
     * cos(x)=sqrt((1+cos(2x))/2)
     * sin(x)=sqrt((1-cos(2x))/2)*sign(alpha*beta)
     *      using:  cos(2x)=2*cos^2(x)-1
     *              cos(2x)=1-2*sin^2(x)
     * 
     * @param <K>
     * @param <T>
     * @param target
     * @param erase
     * @return 
     */
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> CMatrix<K,T> eraser(CMatrix<K,T> target, TList<Integer> erase) {
        ContextBuilder<K,T> b=target.bb;
        T app=target.get(erase.get(0),erase.get(0));
        T aqq=target.get(erase.get(1),erase.get(1));
        T apq=target.get(erase);
        T alpha=app.sub(aqq).div(b.b(2));
        T beta=apq.negate();
        T alpha2=alpha.mul(alpha);
        T beta2=beta.mul(beta);
        T cos2=alpha2.div(alpha2.add(beta2)).sqrt();
        T cos=b.one().add(cos2).div(b.b(2)).sqrt();
        T sin=b.one().sub(cos2).div(b.b(2)).sqrt().mul(alpha.sign().mul(beta.sign()));
        return Givens.spin(target, erase, cos, sin);
    }
    
    final public CMatrix<K,T> A;
    final public CMatrix<K,T> P;
    final public TList<Integer> plane;
    public Jacobi(CMatrix<K,T> A, CMatrix<K,T> P) {
        this.A=A;
        this.P=P;
        this.plane=Givens.plane(A);
    }
    public T largestNoDiag() {
        return A.get(plane);
    }
    public Jacobi<K,T> next() {
        CMatrix<K,T> eraser=eraser(A,plane);
        return new Jacobi(eraser.transpose().mul(A).mul(eraser).sfix(),P.mul(eraser));
    }
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> Jacobi<K,T> erase(CMatrix<K,T> target, T threshold) {
        return TIterator.iterate(new Jacobi<>(target.sfix(),target.i().sfix()), j->j.next()).until(j->j.largestNoDiag().lt(threshold)).last();
    }

    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> TList<CMatrix<K,T>> erase_alternative(CMatrix<K,T> target, T threshold) {
        CMatrix<K,T> A=target.sfix();
        CMatrix<K,T> P=target.i().sfix();
        TList<Integer> plane=null;
        do {
            plane=Givens.plane(A);
            CMatrix<K,T> eraser=eraser(A,plane);
            A=eraser.transpose().mul(A).mul(eraser).sfix();
            P=P.mul(eraser);
        } while(A.get(plane).lt(threshold));
        return TList.sof(A,P);
    }
}
