/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import debug.Te;
import math.C2;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class HouseholderNGTest {
    
    public HouseholderNGTest() {
    }

    @Test
    public void testQSymmetric() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> tested=Householder.mirror(C2.derr,TList.sof(1,1,1).toC(v->(double)v,C2.derr),TList.sof(1,-1,1).toC(v->(double)v, C2.derr));
        var result=tested.transpose();
        var expected=tested;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQOrthogonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> tested=Householder.mirror(C2.derr,TList.sof(1,1,1).toC(v->(double)v,C2.derr),TList.sof(1,-1,1).toC(v->(double)v, C2.derr));
        var result=tested.transpose().mul(tested);
        var expected=tested.i();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testRemove1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b(
                          "5,4,2,3;"
                        + "1,2,3,4;"
                        + "2,3,4,5;"
                        + "6,5,1,3;"
        );
        CMatrix<Double,C2<Double>> tested=Householder.localQ(target);
        var result=tested.mul(target).columns().get(0);
        var expected=TList.sof(8.12403840463596, 0.0, 0.0, 0.0).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRemove2() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b(
                          "5,4,2,3;"
                        + "1,2,3,4;"
                        + "2,3,4,5;"
                        + "6,5,1,3;"
        );
        CMatrix<Double,C2<Double>> h0=Householder.localQ(target);
        CMatrix<Double,C2<Double>> r1=h0.mul(target).sfix();
        CMatrix<Double,C2<Double>> s1=r1.subMatrixLR(1,1);
        CMatrix<Double,C2<Double>> h1=Householder.localQ(s1);
        var result=h1.mul(s1).columns().get(0);
        var expected=TList.sof(1.7407765595569777, 0.0, 0.0).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_QisOrthogonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b(
                          "5,4,2,3;"
                        + "1,2,3,4;"
                        + "2,3,4,5;"
                        + "6,5,1,3;"
        );
        var qr=new QrDecompose<>(target).decompose();
        var result = qr.qinv().mul(qr.q());
        var expected = target.i();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_RisUpperTriangle() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b(
                          "5,4,2,3;"
                        + "1,2,3,4;"
                        + "2,3,4,5;"
                        + "6,5,1,3;"
        );
        var qr=new QrDecompose<>(target).decompose();
        var result = qr.r();
        var expected = qr.r().fillLower(C2.derr.zero());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_QRisTarget() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b(
                          "5,4,2,3;"
                        + "1,2,3,4;"
                        + "2,3,4,5;"
                        + "6,5,1,3;"
        );
        var qr=new QrDecompose<>(target).decompose();
        var result = qr.q().mul(qr.r());
        var expected = target;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
