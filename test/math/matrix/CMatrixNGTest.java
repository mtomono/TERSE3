/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import debug.Te;
import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import math.C2;
import math.CList;
import math.Rational;
import static math.matrix.LuDecompose.doolittleStep;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import string.TString;
import test.PTe;

/**
 *
 * @author masao
 */
public class CMatrixNGTest {
    CMatrix.Builder<BigDecimal,C2<BigDecimal>,C2.Builder<BigDecimal>> mbd=CMatrix.bd;
    CMatrix.Builder<Double,C2<Double>,C2.Builder<Double>>md=CMatrix.d;
    CMatrix.Builder<Rational,C2<Rational>,C2.Builder<Rational>> mr=CMatrix.r;
    CList.Builder<Rational,C2<Rational>> lr=CList.r2;
    public CMatrixNGTest() {
    }
    
    @Test
    public void testMult() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> tested0=mr.b(
                 "1,2,3,4;"
                +"2,3,4,5;"
                +"3,4,5,6"
        );
        CMatrix<Rational,C2<Rational>> result = tested0.mul(tested0.transpose());
        CMatrix<Rational,C2<Rational>> expected = mr.b(
                 "30,40,50;"
                +"40,54,68;"
                +"50,68,86"
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSubMatrix() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(             "1,0,0;"
                                                    + "0,1,0;"
                                                    + "0,0,1").subMatrix(0,1,2,3);
        CMatrix<Rational,C2<Rational>> expected = mr.b(           "0,0;"
                                                    + "1,0");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubMatrixForSetting() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(             "1,0,0;"
                                                    + "0,1,0;"
                                                    + "0,0,1").subMatrix(1,1,3,3);
        result.body.get(0).reset(TList.sof(C2.r.b(2),C2.r.b(2)));
        CMatrix<Rational,C2<Rational>> expected = mr.b(           "2,2;"
                                                    + "0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAppendDiag() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> m0=mr.b(
                          "1,2,3;"
                        + "4,5,6"
        );
        CMatrix<Rational,C2<Rational>> m1=mr.b(
                          "9,8;"
                        + "7,6;"
                        + "5,4"
        );
        var result = m0.appendDiag(m1);
        var expected = mr.b(
                          "1,2,3,0,0;"
                        + "4,5,6,0,0;"
                        + "0,0,0,9,8;"
                        + "0,0,0,7,6;"
                        + "0,0,0,5,4"
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testReset() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.i(5).sfix();
        result.subMatrixLR(2, 2).reset(mr.b(Te.e("""
                                            1,1,1;
                                            1,1,1;
                                            1,1,1
                                            """)));
        CMatrix<Rational,C2<Rational>> expected = mr.b("""
                                     1,0,0,0,0;
                                     0,1,0,0,0;
                                     0,0,1,1,1;
                                     0,0,1,1,1;
                                     0,0,1,1,1
                                                       """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUpper() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        TList<TList<Integer>> result = target.upper();
        TList<TList<Integer>> expected = TList.sof(0,0, 0,1, 0,2, 0,3, 1,1, 1,2, 1,3, 2,2, 2,3, 3,3).fold(2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testLower() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        TList<TList<Integer>> result = target.lower();
        TList<TList<Integer>> expected = TList.sof(0,0, 1,0, 1,1, 2,0, 2,1, 2,2, 3,0, 3,1, 3,2, 3,3).fold(2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testUpperNoDiag() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        TList<TList<Integer>> result = target.upperNoDiag();
        TList<TList<Integer>> expected = TList.sof(0,1, 0,2, 0,3, 1,2, 1,3, 2,3).fold(2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testLowerNoDiag() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        TList<TList<Integer>> result = target.lowerNoDiag();
        TList<TList<Integer>> expected = TList.sof(1,0, 2,0, 2,1, 3,0, 3,1, 3,2).fold(2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testEliminate1235() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<BigDecimal,C2<BigDecimal>> result = mbd.b(          
                                                        "1,2;"
                                                      + "3,5").t(m->doolittleStep(m));
        CMatrix<BigDecimal,C2<BigDecimal>> expected = mbd.b(
                                                        "1,2;"
                                                      + "3,-1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testEliminateI3() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(
                                                            "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").t(m->doolittleStep(m));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminate121253112() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(
                                                            "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2").t(m->doolittleStep(m));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,2,1;"
                                                          + "2,1,1;"
                                                          + "1,-1,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    
    @Test
    public void testEliminate121253112e() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = new LuDecompose<>(mr.b(
                                                            "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2")).doolittle();
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,2,1;"
                                                          + "2,1,1;"
                                                          + "1,-1,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testEliminate2442185212762433() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(
                                                            "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3").t(m->doolittleStep(m)).map(c->c.m((r->r.reduce())));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "2,4,4,2;"
                                                          + "1/2,6,3,1;"
                                                          + "1/2,0,5,5;"
                                                          + "1,0,-1,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testEliminate2442185212762433e() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = new LuDecompose<>(mr.b(
                                                            "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3")).doolittle().map(c->c.m((r->r.reduce())));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "2,4,4,2;"
                                                          + "1/2,6,3,1;"
                                                          + "1/2,0,5,5;"
                                                          + "1,0,-1/5,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testScale() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").scale(C2.r.b(2));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInv() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2").scale(C2.r.b(2).inv());
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2").scale(C2.r.b(2).inv()).map(c->c.m(r->r.reduce()));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> added = mr.b(                    "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").add(added);
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,0,1;"
                                                          + "0,2,0;"
                                                          + "1,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSub() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> added = mr.b(                    "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").sub(added);
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,0,-1;"
                                                          + "0,0,0;"
                                                          + "-1,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> added = mr.b(                    "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").mul(added);
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillLower() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1").fillLower(C2.r.b(0));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,1,1;"
                                                          + "0,1,1;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillUpper() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1").fillUpper(C2.r.b(0));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "1,0,0;"
                                                          + "1,1,0;"
                                                          + "1,1,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> result = mr.b(                   "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1").fillDiagonal(TList.sof(0,0,0).map(i->C2.r.b(i)));
        CMatrix<Rational,C2<Rational>> expected = mr.b(                 "0,1,1;"
                                                          + "1,0,1;"
                                                          + "1,1,0");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        TList<C2<Rational>> result = mr.b(                  "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2").getDiagonal();
        TList<C2<Rational>> expected = TList.sof(1,5,2).map(i->C2.r.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testLu() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b(                 "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2");
        TList<CMatrix<Rational,C2<Rational>>> result = original.luDecompose();
        TList<CMatrix<Rational,C2<Rational>>> expected = TList.sof(mr.b(
                                                            "1,0,0;"
                                                          + "2,1,0;"
                                                          + "1,-1,1"),
                                                       mr.b(
                                                            "1,2,1;"
                                                          + "0,1,1;"
                                                          + "0,0,2"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get());
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    
    @Test
    public void testForwardSubstitution() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> l = mr.b(                        "1,0,0,0;"
                                                          + "2,1,0,0;"
                                                          + "3,4,1,0;"
                                                          + "-1,-3,0,1");
        CList<Rational,C2<Rational>> result = l.forwardSubstitution(lr.b("4,1,-3,4"));
        CList<Rational,C2<Rational>> expected = lr.b("4,-7,13,-13");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testBackwardSubstitution() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> l = mr.b(                        "1,1,0,3;"
                                                          + "0,-1,-1,-5;"
                                                          + "0,0,3,13;"
                                                          + "0,0,0,-13");
        CList<Rational,C2<Rational>> result = l.backwardSubstitution(lr.b("4,-7,13,-13")).m(ll->ll.map(c->c.m(r->r.simplify())));
        CList<Rational,C2<Rational>> expected = lr.b("-1,2,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testLuPivot0() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b(                 "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3");
        TList<CMatrix<Rational,C2<Rational>>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.simplify())));
        TList<CMatrix<Rational,C2<Rational>>> expected = TList.sof(mr.b("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1"),
                                                       mr.b("2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.simplify())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testLuPivot1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b(                 "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        );
        PLU<Rational,C2<Rational>> result = original.pluDecompose();
        TList<CMatrix<Rational,C2<Rational>>> expected = TList.sof(mr.b("0,0,1,0;"
                                                          + "1,0,0,0;"
                                                          + "0,1,0,0;"
                                                          + "0,0,0,1"),
                                                        mr.b("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1"),
                                                       mr.b("2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.restore().map(c->c.m(r->r.simplify())));
        assertEquals(result.restore(),original);
    }
    @Test
    public void testLuSolve1() {
        System.out.println(test.TestUtils.methodName(0));
        PLU<Rational,C2<Rational>> original = mr.b(                     "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3").pluDecompose();
        CList<Rational,C2<Rational>> result = original.solve(lr.b("6,2,12,5")).m(l->l.map(c->c.m(r->r.simplify())));
        CList<Rational,C2<Rational>> expected=lr.b("-3,2,-1,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testForwardSubstitution1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> l = mr.b("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1");
        CList<Rational,C2<Rational>> result = l.forwardSubstitution(lr.b("2,12,6,5")).m(ll->ll.map(c->c.m(r->r.simplify())));
        CList<Rational,C2<Rational>> expected = lr.b("2,11,5,4");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testBackwardSubstitution1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> l = mr.b(                        "2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2");
        CList<Rational,C2<Rational>> result = l.backwardSubstitution(lr.b("2,11,5,4")).m(ll->ll.map(c->c.m(r->r.simplify())));
        CList<Rational,C2<Rational>> expected = lr.b("-3,2,-1,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testInvNoPivot() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> l = mr.b(
                                                            "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3");
        CMatrix<Rational,C2<Rational>> result = l.luDecompose().inv().map(c->c.m(r->r.simplify()));
        assertEquals(Te.e(result.mul(l).map(c->c.m(r->r.simplify()))),mr.i(4));
        CMatrix<Rational,C2<Rational>> expected = mr.b("7/12,-1/3,-1/6,1/6;"
                                                      + "-13/60,1/6,-1/15,1/6;"
                                                      + "9/20,0,1/10,-1/2;"
                                                      + "-11/20,0,1/10,1/2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testInvert() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> l = mr.b(
                                                            "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3");
        CMatrix<Rational,C2<Rational>> result = l.pluDecompose().inv().map(c->c.m(r->r.simplify()));
        assertEquals(Te.e(result.mul(l).map(c->c.m(r->r.simplify()))),mr.i(4));
        CMatrix<Rational,C2<Rational>> expected = mr.b(             "-1/6,7/12,-1/3,1/6;"
                                                      + "-1/15,-13/60,1/6,1/6;"
                                                      + "1/10,9/20,0,-1/2;"
                                                      + "1/10,-11/20,0,1/2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testLuPivot2() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b(                 "1,1,0,3;"
                                                          + "2,1,-1,1;"
                                                          + "3,-1,-1,2;"
                                                          + "-1,2,3,-1");
        TList<CMatrix<Rational,C2<Rational>>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.simplify())));
        TList<CMatrix<Rational,C2<Rational>>> expected = TList.sof(mr.b("1,0,0,0;"
                                                          + "2,1,0,0;"
                                                          + "3,4,1,0;"
                                                          + "-1,-3,0,1"),
                                                       mr.b("1,1,0,3;"
                                                          + "0,-1,-1,-5;"
                                                          + "0,0,3,13;"
                                                          + "0,0,0,-13"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.simplify())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testLuPivot3() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b("1,1,0,3;"
                                                          + "3,-1,-1,2;"
                                                          + "2,1,-1,1;"
                                                          + "-1,2,3,-1");
        TList<CMatrix<Rational,C2<Rational>>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.simplify())));
        TList<CMatrix<Rational,C2<Rational>>> expected = TList.sof(mr.b("1,0,0,0;"
                                                          + "3,1,0,0;"
                                                          + "2,1/4,1,0;"
                                                          + "-1,-3/4,-3,1"),
                                                       mr.b("1,1,0,3;"
                                                          + "0,-4,-1,-7;"
                                                          + "0,0,-3/4,-13/4;"
                                                          + "0,0,0,-13"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.simplify())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testTranspose() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b("1,1,0,3;"
                                                          + "3,-1,-1,2;"
                                                          + "2,1,-1,1;"
                                                          + "-1,2,3,-1");
        CMatrix<Rational,C2<Rational>> result = original.transpose();
        CMatrix<Rational,C2<Rational>> expected = mr.b("1,3,2,-1;"
                                                          + "1,-1,1,2;"
                                                          + "0,-1,-1,3;"
                                                          + "3,2,1,-1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    
    
// from here are exception tests
    
    
    @Test(expectedExceptions=PivotingMightBeRequiredException.class)
    public void testLuFailsBecauseOfALackOfPivoting() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b("1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        );
        try {
            TList<CMatrix<Rational,C2<Rational>>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.simplify())));
        } catch (Exception e) {
            PTe.e(e);
            throw e;
        }
    }
    
