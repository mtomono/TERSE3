/*
 Copyright 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package math.matrix;

import collection.TList;
import iterator.TIterator;
import java.util.Optional;
import math.Context;
import math.ContextBuilder;
import math.ContextOrdered;

/**
 * Calculate eigenvalue of symmetric matrix.
 * @author masao
 * @param <K>
 * @param <T>
 */

public class JacobiGivens<K, T extends Context<K,T>&ContextOrdered<K,T>> {
    /**
     * erase one non-diag element.
     * 
     * here's the calculation
     * P : Givens Matrix
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
        T app=target.get(erase.get(1),erase.get(1));
        T aqq=target.get(erase.get(0),erase.get(0));
        T apq=target.get(erase);
        T alpha=app.sub(aqq).div(b.b(2));
        T beta=apq.negate();
        T alpha2=alpha.mul(alpha);
        T beta2=beta.mul(beta);
        T cos2=alpha2.div(alpha2.add(beta2)).sqrt();
        T cos=b.one().add(cos2).div(b.b(2)).sqrt();
        T sin=b.one().sub(cos2).div(b.b(2)).sqrt().mul(Optional.of(alpha.sign()).map(x->x.isZero()?alpha.one():x).get()).mul(beta.sign());
        return Givens.spin(target, erase, cos, sin);
    }
    
    final public CMatrix<K,T> A;
    final public CMatrix<K,T> P;
    final public TList<Integer> plane;
    public JacobiGivens(CMatrix<K,T> A, CMatrix<K,T> P) {
        this.A=A;
        this.P=P;
        this.plane=Givens.plane(A);
    }
    /**
     * largest non-diagonal element.
     * @return 
     */
    public T largestNonDiag() {
        return A.get(plane);
    }

    public JacobiGivens<K,T> next() {
        return Optional.of(eraser(A,plane)).map(eraser->new JacobiGivens(eraser.transpose().mul(A).mul(eraser).sfix(),P.mul(eraser))).get();
    }
    
    /**
     * erase non-diagonal elements.
     * the object works as a context of loop.
     * plane is required in 2 phases in one lap of loop.
     * calculation of eraser and judging end of loop.
     * 
     * if i allow largestNoDiag() to be executed twice, whole the loop can be made
     * without a Jacobi object.
     * @param <K>
     * @param <T>
     * @param target
     * @param threshold
     * @return 
     */
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> JacobiGivens<K,T> erase(CMatrix<K,T> target, T threshold) {
        assert target.isSymmetric();
        return TIterator.iterate(new JacobiGivens<>(target.sfix(),target.i().sfix()), j->j.next()).until(j->j.largestNonDiag().lt(threshold)).last();
    }
    
    @Override
    public String toString() {
        return A.toString()+P.toString()+plane.toString();
    }

}
