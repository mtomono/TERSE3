/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import math.LuDecompose;
import math.PLU;
import math.NonsingularMatrixException;
import math.PivotingMightBeRequiredException;
import math.CMatrix;
import math.CList;
import math.MathBuilder;
import math.C;
import math.Rational;
import collection.TList;
import debug.Te;
import java.math.BigDecimal;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import test.PTe;

/**
 *
 * @author masao
 */
public class CMatrixNGTest {
    MathBuilder<BigDecimal> kbdb=new MathBuilder<>(C.bd);
    MathBuilder<Rational> krb=new MathBuilder<>(C.r);
    public CMatrixNGTest() {
    }
    
    @Test
    public void testSubMatrix() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(             "1,0,0;"
                                                    + "0,1,0;"
                                                    + "0,0,1").subMatrix(0,1,2,3);
        CMatrix<Rational> expected = krb.m(           "0,1;"
                                                    + "0,0");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubMatrixForSetting() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(             "1,0,0;"
                                                    + "0,1,0;"
                                                    + "0,0,1").subMatrix(1,1,3,3);
        result.body.get(0).reset(TList.sof(C.r.b(2),C.r.b(2)));
        CMatrix<Rational> expected = krb.m(           "2,2;"
                                                    + "0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEliminate1235() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<BigDecimal> result = kbdb.m(          "1,2;"
                                                    + "3,5").doolittleSubMatrix();
        CMatrix<BigDecimal> expected = kbdb.m(        "1,2;"
                                                    + "3,-1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testEliminateI3() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").doolittleSubMatrix();
        CMatrix<Rational> expected = krb.m(                 "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminate121253112() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2").doolittleSubMatrix();
        CMatrix<Rational> expected = krb.m(                 "1,2,1;"
                                                          + "2,1,1;"
                                                          + "1,-1,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.same(expected));
    }
    
