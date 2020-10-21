/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import debug.Te;
import static math.KBigDecimal.b;
import static math.KRational.r;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class KMatrixNGTest {
    MathContext<KBigDecimal> kbdb=new MathContext<>(KBigDecimal.class);
    MathContext<KRational> krb=new MathContext<>(KRational.class);
    public KMatrixNGTest() {
    }
    
    @Test
    public void testSubMatrix() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).subMatrix(0,1,2,3);
        KMatrix<KRational> expected = krb.matrix(           "0,1;"
                                                          + "0,0",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubMatrixForSetting() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).subMatrix(1,1,3,3);
        result.body.get(0).reset(TList.sof(r(2),r(2)));
        KMatrix<KRational> expected = krb.matrix(           "2,2;"
                                                          + "0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEliminate1235() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KBigDecimal> result = kbdb.matrix(          "1,2;"
                                                          + "3,5",i->b(i)).doolittleStep();
        KMatrix<KBigDecimal> expected = kbdb.matrix(        "1,2;"
                                                          + "3,-1",i->b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testEliminateI3() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).doolittleStep();
        KMatrix<KRational> expected = krb.matrix(           "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminate121253112() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2",i->r(i)).doolittleStep();
        KMatrix<KRational> expected = krb.matrix(           "1,2,1;"
                                                          + "2,1,1;"
                                                          + "1,-1,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.same(expected));
    }

    @Test
    public void testEliminate121253112e() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2",i->r(i)).doolittle();
        KMatrix<KRational> expected = krb.matrix(           "1,2,1;"
                                                          + "2,1,1;"
                                                          + "1,-1,2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).scaleR(r(2));
        KMatrix<KRational> expected = krb.matrix(           "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScaleS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).scaleS(r(2));
        KMatrix<KRational> expected = krb.matrix(           "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInvR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2",i->r(i)).invR(r(2));
        KMatrix<KRational> expected = krb.matrix(           "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInvS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2",i->r(i)).invS(r(2));
        KMatrix<KRational> expected = krb.matrix(           "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMapS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "2,0,0;"
                                                          + "0,2,0;"
                                                          + "0,0,2",i->r(i)).invS(r(2)).mapS(r->r.reduce());
        KMatrix<KRational> expected = krb.matrix(           "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAddR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(              "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0",i->r(i));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).addR(added);
        KMatrix<KRational> expected = krb.matrix(           "1,0,1;"
                                                          + "0,2,0;"
                                                          + "1,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAddS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(              "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0",i->r(i));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).addS(added);
        KMatrix<KRational> expected = krb.matrix(           "1,0,1;"
                                                          + "0,2,0;"
                                                          + "1,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(              "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0",i->r(i));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).subR(added);
        KMatrix<KRational> expected = krb.matrix(           "1,0,-1;"
                                                          + "0,0,0;"
                                                          + "-1,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(              "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0",i->r(i));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).subS(added);
        KMatrix<KRational> expected = krb.matrix(           "1,0,-1;"
                                                          + "0,0,0;"
                                                          + "-1,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(              "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0",i->r(i));
        KMatrix<KRational> result = krb.matrix(             "1,0,0;"
                                                          + "0,1,0;"
                                                          + "0,0,1",i->r(i)).mul(added);
        KMatrix<KRational> expected = krb.matrix(           "0,0,1;"
                                                          + "0,1,0;"
                                                          + "1,0,0",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillLower() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1",i->r(i)).fillLower(r(0));
        KMatrix<KRational> expected = krb.matrix(           "1,1,1;"
                                                          + "0,1,1;"
                                                          + "0,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillUpper() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1",i->r(i)).fillUpper(r(0));
        KMatrix<KRational> expected = krb.matrix(           "1,0,0;"
                                                          + "1,1,0;"
                                                          + "1,1,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(             "1,1,1;"
                                                          + "1,1,1;"
                                                          + "1,1,1",i->r(i)).fillDiagonal(TList.sof(0,0,0).map(i->r(i)));
        KMatrix<KRational> expected = krb.matrix(           "0,1,1;"
                                                          + "1,0,1;"
                                                          + "1,1,0",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        TList<KRational> result = krb.matrix(               "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2",i->r(i)).getDiagonal();
        TList<KRational> expected = TList.sof(1,5,2).map(i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testLu() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> original = krb.matrix(           "1,2,1;"
                                                          + "2,5,3;"
                                                          + "1,1,2",i->r(i));
        TList<KMatrix<KRational>> result = original.luMatrices();
        TList<KMatrix<KRational>> expected = TList.sof(krb.matrix(
                                                            "1,0,0;"
                                                          + "2,1,0;"
                                                          + "1,-1,1",i->r(i)),
                                                       krb.matrix(
                                                            "1,2,1;"
                                                          + "0,1,1;"
                                                          + "0,0,2",i->r(i)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get());
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    
    @Test
    public void testForwardSubstitution() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> l = krb.matrix(                  "1,0,0,0;"
                                                          + "2,1,0,0;"
                                                          + "3,4,1,0;"
                                                          + "-1,-3,0,1",i->r(i));
        KVector<KRational> result = l.forwardSubstitution(krb.vector("4,1,-3,4",i->r(i)));
        KVector<KRational> expected = krb.vector("4,-7,13,-13",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testBackwardSubstitution() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> l = krb.matrix(                  "1,1,0,3;"
                                                          + "0,-1,-1,-5;"
                                                          + "0,0,3,13;"
                                                          + "0,0,0,-13",i->r(i));
        KVector<KRational> result = l.backwardSubstitution(krb.vector("4,-7,13,-13",i->r(i))).map(r->r.rednorm());
        KVector<KRational> expected = krb.vector("-1,2,0,1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testLuPivot0() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> original = krb.matrix(           "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3",i->r(i));
        TList<KMatrix<KRational>> result = original.luMatrices().map(m->m.mapR(r->r.rednorm()));
        TList<KMatrix<KRational>> expected = TList.sof(krb.matrix(
                                                            "1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1",i->r(i)),
                                                       krb.matrix(
                                                            "2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2",i->r(i)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().mapR(r->r.rednorm()));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testLuPivot1() {
        System.out.println(test.TestUtils.methodName(0));
        KPivotMatrix<KRational> original = krb.matrix(      "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3;"
                                                        ,i->r(i)).pivot();
        TList<KMatrix<KRational>> result = original.luMatrices().map(m->m.mapR(r->r.rednorm()));
        TList<KMatrix<KRational>> expected = TList.sof(krb.matrix(
                                                            "1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1",i->r(i)),
                                                       krb.matrix(
                                                            "2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2",i->r(i)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + original.pinv().mul(result.stream().reduce((a,b)->a.mul(b)).get()).mapR(r->r.rednorm()));
        assertEquals(original.pinv().mul(result.stream().reduce((a,b)->a.mul(b)).get()),original);
    }
    @Test
    public void testLuSolve1() {
        System.out.println(test.TestUtils.methodName(0));
        KPivotMatrix<KRational> original = krb.matrix(      "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3",i->r(i)).pivot();
        KVector<KRational> result = original.lu().solve(krb.vector("6,2,12,5",i->r(i))).map(r->r.rednorm());
        KVector<KRational> expected=krb.vector("-3,2,-1,2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testForwardSubstitution1() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> l = krb.matrix(
                                                            "1,0,0,0;"
                                                          + "1/2,1,0,0;"
                                                          + "1/2,0,1,0;"
                                                          + "1,0,-1/5,1",i->r(i));
        KVector<KRational> result = l.forwardSubstitution(krb.vector("2,12,6,5",i->r(i))).map(r->r.rednorm());
        KVector<KRational> expected = krb.vector("2,11,5,4",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testBackwardSubstitution1() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> l = krb.matrix(                  "2,4,4,2;"
                                                          + "0,6,3,1;"
                                                          + "0,0,5,5;"
                                                          + "0,0,0,2",i->r(i));
        KVector<KRational> result = l.backwardSubstitution(krb.vector("2,11,5,4",i->r(i))).map(r->r.rednorm());
        KVector<KRational> expected = krb.vector("-3,2,-1,2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testInvNoPivot() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> l = krb.matrix(
                                                            "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "1,2,7,6;"
                                                          + "2,4,3,3",i->r(i));
        KMatrix<KRational> result = l.pivot().inv().mapR(r->r.rednorm());
        assertEquals(Te.e(result.mul(l).mapR(r->r.rednorm())),krb.I(4));
        KMatrix<KRational> expected = krb.matrix("7/12,-1/3,-1/6,1/6;"
                                                      + "-13/60,1/6,-1/15,1/6;"
                                                      + "9/20,0,1/10,-1/2;"
                                                      + "-11/20,0,1/10,1/2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testInv() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> l = krb.matrix(
                                                            "1,2,7,6;"
                                                          + "2,4,4,2;"
                                                          + "1,8,5,2;"
                                                          + "2,4,3,3",i->r(i));
        KMatrix<KRational> result = l.pivot().inv().mapR(r->r.rednorm());
        assertEquals(Te.e(result.mul(l).mapR(r->r.rednorm())),krb.I(4));
        KMatrix<KRational> expected = krb.matrix("-1/6,7/12,-1/3,1/6;"
                                                      + "-1/15,-13/60,1/6,1/6;"
                                                      + "1/10,9/20,0,-1/2;"
                                                      + "1/10,-11/20,0,1/2",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testLuPivot2() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> original = krb.matrix(           "1,1,0,3;"
                                                          + "2,1,-1,1;"
                                                          + "3,-1,-1,2;"
                                                          + "-1,2,3,-1",i->r(i));
        TList<KMatrix<KRational>> result = original.luMatrices().map(m->m.mapR(r->r.rednorm()));
        TList<KMatrix<KRational>> expected = TList.sof(krb.matrix(
                                                            "1,0,0,0;"
                                                          + "2,1,0,0;"
                                                          + "3,4,1,0;"
                                                          + "-1,-3,0,1",i->r(i)),
                                                       krb.matrix(
                                                            "1,1,0,3;"
                                                          + "0,-1,-1,-5;"
                                                          + "0,0,3,13;"
                                                          + "0,0,0,-13",i->r(i)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().mapR(r->r.rednorm()));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testLuPivot3() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> original = krb.matrix(           "1,1,0,3;"
                                                          + "3,-1,-1,2;"
                                                          + "2,1,-1,1;"
                                                          + "-1,2,3,-1",i->r(i));
        TList<KMatrix<KRational>> result = original.luMatrices().map(m->m.mapR(r->r.rednorm()));
        TList<KMatrix<KRational>> expected = TList.sof(krb.matrix(
                                                            "1,0,0,0;"
                                                          + "3,1,0,0;"
                                                          + "2,1/4,1,0;"
                                                          + "-1,-3/4,-3,1",i->r(i)),
                                                       krb.matrix(
                                                            "1,1,0,3;"
                                                          + "0,-4,-1,-7;"
                                                          + "0,0,-3/4,-13/4;"
                                                          + "0,0,0,-13",i->r(i)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get().mapR(r->r.rednorm()));
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
    @Test
    public void testTranspose() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> original = krb.matrix(           "1,1,0,3;"
                                                          + "3,-1,-1,2;"
                                                          + "2,1,-1,1;"
                                                          + "-1,2,3,-1",i->r(i));
        KMatrix<KRational> result = original.transpose();
        KMatrix<KRational> expected = krb.matrix(           "1,3,2,-1;"
                                                          + "1,-1,1,2;"
                                                          + "0,-1,-1,3;"
                                                          + "3,2,1,-1",i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
}
