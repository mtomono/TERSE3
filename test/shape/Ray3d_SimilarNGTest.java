/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.*;

/**
 * test with similar line families
 * @author masao
 */
public class Ray3d_SimilarNGTest {
    Ray3d tested = ray3(1, 1, 1, 2, 0, 0);
    Line3d line = line3(1, 1, 1, 2, 0, 0);
    Ray3d ray = ray3(1, 1, 1, 2, 0, 0);
    Segment3d segment = segment3(1, 1, 1, 3, 1, 1);
    TPoint3d point = point3(1, 1, 1);

    public Ray3d_SimilarNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testEquals_Object() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equals(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquals_Ray3d_double() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equals(ray, 0);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquivalent_Ray3d_double() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.equivalent(ray, 0);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquivalent_Ray3d() {
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
        Line3d result = tested.asLine();
        Line3d expected = line;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse() {
        System.out.println(test.TestUtils.methodName(0));
        Ray3d result = tested.reverse();
        Ray3d expected = ray3(1, 1, 1, -2, 0, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMove() {
        System.out.println(test.TestUtils.methodName(0));
        Ray3d result = tested.move(vector3(1, 2, 3));
        Ray3d expected = ray3(2, 3, 4, 2, 0, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testInSameSide() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.inSameSide(ray.direction);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHasParallelPartOnSide_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hasParallelPartOnSide(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHasParallelPartOnSide_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hasParallelPartOnSide(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOtherGetsClosestOnSide_Line3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.otherGetsClosestOnSide(line);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_Line3d() {
        // N/A
    }

    @Test
    public void testOtherGetsClosestOnSide_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.otherGetsClosestOnSide(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_Ray3d() {
        // N/A
    }

    @Test
    public void testOtherGetsClosestOnSide_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.otherGetsClosestOnSide(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_Segment3d() {
        // N/A
    }

    @Test
    public void testOtherGetsClosestOnSide_TPoint3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.otherGetsClosestOnSide(point);
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClosestPoint_TPoint3d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3d result = tested.closestPoint(point);
        TPoint3d expected = point;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_Line3d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(line);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_TPoint3d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(point);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(ray);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        double result = tested.distance(segment);
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_Line3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(line);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHit_TPoint3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.hit(point);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParallel_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.parallel(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParallel_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.parallel(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParallel_Line3d() {
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
    
    @Test
    public void testDoesSidedPartFormSegment_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.doesSidedPartFormSegment(ray);
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSidedPartSegment_Ray3d() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Segment3d> result = tested.sidedPartSegment(ray);
        Optional<Segment3d> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDoesSidedPartFormRay() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.doesSidedPartFormRay(ray);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSidedPartRay() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Ray3d> result = tested.sidedPartRay(ray);
        Optional<Ray3d> expected = Optional.of(ray3(1, 1, 1, 2, 0, 0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDoesSidedPartFormSegment_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = tested.doesSidedPartFormSegment(segment);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSidedPartSegment_Segment3d() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Segment3d> result = tested.sidedPartSegment(segment);
        Optional<Segment3d> expected = Optional.of(segment3(1, 1, 1, 3, 1, 1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