    @Test(expectedExceptions=NonsingularMatrixException.class)
    public void testLuFailsBecauseOfNonsingularity() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b("1,2,2,1;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        );
        try {
            CList<Rational,C2<Rational>> result = original.pluDecompose().solve(lr.b("6,2,12,5"));
        } catch (Exception e) {
            PTe.e(e);
            throw e;
        }
    }

    @Test(expectedExceptions=NonsingularMatrixException.class)
    public void testLuFailsBecauseOfNonsingularityDuringDecomposiiton() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b(                 "1,2,2,1;"
                                                          + "2,4,4,2;"
                                                          + "3,6,6,3;"
                                                          + "2,4,3,3;");
        try {
            TList<CMatrix<Rational,C2<Rational>>> result = original.pluDecompose().map(m->m.map(c->c.m(r->r.simplify())));
        } catch (Exception e) {
            PTe.e(e);
            throw e;
        }
    }
    
    @Test(expectedExceptions=NonsingularMatrixException.class)
    public void testLuPivot4() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational,C2<Rational>> original = mr.b("1,2,2,1;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3");
        TList<CMatrix<Rational,C2<Rational>>> result = original.pluDecompose().map(m->m.map(c->c.m(r->r.simplify())));
        TList<CMatrix<Rational,C2<Rational>>> expected = TList.sof(mr.b(
                                                            "0,0,0,1;"
                                                          + "1,0,0,0;"
                                                          + "0,1,0,0;"
                                                          + "0,0,1,0"),
                                                        mr.b("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1,0,1,0;"
                                                          + "1/2,0,0,1"),
                                                       mr.b("2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,-1,1;"
                                                          + "0,0,0,0"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.simplify())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
        /**
         * though the exception must be thrown from the line where pluDecompose() is executed, i intentionally 
         * kept the code after that.
         * in this specific case, the last line still can restore the original matrix.
         * it's not practical to allow this case to succeed because the result is not usable as 
         * an expected LU decomposition result (upper one is not even triangle).
         * but the simple fact it restored caught my interest.
         */
    }

    @Test
    public void testPlu() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> original = md.b("""
                                                 5,4,3,2,1;
                                                 4,4,3,2,1;
                                                 3,3,3,2,1;
                                                 2,2,2,2,1;
                                                 1,1,1,1,1
                                                   """);
        TList<CMatrix<Double,C2<Double>>> result = original.pluDecompose();
        System.out.println("result  : " + result);
    }
    
    @Test
    public void testQ0() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> original = CMatrix.derr.b("""
                                                            1,1,0;
                                                            0,1,0;
                                                            0,1,1
                                                             """);
        CMatrix<Double,C2<Double>> result = original.qrDecompose().q();
        CMatrix<Double,C2<Double>> expected =CMatrix.derr.b(
                new TString("""
                         1.0, 0.0,     0.0;
                         0.0, #{r2}, -#{r2};
                         0.0, #{r2},  #{r2}
                           """).let("#{r2}", sqrt(2)/2).body());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testQ() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> original = CMatrix.derr.b("""
                                                            1,0,0;
                                                            1,1,1;
                                                            0,0,1
                                                            """);
        CMatrix<Double,C2<Double>> result = original.qrDecompose().q();
        CMatrix<Double,C2<Double>> expected =CMatrix.derr.b(
                new TString("""
                         #{r2}, -#{r2},  0.0;
                         #{r2},  #{r2},  0.0;
                         0.0,    0.0,    1.0
                           """).let("#{r2}", sqrt(2)/2).body());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testR() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> original = CMatrix.derr.b("""
                                                         1,0,0;
                                                         1,1,1;
                                                         0,0,1
                                                             """);
        CMatrix<Double,C2<Double>> result = original.qrDecompose().r();
        CMatrix<Double,C2<Double>> expected =CMatrix.derr.b(
                 new TString("""
                        #{r2inv}, #{r2},  #{r2};
                        0.0,      #{r2},  #{r2};
                        0.0,       0.0,  1.0
                            """).let("#{r2}", sqrt(2)/2)
                     .let("#{r2inv}", 2/sqrt(2)).body());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testRsolve() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> original = CMatrix.derr.b("""
                                                     1,0,0;
                                                     1,1,1;
                                                     0,0,1
                                                        """);
        CList<Double,C2<Double>> result = original.qrDecompose().solve(TList.sof(1.0,1.0,1.0).toC(v->v, C2.derr));
        CList<Double,C2<Double>> expected =TList.sof(1.0,-1.0,1.0).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
}
