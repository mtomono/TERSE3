/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import java.math.BigDecimal;
import static math.KRational.gcd;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class RationalNGTest {
    
    public RationalNGTest() {
    }

    @Test
    public void testGcd() {
        System.out.println(test.TestUtils.methodName(0));
        long result = gcd(49,56);
        long expected = 7;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReduce() {
        System.out.println(test.TestUtils.methodName(0));
        KRational result = new KRational(49,56).reduce();
        KRational expected = new KRational(7,8);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToDouble() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new KRational(1,2).toDouble();
        double expected = 0.5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToList() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Long> result = new KRational(1,2).toList();
        TList<Long> expected = TList.sof(1L,2L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToBigDecimal() {
        System.out.println(test.TestUtils.methodName(0));
        BigDecimal result = new KRational(1,2).toBigDecimal();
        BigDecimal expected = new BigDecimal(0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        KRational result = new KRational(1,6).add(new KRational(1,4)).reduce();
        KRational expected = new KRational(5,12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSub() {
        System.out.println(test.TestUtils.methodName(0));
        KRational result = new KRational(1,6).sub(new KRational(1,4)).reduce();
        KRational expected = new KRational(-1,12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        KRational result = new KRational(3,14).mul(new KRational(7,9)).reduce();
        KRational expected = new KRational(1,6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiv() {
        System.out.println(test.TestUtils.methodName(0));
        KRational result = new KRational(3,14).div(new KRational(9,7)).reduce();
        KRational expected = new KRational(1,6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    
}
