/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class HomogeneousPermutationNGTest {
    
    public HomogeneousPermutationNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testCalc_3args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = HomogeneousPermutation.calc(TList.of(0,2,4,6), TList.of(1,3,5), 1);
        TList<TList<Integer>> expected = TList.of(
                TList.of(1,3,5, 0,2,4,6),
                TList.of(0, 1,3,5, 2,4,6),
                TList.of(0,2, 1,3,5, 4,6),
                TList.of(0,2,4, 1,3,5, 6),
                TList.of(0,2,4,6, 1,3,5)
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCalc_3args2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = HomogeneousPermutation.calc(TList.of(0,2,4,6), TList.of(1,3,5), 2);
        TList<TList<Integer>> expected = TList.of(
                TList.of(1, 0, 3,5, 2,4,6),
                TList.of(1,3, 0, 5, 2,4,6),
                TList.of(1, 0,2, 3,5, 4,6),
                TList.of(1,3, 0,2, 5, 4,6),
                TList.of(1, 0,2,4, 3,5, 6),
                TList.of(1,3, 0,2,4, 5, 6),
                TList.of(1, 0,2,4,6, 3,5),
                TList.of(1,3, 0,2,4,6, 5),
                TList.of(0, 1, 2, 3,5, 4,6),
                TList.of(0, 1,3, 2, 5, 4,6),
                TList.of(0, 1, 2,4, 3,5, 6),
                TList.of(0, 1,3, 2,4, 5, 6),
                TList.of(0, 1, 2,4,6, 3,5),
                TList.of(0, 1,3, 2,4,6, 5),
                TList.of(0,2, 1, 4, 3,5, 6),
                TList.of(0,2, 1,3, 4, 5, 6),
                TList.of(0,2, 1, 4,6, 3,5),
                TList.of(0,2, 1,3, 4,6, 5),
                TList.of(0,2,4, 1, 6 ,3,5),
                TList.of(0,2,4, 1,3, 6 ,5)
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCalc_4args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = HomogeneousPermutation.calc(TList.of(0,2,4,6), 3, 9, 1);
        TList<TList<Integer>> expected = HomogeneousPermutation.calc(TList.of(0,2,4,6), TList.nCopies(3, 9), 1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCalc_4args2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = HomogeneousPermutation.calc(TList.of(0,2,4,6), 3, 9, 2);
        TList<TList<Integer>> expected = HomogeneousPermutation.calc(TList.of(0,2,4,6), TList.nCopies(3, 9), 2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCalc_5args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = HomogeneousPermutation.calc(4, 0, 3, 9, 1);
        TList<TList<Integer>> expected = HomogeneousPermutation.calc(TList.nCopies(4, 0), TList.nCopies(3, 9), 1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testCalc_5args2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = HomogeneousPermutation.calc(4, 0, 3, 9, 2);
        TList<TList<Integer>> expected = HomogeneousPermutation.calc(TList.nCopies(4, 0), TList.nCopies(3, 9), 2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
