/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import static arithmetic.Arithmetic.mod;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ModNGTest {
    
    public ModNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testO_int_int() {
        System.out.println(test.TestUtils.methodName(0));
        int result = mod.o(-5, 3);
        int expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_long_long() {
        System.out.println(test.TestUtils.methodName(0));
        long result = mod.o(-5L, 3);
        long expected = 1L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_int_long() {
        System.out.println(test.TestUtils.methodName(0));
        long result = mod.o(-5, 3L);
        long expected = 1L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_long_int() {
        System.out.println(test.TestUtils.methodName(0));
        long result = mod.o(-5L, 3);
        long expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        double result = mod.o(-5D, 3D);
        double expected = 1D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_int_double() {
        System.out.println(test.TestUtils.methodName(0));
        double result = mod.o(-5, 3D);
        double expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_long_double() {
        System.out.println(test.TestUtils.methodName(0));
        double result = mod.o(-5L, 3D);
        double expected = 1D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_double_int() {
        System.out.println(test.TestUtils.methodName(0));
        double result = mod.o(-5D, 3);
        double expected = 1D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_double_long() {
        System.out.println(test.TestUtils.methodName(0));
        double result = mod.o(-5D, 3L);
        double expected = 1D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
