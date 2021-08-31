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
public class ComparisonNGTest {
    
    public ComparisonNGTest() {
    }
    
    TRangeBuilder<Integer> c=TRangeBuilder.natural(Integer.class);

    @Test
    public void testContainsGe() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.ge(6));
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    @Test
    public void testContainsLt() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.lt(6));
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }
    @Test
    public void testContainsGt() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.gt(6));
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertFalse(x.contains(5));
    }
    @Test
    public void testContainsLe() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.le(6));
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
    }
    @Test
    public void testContainsEq() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.eq(6));
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    @Test
    public void testContainsNe() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.ne(6));
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }

    @Test
    public void testNegateLt() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.lt(6).negate());
        assertTrue(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    @Test
    public void testNegateGe() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.ge(6).negate());
        assertFalse(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }
    @Test
    public void testNegateLe() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.le(6).negate());
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertFalse(x.contains(5));
    }
    @Test
    public void testNegateGt() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.gt(6).negate());
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertTrue(x.contains(5));
    }
    @Test
    public void testNegateNe() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.ne(6).negate());
        assertFalse(x.contains(7));
        assertTrue(x.contains(6));
        assertFalse(x.contains(5));
    }
    @Test
    public void testNegateEq() {
        System.out.println(test.TestUtils.methodName(0));
        var x=PTe.e(c.eq(6).negate());
        assertTrue(x.contains(7));
        assertFalse(x.contains(6));
        assertTrue(x.contains(5));
    }

}
