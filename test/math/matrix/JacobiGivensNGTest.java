/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import static java.lang.Math.sqrt;
import math.C2;
import math.Context;
import math.ContextOrdered;
import static math.matrix.JacobiGivens.eraser;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import string.TString;

/**
 *
 * @author masao
 */
public class JacobiGivensNGTest {
    
    public JacobiGivensNGTest() {
    }
    
    CMatrix<Double,C2<Double>> pap(CMatrix<Double,C2<Double>> p, CMatrix<Double,C2<Double>> a) {
        return p.transpose().mul(a).mul(p);
    }
    
    @Test
    public void testEraser1221() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2;
                        2,1;
                             """);
        TList<Integer> plane=Givens.plane(target);
        var result=JacobiGivens.eraser(target,plane);
        var expected = CMatrix.derr.b(new TString("""
                          #{r2}, -#{r2};
                          #{r2}, #{r2}
                           """).let("#{r2}", sqrt(2)/2).body());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testErase1221() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2;
                        2,1;
                             """);
        var result=pap(JacobiGivens.erase(target,C2.derr.b(1e-7)).P,target);
        var expected = CMatrix.derr.b("""
                        3,0;
                        0,-1;
                             """);;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEraser1223() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2;
                        2,3;
                             """);
        var result=pap(JacobiGivens.erase(target,C2.derr.b(1e-7)).P,target);
        var expected = CMatrix.derr.b("""
                                      -0.23606797749979,0;
                                      0,4.2360679774998;
                                      """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testErase3x3() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2,-1;
                        2,-2,2;
                        -1,2,1;
                             """);
        var result=pap(JacobiGivens.erase(target,C2.derr.b(1e-10)).P,target);
        var expected = CMatrix.derr.b("""
                                      2,0,0;
                                      0,-4,0;
                                      0,0,2;
                                      """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    /**
     * erase alternative.
     * erase_flat() is shortest in terms of LOC. but it is not showing its structure clearly.
     * using JacobiGivens object, erase_with_object() exhibits it clearer.
     * after all, erase_with_object() itself is so simple that it can be written in 1 line.
     * that is the above method, erase().
     * @param <K>
     * @param <T>
     * @param target
     * @param threshold
     * @return 
     */
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> JacobiGivens<K,T> erase_with_object(CMatrix<K,T> target, T threshold) {
        JacobiGivens<K,T> jg=new JacobiGivens<>(target.sfix(),target.i().sfix());
        do
            jg=jg.next();
        while(jg.largestNonDiag().ge(threshold));
        return jg;
    }

    @Test
    public void testErase3x3WithObject() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2,-1;
                        2,-2,2;
                        -1,2,1;
                             """);
        var result=pap(erase_with_object(target,C2.derr.b(1e-10)).P,target);
        var expected = CMatrix.derr.b("""
                                      2,0,0;
                                      0,-4,0;
                                      0,0,2;
                                      """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>> TList<CMatrix<K,T>> erase_flat(CMatrix<K,T> target, T threshold) {
        CMatrix<K,T> A=target.sfix();
        CMatrix<K,T> P=target.i().sfix();
        TList<Integer> plane=Givens.plane(A);
        do {
            CMatrix<K,T> eraser=eraser(A,plane);
            A=eraser.transpose().mul(A).mul(eraser).sfix();
            P=P.mul(eraser);
            plane=Givens.plane(A);
        } while(A.get(plane).ge(threshold));
        return TList.sof(A,P);
    }
    @Test
    public void testErase3x3Flat() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2,-1;
                        2,-2,2;
                        -1,2,1;
                             """);
        var result=pap(erase_flat(target,C2.derr.b(1e-10)).get(1),target);
        var expected = CMatrix.derr.b("""
                                      2,0,0;
                                      0,-4,0;
                                      0,0,2;
                                      """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testOrthogonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2,-1;
                        2,-2,2;
                        -1,2,1;
                             """);
        var p=JacobiGivens.erase(target,C2.derr.b(1e-10)).P;
        var result=p.transpose().mul(p);
        var expected = target.i();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPrintP() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2,-1;
                        2,-2,2;
                        -1,2,1;
                             """);
        var p=JacobiGivens.erase(target,C2.derr.b(1e-10)).P;
        System.out.println(p);
    }

}
