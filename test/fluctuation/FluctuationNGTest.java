/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fluctuation;

import math.KBigDecimal;
import static math.KBigDecimal.b;
import orderedSet.Range;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class FluctuationNGTest {
    fluctuation.Builder<KBigDecimal> builder;
    
    public FluctuationNGTest() {
        this.builder=Fluctuation.builder(KBigDecimal.class);
    }

    @Test
    public void testAccumulate() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested.normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b(1)).a(3,4,b(1)).a(4,5,b(2)).a(5,7,b(3)).a(7,8,b(2)).a(8,9,b(1)).a(9,10,b(2)).a(10,11,b(1)).a(11,13,b(2)).a(13,14,b(1)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testAccumulate02() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).a(2,12,b(0)).build();
        Fluctuation<KBigDecimal> result = tested.normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b(1)).a(3,4,b(1)).a(4,5,b(2)).a(5,7,b(3)).a(7,8,b(2)).a(8,9,b(1)).a(9,10,b(2)).a(10,11,b(1)).a(11,13,b(2)).a(13,14,b(1)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testAccumulate03() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,12,b(1)).a(12,14,b(1)).a(11,13,b(1)).a(2,12,b(0)).build();
        Fluctuation<KBigDecimal> result = tested.normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b(1)).a(3,4,b(1)).a(4,5,b(2)).a(5,7,b(3)).a(7,8,b(2)).a(8,9,b(1)).a(9,10,b(2)).a(10,11,b(1)).a(11,13,b(2)).a(13,14,b(1)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested0 = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).build();
        Fluctuation<KBigDecimal> tested1 = builder.accumulates().a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested0.add(tested1).normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b(1)).a(3,4,b(1)).a(4,5,b(2)).a(5,7,b(3)).a(7,8,b(2)).a(8,9,b(1)).a(9,10,b(2)).a(10,11,b(1)).a(11,13,b(2)).a(13,14,b(1)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testSub() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested0 = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(2)).a(9,14,b(2)).a(11,13,b(2)).build();
        Fluctuation<KBigDecimal> tested1 = builder.accumulates().a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested0.sub(tested1).normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b(1)).a(3,4,b(1)).a(4,5,b(2)).a(5,7,b(3)).a(7,8,b(2)).a(8,9,b(1)).a(9,10,b(2)).a(10,11,b(1)).a(11,13,b(2)).a(13,14,b(1)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testScale() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested0 = builder.accumulates().a(-1,1,b(2)).a(3,7,b(2)).a(5,10,b(2)).a(4,8,b(2)).a(9,14,b(2)).a(11,13,b(2)).build();
        Fluctuation<KBigDecimal> result = tested0.scale(b("0.5")).normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b("1.0")).a(3,4,b("1.0")).a(4,5,b("2.0")).a(5,7,b("3.0")).a(7,8,b("2.0")).a(8,9,b("1.0")).a(9,10,b("2.0")).a(10,11,b("1.0")).a(11,13,b("2.0")).a(13,14,b("1.0")).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCut() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested.cut(new Range<>(6L,9L)).normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(6,7,b(3)).a(7,8,b(2)).a(8,9,b(1)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testSufficient() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        System.out.println(tested.normalize());
        System.out.println(tested.scale(b(2)).normalize());
        assertTrue(tested.scale(b(2)).sub(tested).normalize().sufficient());
    }
    @Test
    public void testSufficient02() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested0 = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> tested1 = builder.accumulates().a(6,10,b(1)).a(4,8,b(1)).a(9,13,b(1)).build();
        System.out.println(tested0.normalize());
        System.out.println(tested1.normalize());
        assertTrue(tested0.sub(tested1).normalize().sufficient());
    }
    @Test
    public void testNotSufficient() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested0 = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> tested1 = builder.accumulates().a(6,10,b(1)).a(4,8,b(1)).a(9,15,b(1)).build();
        System.out.println(tested0.sub(tested1).normalize());
        assertFalse(tested0.sub(tested1).normalize().sufficient());
    }
    @Test
    public void testDot() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested0 = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> tested1 = builder.accumulates().a(3,6,b(1)).build();
        KBigDecimal result = tested0.accumulates.dot(tested1);
        KBigDecimal expected = b(6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testRound() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested.round(e->b(2));
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1L,14L,b(2)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testMax() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested.normalize().round(Fluctuation.max());
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1L,14L, b(3)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testMin() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested.normalize().round(Fluctuation.min());
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1L,14L,b(0)).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testChattering() {
        System.out.println(test.TestUtils.methodName(0));
        Fluctuation<KBigDecimal> tested = builder.accumulates().a(-1,1,b(1)).a(3,7,b(1)).a(5,6,b("1.0625")).a(6,10,b(1)).a(4,8,b(1)).a(9,14,b(1)).a(11,13,b(1)).build();
        Fluctuation<KBigDecimal> result = tested.normalize().unchattering(b("0.2")).map(e->e.round(Fluctuation.min())).stream().reduce((a,b)->a.add(b)).orElse(tested.builder.empty()).normalize().accumulates.nonzero();
        Fluctuation<KBigDecimal> expected = builder.accumulates().a(-1,1,b(1)).a(3,4,b(1)).a(4,5,b(2)).a(5,7,b("3.0000")).a(7,8,b("2.0000")).a(8,9,b("1.0000")).a(9,10,b("2.0000")).a(10,11,b("1.0000")).a(11,13,b("2.0000")).a(13,14,b("1.0000")).build();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
