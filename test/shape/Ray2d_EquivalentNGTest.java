/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.*;

/**
 * test with equivalent line families
 * @author masao
 */
public class Ray2d_EquivalentNGTest {
    Ray2d tested = ray2(1, 1, 2, 0);
    Line2d line = line2(1, 1, 4, 0);
    Ray2d ray = ray2(1, 1, 4, 0);
    Segment2d segment = segment2(1, 1, 5, 1);
    TPoint2d point = point2(1, 1);
    
    public Ray2d_EquivalentNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testEquals() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equals(ray);
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquivalent() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equivalent(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAsLine() {
        System.out.println(test.TestUtils.methodName(0));
        Line2d result = tested.asLine();
        Line2d expected = line2(1, 1, 2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse() {
        System.out.println(test.TestUtils.methodName(0));
        Ray2d result = tested.reverse();
        Ray2d expected = ray2(1, 1, -2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions={java.lang.AssertionError.class})
    public void testClosestPoint_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(line);
        throw new RuntimeException();
    }

    @Test(expectedExceptions={java.lang.AssertionError.class})
    public void testClosestPoint_Ray2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(ray);
        throw new RuntimeException();
    }

    @Test(expectedExceptions={java.lang.AssertionError.class})
    public void testClosestPoint_Segment2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(segment);
        throw new RuntimeException();
    }

    @Test
    public void testClosestPoint_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(point);
        TPoint2d expected = point;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(point);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(line);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_Ray2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(ray);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_Segment2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(segment);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(point);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Segment2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Ray2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(line);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParallel_Segment2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.parallel(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParallel_Ray2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.parallel(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParallel_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.parallel(line);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToString() {
    }
    
}
