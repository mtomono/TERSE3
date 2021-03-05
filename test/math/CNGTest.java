/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import java.math.BigDecimal;
import java.text.ParseException;
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
        C.Builder<Integer> b = C.i;
        C<Integer> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<Integer> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testLong() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b = C.l;
        C<Long> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<Long> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testDouble() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Double> b = C.d;
        C<Double> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<Double> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testBigDecimal() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<BigDecimal> b = C.bd;
        C<BigDecimal> result = b.b(5).add(b.b(13.0)).div(b.b("3"));
        C<BigDecimal> expected = b.b(6L);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testRational() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Rational> b = C.r;
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
    @Test
    public void testFormatBigDecimal() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        C<BigDecimal> result = C.bd.f("5,000.20");
        C<BigDecimal> expected = C.bd.b("5000.20");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testFormatRational() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        C<Rational> result = C.r.f("1,000/2,000");
        C<Rational> expected = C.r.b("1000/2000");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testFormatStringBigDecimal() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        String result = C.bd.f("5000.20").toFormattedString();
        String expected = "5,000.2";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testFormatStringRational() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        String result = C.r.f("1000/2000").toFormattedString();
        String expected = "1,000/2,000";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testSqrt() {
        System.out.println(test.TestUtils.methodName(0));
        TList<C<Integer>> result = TList.range(0, 10).map(i->C.i.b(i).sqrt());
        TList<C<Integer>> expected = TList.sofi(0,1,1,2,2,2,2,3,3,3).map(i->C.i.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