    @Test
    public void testEliminate121253112e() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = new LuDecompose<>(krb.m(
                                                            "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2")).doolittleWholeMatrix().target;
        CMatrix<Rational> expected = krb.m(                 "1,2,1;"
                                                          + "2,1,1;"
                                                          + "1,-1,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testEliminate2442185212762433() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(
                                                            "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3").doolittleSubMatrix().map(c->c.m((r->r.reduce())));
        CMatrix<Rational> expected = krb.m(                 "2,4,4,2;"
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
        CMatrix<Rational> result = new LuDecompose<>(krb.m(
                                                            "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3")).doolittleWholeMatrix().target.map(c->c.m((r->r.reduce())));
        CMatrix<Rational> expected = krb.m(                 "2,4,4,2;"
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
        CMatrix<Rational> result = krb.m(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").scale(C.r.b(2));
        CMatrix<Rational> expected = krb.m(                 "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInv() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2").scale(C.r.b(2).inv());
        CMatrix<Rational> expected = krb.m(                 "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2").scale(C.r.b(2).inv()).map(c->c.m(r->r.reduce()));
        CMatrix<Rational> expected = krb.m(                 "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> added = krb.m(                    "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        CMatrix<Rational> result = krb.m(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").add(added);
        CMatrix<Rational> expected = krb.m(                 "1,0,1;"
                                                          + "0,2,0;"
                                                          + "1,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSub() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> added = krb.m(                    "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        CMatrix<Rational> result = krb.m(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").sub(added);
        CMatrix<Rational> expected = krb.m(                 "1,0,-1;"
                                                          + "0,0,0;"
                                                          + "-1,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> added = krb.m(                    "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        CMatrix<Rational> result = krb.m(                   "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1").mul(added);
        CMatrix<Rational> expected = krb.m(                 "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillLower() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1").fillLower(C.r.b(0));
        CMatrix<Rational> expected = krb.m(                 "1,1,1;"
                                                          + "0,1,1;"
                                                          + "0,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillUpper() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1").fillUpper(C.r.b(0));
        CMatrix<Rational> expected = krb.m(                 "1,0,0;"
                                                          + "1,1,0;"
                                                          + "1,1,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> result = krb.m(                   "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1").fillDiagonal(TList.sof(0,0,0).map(i->C.r.b(i)));
        CMatrix<Rational> expected = krb.m(                 "0,1,1;"
                                                          + "1,0,1;"
                                                          + "1,1,0");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        TList<C<Rational>> result = krb.m(                  "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2").getDiagonal();
        TList<C<Rational>> expected = TList.sof(1,5,2).map(i->C.r.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testLu() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m(                 "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2");
        TList<CMatrix<Rational>> result = original.luDecompose();
        TList<CMatrix<Rational>> expected = TList.sof(krb.m(
                                                            "1,0,0;"
                                                          + "2,1,0;"
                                                          + "1,-1,1"),
                                                       krb.m(
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
        CMatrix<Rational> l = krb.m(                        "1,0,0,0;"
                                                          + "2,1,0,0;"
                                                          + "3,4,1,0;"
                                                          + "-1,-3,0,1");
        CList<Rational> result = l.forwardSubstitution(krb.l("4,1,-3,4"));
        CList<Rational> expected = krb.l("4,-7,13,-13");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testBackwardSubstitution() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> l = krb.m(                        "1,1,0,3;"
                                                          + "0,-1,-1,-5;"
                                                          + "0,0,3,13;"
                                                          + "0,0,0,-13");
        CList<Rational> result = l.backwardSubstitution(krb.l("4,-7,13,-13")).m(ll->ll.map(c->c.m(r->r.rednorm())));
        CList<Rational> expected = krb.l("-1,2,0,1");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testLuPivot0() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m(                 "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3");
        TList<CMatrix<Rational>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.rednorm())));
        TList<CMatrix<Rational>> expected = TList.sof(krb.m("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1"),
                                                       krb.m("2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.rednorm())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testLuPivot1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m(                 "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        );
        PLU<Rational> result = original.pluDecompose();
        TList<CMatrix<Rational>> expected = TList.sof(krb.m("0,0,1,0;"
                                                          + "1,0,0,0;"
                                                          + "0,1,0,0;"
                                                          + "0,0,0,1"),
                                                        krb.m("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1"),
                                                       krb.m("2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.restore().map(c->c.m(r->r.rednorm())));
        assertEquals(result.restore(),original);
    }
    @Test
    public void testLuSolve1() {
        System.out.println(test.TestUtils.methodName(0));
        PLU<Rational> original = krb.m(                     "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3").pluDecompose();
        CList<Rational> result = original.solve(krb.l("6,2,12,5")).m(l->l.map(c->c.m(r->r.rednorm())));
        CList<Rational> expected=krb.l("-3,2,-1,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testForwardSubstitution1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> l = krb.m("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1");
        CList<Rational> result = l.forwardSubstitution(krb.l("2,12,6,5")).m(ll->ll.map(c->c.m(r->r.rednorm())));
        CList<Rational> expected = krb.l("2,11,5,4");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testBackwardSubstitution1() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> l = krb.m(                        "2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2");
        CList<Rational> result = l.backwardSubstitution(krb.l("2,11,5,4")).m(ll->ll.map(c->c.m(r->r.rednorm())));
        CList<Rational> expected = krb.l("-3,2,-1,2");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testInvNoPivot() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> l = krb.m("2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3");
        CMatrix<Rational> result = l.luDecompose().inv().map(c->c.m(r->r.rednorm()));
        assertEquals(Te.e(result.mul(l).map(c->c.m(r->r.rednorm()))),krb.I(4));
        CMatrix<Rational> expected = krb.m("7/12,-1/3,-1/6,1/6;"
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
        CMatrix<Rational> l = krb.m("1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3");
        CMatrix<Rational> result = l.pluDecompose().inv().map(c->c.m(r->r.rednorm()));
        assertEquals(Te.e(result.mul(l).map(c->c.m(r->r.rednorm()))),krb.I(4));
        CMatrix<Rational> expected = krb.m(             "-1/6,7/12,-1/3,1/6;"
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
        CMatrix<Rational> original = krb.m(                 "1,1,0,3;"
                                                          + "2,1,-1,1;"
                                                          + "3,-1,-1,2;"
                                                          + "-1,2,3,-1");
        TList<CMatrix<Rational>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.rednorm())));
        TList<CMatrix<Rational>> expected = TList.sof(krb.m("1,0,0,0;"
                                                          + "2,1,0,0;"
                                                          + "3,4,1,0;"
                                                          + "-1,-3,0,1"),
                                                       krb.m("1,1,0,3;"
                                                          + "0,-1,-1,-5;"
                                                          + "0,0,3,13;"
                                                          + "0,0,0,-13"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.rednorm())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testLuPivot3() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m("1,1,0,3;"
                                                          + "3,-1,-1,2;"
                                                          + "2,1,-1,1;"
                                                          + "-1,2,3,-1");
        TList<CMatrix<Rational>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.rednorm())));
        TList<CMatrix<Rational>> expected = TList.sof(krb.m("1,0,0,0;"
                                                          + "3,1,0,0;"
                                                          + "2,1/4,1,0;"
                                                          + "-1,-3/4,-3,1"),
                                                       krb.m("1,1,0,3;"
                                                          + "0,-4,-1,-7;"
                                                          + "0,0,-3/4,-13/4;"
                                                          + "0,0,0,-13"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.rednorm())));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testTranspose() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m("1,1,0,3;"
                                                          + "3,-1,-1,2;"
                                                          + "2,1,-1,1;"
                                                          + "-1,2,3,-1");
        CMatrix<Rational> result = original.transpose();
        CMatrix<Rational> expected = krb.m("1,3,2,-1;"
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
        CMatrix<Rational> original = krb.m("1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        );
        try {
            TList<CMatrix<Rational>> result = original.luDecompose().map(m->m.map(c->c.m(r->r.rednorm())));
        } catch (Exception e) {
            PTe.e(e);
            throw e;
        }
    }
    
    @Test(expectedExceptions=NonsingularMatrixException.class)
    public void testLuFailsBecauseOfNonsingularity() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m("1,2,2,1;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        );
        try {
            CList<Rational> result = original.pluDecompose().solve(krb.l("6,2,12,5"));
        } catch (Exception e) {
            PTe.e(e);
            throw e;
        }
    }

    @Test(expectedExceptions=NonsingularMatrixException.class)
    public void testLuFailsBecauseOfNonsingularityDuringDecomposiiton() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m(                 "1,2,2,1;"
                                                          + "2,4,4,2;"
                                                          + "3,6,6,3;"
                                                          + "2,4,3,3;");
        try {
            TList<CMatrix<Rational>> result = original.pluDecompose().map(m->m.map(c->c.m(r->r.rednorm())));
        } catch (Exception e) {
            PTe.e(e);
            throw e;
        }
    }
    
    @Test(expectedExceptions=NonsingularMatrixException.class)
    public void testLuPivot4() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Rational> original = krb.m("1,2,2,1;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;");
        TList<CMatrix<Rational>> result = original.pluDecompose().map(m->m.map(c->c.m(r->r.rednorm())));
        TList<CMatrix<Rational>> expected = TList.sof(krb.m("0,0,0,1;"
                                                          + "1,0,0,0;"
                                                          + "0,1,0,0;"
                                                          + "0,0,1,0"),
                                                        krb.m("1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1,0,1,0;"
                                                          + "1/2,0,0,1"),
                                                       krb.m("2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,-1,1;"
                                                          + "0,0,0,0"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().map(c->c.m(r->r.rednorm())));
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
}
