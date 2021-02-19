/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ExperimentScaleNGTest {
    
    public ExperimentScaleNGTest() {
    }
    
    @Test
    public void testScale3() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new BigDecimal("0.001").scale();
        int expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testScale0() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new BigDecimal("122").scale();
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPrecision3() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new BigDecimal("122").precision();
        int expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPrecision6() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new BigDecimal("122.001").precision();
        int expected = 6;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSetScale() {
        System.out.println(test.TestUtils.methodName(0));
        BigDecimal result = new BigDecimal("122.001").setScale(0, RoundingMode.DOWN);
        BigDecimal expected = new BigDecimal("122");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSetScaleN() {
        System.out.println(test.TestUtils.methodName(0));
        BigDecimal result = new BigDecimal("-122.001").setScale(0, RoundingMode.DOWN);
        BigDecimal expected = new BigDecimal("-122");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testBigDecimalLongValue() {
        System.out.println(test.TestUtils.methodName(0));
        long result = new BigDecimal("112.001").longValue();
        long expected = 112L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testBigDecimalLongValueN() {
        System.out.println(test.TestUtils.methodName(0));
        long result = new BigDecimal("-112.001").longValue();
        long expected = -112L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRational() {
        System.out.println(test.TestUtils.methodName(0));
        int result = 153/23;
        int expected = 6;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRationalN() {
        System.out.println(test.TestUtils.methodName(0));
        int result = 153/-23;
        int expected = -6;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDouble() {
        System.out.println(test.TestUtils.methodName(0));
        Double tested = Double.parseDouble("125.00");
        boolean result = tested.equals((double)tested.longValue());
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testDoubleN() {
        System.out.println(test.TestUtils.methodName(0));
        Double tested = Double.parseDouble("-125.00");
        boolean result = tested.equals((double)tested.longValue());
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
