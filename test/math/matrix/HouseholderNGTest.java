/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import java.util.Optional;
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
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        CMatrix<Double,C2<Double>> tested=Householder.localQ(target);
        var result=tested.mul(target).columns().get(0);
        var expected=TList.sof(-8.12403840463596, 0.0, 0.0, 0.0).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRemove2() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        CMatrix<Double,C2<Double>> h0=Householder.localQ(target);
        CMatrix<Double,C2<Double>> r1=h0.mul(target).sfix();
        CMatrix<Double,C2<Double>> s1=r1.subMatrixLR(1,1);
        CMatrix<Double,C2<Double>> h1=Householder.localQ(s1);
        var result=h1.mul(s1).columns().get(0);
        var expected=TList.sof(-1.7407765595569777, 0.0, 0.0).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_QisOrthogonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var result = qr.qinv().mul(qr.q());
        var expected = target.i();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_SquareOfQsDetisOne() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var result = Optional.of(qr.q().luDecompose().det()).map(d->d.mul(d)).get();
        var expected = C2.derr.one();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_RisUpperTriangle() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var result = qr.r();
        var expected = qr.r().fillLower(C2.derr.zero());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testQrDecomposeH_Ris() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var result = qr.r();
        var expected = CMatrix.derr.b("""
    -8.12403840463596, -7.139306476801299, -3.323470256441984, -5.785300076028639;
    1.1102230246251565E-16, -1.7407765595569784, -4.177863742936747, -4.996028725928527;
    3.3306690738754696E-16, 2.736526283353413E-16, -1.2247448713915898, -0.7348469228349542;
    4.440892098500626E-16, 0.0, 5.551115123125783E-17, -0.17320508075688756;
                                    """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testQrDecomposeH_Qis() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var rinv=qr.r().luDecompose().inv();
        var q=target.mul(rinv);
        var result = qr.q();
        var expected = CMatrix.derr.b("""
    -0.6154574548966638, 0.22630095274240733, -0.7348469228349533, -0.17320508075688817;
    -0.12309149097933272, -0.6440873270360821, 0.08164965809277289, -0.7505553499465134;
    -0.24618298195866545, -0.7137183894183611, -0.16329931618554577, 0.6350852961085883;
    -0.7385489458759964, 0.15666989036012788, 0.653197264742181, 0.05773502691896297
                                                                        """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testQrDecomposeH_QCalculatedFromR() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var rinv=qr.r().luDecompose().inv();
        var q=target.mul(rinv);
        System.out.println(q);
        var result = q.mul(q.transpose());
        var expected = target.i();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testQrDecomposeH_QRisTarget() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var result = qr.q().mul(qr.r());
        var expected = target;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQrDecomposeH_RinvRisI() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var qr=new QrDecomposeH<>(target).decompose();
        var result = qr.rinv().mul(qr.r());
        var expected = target.i();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
