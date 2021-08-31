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
public class OrNGTest {
    
    public OrNGTest() {
    }

    TRangeBuilder<Integer> c=TRangeBuilder.natural(Integer.class);

    @Test
    public void testContainsOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.ge(6),c.le(6)));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
    }

    @Test
    public void testContainsNoOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.gt(6),c.lt(6)));
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }
    
    @Test
    public void testNegateOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.ge(6),c.le(6)).negate());
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertFalse(x.contains(5));
    }

    @Test
    public void testNegateNoOverlap() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.gt(6),c.lt(6)).negate());
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    
    @Test
    public void testContainsOther() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.le(6),c.ge(8)));
        assertFalse(x.contains(c.lt(8)));
        assertFalse(x.contains(c.lt(7)));
        assertTrue(x.contains(c.lt(6)));
    }
    
    @Test
    public void testReduction00() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.gt(6),c.ge(6)));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
        assertEquals(x,c.ge(6));
    }
    
    @Test
    public void testReduction01() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.le(6),c.ge(6)));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
        assertEquals(x,c.whole());
    }
    
    @Test
    public void testReduction02() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.lt(7),c.and(c.ge(6),c.lt(10))));
        assertFalse(x.contains(10));
        assertTrue(x.contains(9));
        assertTrue(x.contains(8));
        assertEquals(x,c.lt(10));
    }
    
    @Test
    public void testReduction03() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.lt(7),c.or(c.lt(6),c.gt(10))));
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
        assertTrue(x.contains(c.lt(7)));
        assertEquals(c.and(x, c.lt(7)),c.lt(7));
    }
    
    @Test
    public void testReduction04() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.and(c.ge(0),c.lt(7)),c.and(c.ge(6),c.lt(10))));
        assertFalse(x.contains(-1));
        assertTrue(x.contains(0));
        assertTrue(x.contains(1));
        assertTrue(x.contains(2));
        assertTrue(x.contains(3));
        assertTrue(x.contains(7));
        assertTrue(x.contains(9));
        assertTrue(x.contains(8));
        assertFalse(x.contains(10));
        assertFalse(x.contains(11));
    }
    
    @Test
    public void testReduction05() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.or(c.or(c.lt(0),c.ge(7)),c.or(c.lt(9),c.ge(10))));
        assertEquals(x,c.whole());
    }

}
