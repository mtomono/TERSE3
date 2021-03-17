/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.P;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class IntegersNGTest {
    
    public IntegersNGTest() {
    }

    @Test
    public void testGcd() {
        System.out.println(test.TestUtils.methodName(0));
        long result = Integers.gcd(49,56);
        long expected = 7;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExec120() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Long> result = Integers.factorization(120);
        TList<Long> expected = TList.sofi(2,2,2,3,5).map(i->(long)i);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testExec9800() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Long> result = Integers.factorization(9800);
        TList<Long> expected = TList.sofi(2,2,2,5,5,7,7).map(i->(long)i);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testExec9800Compress() {
        System.out.println(test.TestUtils.methodName(0));
        P<TList<Long>,TList<Integer>> result = Integers.factorization(9800).compress();
        P<TList<Long>,TList<Integer>> expected = P.p(TList.sof(2L,5L,7L), TList.sofi(3,2,2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPow() {
        System.out.println(test.TestUtils.methodName(0));
        long result = Integers.pow(2, 30);
        long expected = 1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
