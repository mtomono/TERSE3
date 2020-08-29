/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
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
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).subMatrix(0,1,2,3);
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {0,1},
                                                            {0,0}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubMatrixForSetting() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).subMatrix(1,1,3,3);
        result.body.get(0).reset(TList.sof(r(2),r(2)));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {2,2},
                                                            {0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEliminate1235() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KBigDecimal> result = kbdb.matrix(TList.sof(
                TList.sof(b(1),b(2)),
                TList.sof(b(3),b(5)))).doolittleStep();
        KMatrix<KBigDecimal> expected = kbdb.matrix(TList.sof(
                TList.sof(b(1),b(2)),
                TList.sof(b(3),b(-1))));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminateI3() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).doolittleStep();
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminate121253112() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,5,3},
                                                            {1,1,2}
                                                        },i->r(i)).doolittleStep();
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,1,1},
                                                            {1,-1,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.same(expected));
    }

    @Test
    public void testEliminate121253112e() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,5,3},
                                                            {1,1,2}
                                                        },i->r(i)).doolittle();
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,1,1},
                                                            {1,-1,2}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }

    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).scaleR(r(2));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {2,0,0},
                                                            {0,2,0},
                                                            {0,0,2}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScaleS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).scaleS(r(2));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {2,0,0},
                                                            {0,2,0},
                                                            {0,0,2}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInvR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {2,0,0},
                                                            {0,2,0},
                                                            {0,0,2}
                                                        },i->r(i)).invR(r(2));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInvS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {2,0,0},
                                                            {0,2,0},
                                                            {0,0,2}
                                                        },i->r(i)).invS(r(2));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMapS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {2,0,0},
                                                            {0,2,0},
                                                            {0,0,2}
                                                        },i->r(i)).invS(r(2)).mapS(r->r.reduce());
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAddR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(new Integer[][]{
                                                            {0,0,1},
                                                            {0,1,0},
                                                            {1,0,0}
                                                        },i->r(i));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).addR(added);
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,1},
                                                            {0,2,0},
                                                            {1,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAddS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(new Integer[][]{
                                                            {0,0,1},
                                                            {0,1,0},
                                                            {1,0,0}
                                                        },i->r(i));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).addS(added);
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,1},
                                                            {0,2,0},
                                                            {1,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubR() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(new Integer[][]{
                                                            {0,0,1},
                                                            {0,1,0},
                                                            {1,0,0}
                                                        },i->r(i));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).subR(added);
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,-1},
                                                            {0,0,0},
                                                            {-1,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubS() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(new Integer[][]{
                                                            {0,0,1},
                                                            {0,1,0},
                                                            {1,0,0}
                                                        },i->r(i));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).subS(added);
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,-1},
                                                            {0,0,0},
                                                            {-1,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> added = krb.matrix(new Integer[][]{
                                                            {0,0,1},
                                                            {0,1,0},
                                                            {1,0,0}
                                                        },i->r(i));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).mul(added);
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {0,0,1},
                                                            {0,1,0},
                                                            {1,0,0}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillLower() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,1,1},
                                                            {1,1,1},
                                                            {1,1,1}
                                                        },i->r(i)).fillLower(r(0));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,1,1},
                                                            {0,1,1},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillUpper() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,1,1},
                                                            {1,1,1},
                                                            {1,1,1}
                                                        },i->r(i)).fillUpper(r(0));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {1,1,0},
                                                            {1,1,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,1,1},
                                                            {1,1,1},
                                                            {1,1,1}
                                                        },i->r(i)).fillDiagonal(TList.sof(0,0,0).map(i->r(i)));
        KMatrix<KRational> expected = krb.matrix(new Integer[][]{
                                                            {0,1,1},
                                                            {1,0,1},
                                                            {1,1,0}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetDiagonal() {
        System.out.println(test.TestUtils.methodName(0));
        TList<KRational> result = krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,5,3},
                                                            {1,1,2}
                                                        },i->r(i)).getDiagonal();
        TList<KRational> expected = TList.sof(1,5,2).map(i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
    }
    @Test
    public void testLu() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> original = krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,5,3},
                                                            {1,1,2}
                                                        },i->r(i));
        TList<KMatrix<KRational>> result = original.lu();
        TList<KMatrix<KRational>> expected = TList.sof(krb.matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {2,1,0},
                                                            {1,-1,1}
                                                        },i->r(i)),
                                                       krb.matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {0,1,1},
                                                            {0,0,2}
                                                        },i->r(i)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result,expected);
        System.out.println("lu      : " + result.stream().reduce((a,b)->a.mul(b)).get());
        assertEquals(result.stream().reduce((a,b)->a.mul(b)).get(),original);
    }
}
