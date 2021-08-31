/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet.primitive;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import test.PTe;

/**
 *
 * @author masao
 */
public class AndNGTest {
    
    public AndNGTest() {
    }

    TRangeBuilder<Integer> c=TRangeBuilder.natural(Integer.class);

    @Test
    public void testContainsOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.ge(6),c.le(6)));
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }

    @Test
    public void testContainsNoOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.gt(6),c.lt(6)));
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertFalse(x.contains(5));
    }
    
    @Test
    public void testNegateOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.ge(6),c.le(6)).negate());
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }

    @Test
    public void testNegateNoOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.gt(6),c.lt(6)).negate());
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
    }
    
    @Test
    public void testContainsOther() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.ge(6),c.lt(7)));
        assertTrue(c.lt(8).contains(x));
        assertTrue(c.lt(7).contains(x));
        assertFalse(c.lt(6).contains(x));
    }
    
    @Test
    public void testReduction00() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.gt(6),c.ge(6)));
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertFalse(x.contains(5));
        assertEquals(x,c.gt(6));
    }
    
    @Test
    public void testReduction01() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.lt(6),c.gt(6)));
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertFalse(x.contains(5));
        assertEquals(x,c.none());
    }
    
    @Test
    public void testReduction02() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.lt(7),c.and(c.ge(6),c.lt(10))));
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    
    @Test
    public void testReduction03() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.and(c.ge(0),c.lt(7)),c.and(c.ge(6),c.lt(10))));
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    
    @Test
    public void testReduction04() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.and(c.ge(0),c.lt(5)),c.and(c.ge(6),c.lt(10))).negate());
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }
    
    @Test
    public void testReduction05() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.and(c.and(c.ge(0),c.lt(5)).negate(),c.and(c.ge(6),c.lt(10)).negate()));
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }
    
    @Test
    public void testReduction06() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.and(c.ge(0),c.lt(5)),c.and(c.ge(6),c.lt(10))));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    
    @Test
    public void testReduction07() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.or(c.and(c.ge(0),c.lt(5)),c.lt(0)),c.gt(4)));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
        assertEquals(x,c.whole());
    }
    
    @Test
    public void testReduction08() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.or(c.lt(0),c.gt(4)),c.none()));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
    }
}
