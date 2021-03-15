/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

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
    public void testFactorization() {
    }

    @Test
    public void testPow() {
        System.out.println(test.TestUtils.methodName(0));
        long result = Integers.pow(2, 5);
        long expected = 32;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
