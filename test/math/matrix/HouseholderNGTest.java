/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.C2;
import math.CList;
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
    public void testU() {
        System.out.println(test.TestUtils.methodName(0));
        CList<Double,C2<Double>> result=new Householder<>(C2.derr,TList.sof(1,1,1).toC(v->(double)v,C2.derr),TList.sof(1,-1,1).toC(v->(double)v, C2.derr)).u();
        CList<Double,C2<Double>> expected = TList.sof(0,2,0).toC(v->(double)v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testQSymmetric() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> tested=new Householder<>(C2.derr,TList.sof(1,1,1).toC(v->(double)v,C2.derr),TList.sof(1,-1,1).toC(v->(double)v, C2.derr)).H();
        var result=tested.transpose();
        var expected=tested;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testQOrthogonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> tested=new Householder<>(C2.derr,TList.sof(1,1,1).toC(v->(double)v,C2.derr),TList.sof(1,-1,1).toC(v->(double)v, C2.derr)).H();
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
        var x=target.columns().get(0);
        var y=x.wrap(TList.sof(x.dot(x).sqrt()).append(TList.nCopies(x.body().size()-1,x.b.zero())));
        CMatrix<Double,C2<Double>> tested=new Householder<>(C2.derr,x,y).H();
        System.out.println(tested.mul(x));
        var result=tested.mul(target).columns().get(0);
        var expected=y;
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
        var x0=target.columns().get(0);
        var y0=x0.wrap(TList.sof(x0.dot(x0).sqrt()).append(TList.nCopies(x0.body().size()-1,x0.b.zero())));
        CMatrix<Double,C2<Double>> h0=new Householder<>(C2.derr,x0,y0).H();
        CMatrix<Double,C2<Double>> t1=h0.mul(target);
        var x1=t1.columns().get(1).m(l->l.replaceAt(0, x0.b.zero()));
        var y1=x1.wrap(TList.nCopies(x1.body().size(),x1.b.zero()).replaceAt(1, x1.dot(x1).sqrt()));
        CMatrix<Double,C2<Double>> h1=new Householder<>(C2.derr,x1,y1).H();
        var result=h1.mul(h0.mul(target)).columns().get(1).m(l->l.subList(1,4));
        var expected=y1.m(l->l.subList(1,4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
