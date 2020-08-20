/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import static math.KBigDecimal.b;
import static math.KMatrix.matrix;
import static math.KRational.r;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class KMatrixNGTest {
    
    public KMatrixNGTest() {
    }

    @Test
    public void testEliminate1235() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KBigDecimal> result = matrix(TList.sof(TList.sof(b(1),b(2)),TList.sof(b(3),b(5)))).eliminate();
        KMatrix<KBigDecimal> expected = matrix(TList.sof(TList.sof(b(1),b(2)),TList.sof(b(0),b(1))));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminateI3() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i)).eliminate();
        KMatrix<KRational> expected = matrix(new Integer[][]{
                                                            {1,0,0},
                                                            {0,1,0},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEliminate121210112() {
        System.out.println(test.TestUtils.methodName(0));
        KMatrix<KRational> result = matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {2,5,3},
                                                            {1,1,2}
                                                        },i->r(i)).eliminate();
        KMatrix<KRational> expected = matrix(new Integer[][]{
                                                            {1,2,1},
                                                            {0,1,1},
                                                            {0,0,1}
                                                        },i->r(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.same(expected));
    }
}
