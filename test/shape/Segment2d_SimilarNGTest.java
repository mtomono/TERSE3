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
 * test with similar line families
 * @author masao
 */
public class Segment2d_SimilarNGTest {
    Segment2d tested = segment2(1, 1, 3, 1);
    Line2d line = line2(1, 1, 2, 0);
    Ray2d ray = ray2(1, 1, 2, 0);
    Segment2d segment = segment2(1, 1, 3, 1);
    TPoint2d point = point2(1, 1);
    
    public Segment2d_SimilarNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testEquals() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equals(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquivalent() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equivalent(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAsLine() {
        System.out.println(test.TestUtils.methodName(0));
        Line2d result = tested.asLine();
        Line2d expected = line;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAsRay() {
        System.out.println(test.TestUtils.methodName(0));
        Ray2d result = tested.asRay();
        Ray2d expected = ray;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse() {
        System.out.println(test.TestUtils.methodName(0));
        Segment2d result = tested.reverse();
        Segment2d expected = segment2(3, 1, 1, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAsVector() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d result = tested.asVector();
        TVector2d expected = vector2(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testLength() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.length();
        double expected = 2;
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
        TPoint2d expected = point2(1, 1);
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
    public void testHit_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(point);
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
