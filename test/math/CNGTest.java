/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import math.RationalOp;
import math.BigDecimalOp;
import math.IntegerOp;
import math.C;
import math.Rational;
import math.LongOp;
import math.DoubleOp;
import java.math.BigDecimal;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CNGTest {
    
    public CNGTest() {
    }
    
    @Test
    public void testInt() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Integer> b = C.b(new IntegerOp());
        C<Integer> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<Integer> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testLong() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b = C.b(new LongOp());
        C<Long> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<Long> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testDouble() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Double> b = C.b(new DoubleOp());
        C<Double> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<Double> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testBigDecimal() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<BigDecimal> b = C.b(new BigDecimalOp());
        C<BigDecimal> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<BigDecimal> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testRational() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Rational> b = C.b(new RationalOp());
        C<Rational> result = b.b(5).add(b.b(13)).div(b.b("3"));
        C<Rational> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testInterpolate() {
        System.out.println(test.TestUtils.methodName(0));
        C<Integer> result = C.i.b(100).interpolate(C.i.b(57), C.i.b(200), C.i.b(43));
        C<Integer> expected = C.i.b(157);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testInterpolate100() {
        System.out.println(test.TestUtils.methodName(0));
        C<Integer> result = C.i.b(100).interpolate100(C.i.b(57), C.i.b(200));
        C<Integer> expected = C.i.b(157);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testInterpolate1() {
        System.out.println(test.TestUtils.methodName(0));
        C<Double> result = C.d.b(100).interpolate1(C.d.b(0.5), C.d.b(200));
        C<Double> expected = C.d.b(150);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
