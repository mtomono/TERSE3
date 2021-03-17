/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PowerNGTest {
    
    public PowerNGTest() {
    }

    @Test
    public void testPow() {
        System.out.println(test.TestUtils.methodName(0));
        long result = Power.pow(2L, 30, 1L, (a,b)->a*b);
        long expected = 1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
        
    @Test
    public void testPow5() {
        System.out.println(test.TestUtils.methodName(0));
        long result = Power.pow(2L, 5, 1L, (a,b)->a*b);
        long expected = 32;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
        
    @Test
    public void testPow7() {
        System.out.println(test.TestUtils.methodName(0));
        long result = Power.pow(2L, 7L, 1L, (a,b)->a*b);
        long expected = 128;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
        
    @Test(groups="performance")
    public void testPowP() {
        System.out.println(test.TestUtils.methodName(0));
        long result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.pow(2L, 30, 1L, (a,b)->a*b);
        long expected =  1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowLongP() {
        System.out.println(test.TestUtils.methodName(0));
        long result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.pow(2L, 62L, 1L, (a,b)->a*b);
        long expected =  4611686018427387904L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowxP() {
        System.out.println(test.TestUtils.methodName(0));
        long result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.powx(2L, 30, 1L, (a,b)->a*b);
        long expected =  1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowlP() {
        System.out.println(test.TestUtils.methodName(0));
        long result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.powl(2L, 30, 1L, (a,b)->a*b);
        long expected = 1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowllP() {
        System.out.println(test.TestUtils.methodName(0));
        long result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.powll(2L, 30, 1L, (a,b)->a*b);
        long expected = 1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowsP() {
        System.out.println(test.TestUtils.methodName(0));
        long result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.pows(2L, 30, 1L, (a,b)->a*b);
        long expected = 1073741824;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test(groups="performance")
    public void testPowPD() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.pow(1.00000001, 2000, 1D, (a,b)->a*b);
        double expected =  1.0000200001997475;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowxPD() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.powx(1.00000001, 2000, 1D, (a,b)->a*b);
        double expected =  1.00002000019978;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testPowsPD() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        for (int i=0;i<10000000;i++)
            result=Power.pows(1.00000001, 2000, 1D, (a,b)->a*b);
        double expected =  1.0000200001997475;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
