/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static java.lang.Math.sqrt;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.*;

/**
 * cross pattern 1
 * 
 * y
 * 
 * 3       y
 *           \ 
 * 2           \   
 *               \
 * 1   x-----------\------------
 *                   \        
 * 0       
 *    -1   0   1   2   3  x
 * @author masao
 */
public class Ray2d_XNGTest1 {
    Ray2d tested = ray2(-1, 1, 2, 0);
    Segment2d segment = segment2(0, 3, 3, 0);
    
    public Ray2d_XNGTest1() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void announceTestName() {
        System.out.println(getClass().getSimpleName());
    }

    @Test
    public void testClosestPoint_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(segment.asLine());
        TPoint2d expected = point2(2, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_Ray2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(segment.asRay());
        TPoint2d expected = point2(2, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_Segment2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(segment);
        TPoint2d expected = point2(2, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = tested.closestPoint(segment.start);
        TPoint2d expected = point2(0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(segment.start);
        double expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected, err);
    }

    @Test
    public void testDistance_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(segment.asLine());
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected, err);
    }

    @Test
    public void testDistance_Ray2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(segment.asRay());
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected, err);
    }

    @Test
    public void testDistance_Segment2d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(segment);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected, err);
    }

    @Test
    public void testHit_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(segment.start);
        boolean expected = false;
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
        boolean result = tested.hit(segment.asRay());
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Line2d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(segment.asLine());
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToString() {
    }
    
}
