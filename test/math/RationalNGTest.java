/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import math.Rational;
import collection.TList;
import java.math.BigDecimal;
import static math.Rational.gcd;
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
        Rational result = new Rational(49,56).reduce();
        Rational expected = new Rational(7,8);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDoubleValue() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Rational(1,2).doubleValue();
        double expected = 0.5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntValue() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new Rational(8,3).intValue();
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCompareToNeg() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new Rational(4,5).compareTo(new Rational(5,6));
        int expected = -1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCompareToPos() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new Rational(5,6).compareTo(new Rational(4,5));
        int expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCompareToZero() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new Rational(5,6).compareTo(new Rational(5,6));
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToList() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Long> result = new Rational(1,2).toList();
        TList<Long> expected = TList.sof(1L,2L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        Rational result = new Rational(1,6).add(new Rational(1,4)).reduce();
        Rational expected = new Rational(5,12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSub() {
        System.out.println(test.TestUtils.methodName(0));
        Rational result = new Rational(1,6).sub(new Rational(1,4)).reduce();
        Rational expected = new Rational(-1,12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        Rational result = new Rational(3,14).mul(new Rational(7,9)).reduce();
        Rational expected = new Rational(1,6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiv() {
        System.out.println(test.TestUtils.methodName(0));
        Rational result = new Rational(3,14).div(new Rational(9,7)).reduce();
        Rational expected = new Rational(1,6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    
}
