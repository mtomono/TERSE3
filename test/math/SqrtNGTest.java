/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import debug.Te;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class SqrtNGTest {
    static double save;
    
    @Test(groups="performance")
    public void testSqrt100M() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        int end=100*1000*1000+1;
        for (int i=0;i<end;i++) {
            result=Math.sqrt(i);
            save=result;
        }
        double expected = 10000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrt100MCompare() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        int end=100*1000*1000+1;
        for (int i=0;i<end;i++) {
            save=i;
        }
        double expected = 0.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrt400M() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        int end=400*1000*1000+1;
        for (int i=0;i<end;i++) {
            result=Math.sqrt(i);
            save=result;
        }
        double expected = 20000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrt400MCompare() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        int end=400*1000*1000+1;
        for (int i=0;i<end;i++) {
            save=i;
        }
        double expected = 0.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrtDouble100M() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        final double end=100*1000*1000+1;
        for (double i=0;i<end;i++) {
            result=Math.sqrt(i);
            save=result;
        }
        double expected = 10000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrtDouble100MCompare() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        final double end=100*1000*1000+1;
        for (double i=0;i<end;i++) {
            save=i;
        }
        double expected = 0.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrtLong100M() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        final long end=100*1000*1000+1;
        for (long i=0;i<end;i++) {
            result=Math.sqrt(i);
            save=result;
        }
        double expected = 10000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups="performance")
    public void testSqrtLong100MCompare() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        final long end=100*1000*1000+1;
        for (long i=0;i<end;i++) {
            save=i;
        }
        double expected = 0.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
