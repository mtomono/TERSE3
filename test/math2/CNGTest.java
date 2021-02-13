/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

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
}
