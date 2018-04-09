/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import static java.lang.Math.sqrt;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.point2;
import static shape.ShapeUtil.vector2;

/**
 *
 * @author masao
 */
public class Rect2dNGTest {
    
    public Rect2dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSw() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = Rect2d.c(point2(7,2), point2(2, 7)).sw();
        TPoint2d expected = point2(2, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNe() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = Rect2d.c(point2(7,2), point2(2, 7)).ne();
        TPoint2d expected = point2(7, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNw() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = Rect2d.c(point2(7,2), point2(2, 7)).nw();
        TPoint2d expected = point2(2, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSe() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d result = Rect2d.c(point2(7,2), point2(2, 7)).se();
        TPoint2d expected = point2(7, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d result = Rect2d.c(point2(7,2), point2(2, 7)).size();
        TVector2d expected = vector2(5, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    TList<TPoint2d> points = TList.range(0, 3).flatMap(i->TList.range(0, 3).map(ii->point2(ii, i)));
    @Test
    public void testPoints() {
        System.out.println(points);
    }

    @Test
    public void testInX() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.inX(p));
        TList<Boolean> expected = TList.ofStatic(
                false, true, false, 
                false, true, false, 
                false, true, false);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInY() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.inY(p));
        TList<Boolean> expected = TList.ofStatic(
                false, false, false, 
                true, true, true, 
                false, false, false);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testContains_TPoint2d() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.contains(p));
        TList<Boolean> expected = TList.ofStatic(
                false, false, false, 
                false, true, false, 
                false, false, false);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistance() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Double> result = points.map(p->tested.distance(p));
        TList<Double> expected = TList.ofStatic(
                sqrt(0.5), 0.5, sqrt(0.5), 
                0.5, 0.0, 0.5, 
                sqrt(0.5), 0.5, sqrt(0.5));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIn1quad() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.in1quad(p));
        TList<Boolean> expected = TList.ofStatic(
                false, false, false, 
                false, false, false, 
                false, false, true);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIn2quad() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.in2quad(p));
        TList<Boolean> expected = TList.ofStatic(
                false, false, false, 
                false, false, false, 
                true, false, false);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIn3quad() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.in3quad(p));
        TList<Boolean> expected = TList.ofStatic(
                true, false, false, 
                false, false, false, 
                false, false, false);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIn4quad() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->tested.in4quad(p));
        TList<Boolean> expected = TList.ofStatic(
                false, false, true, 
                false, false, false, 
                false, false, false);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testContains_Rect2d() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->Rect2d.c(point2(0.2, 0.2), p).contains(tested));
        TList<Boolean> expected = TList.ofStatic(
                false, false, false, 
                false, false, false, 
                false, false, true);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOverlaps_Rect2d() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Boolean> result = points.map(p->Rect2d.c(point2(0.2, 0.2), p).overlaps(tested));
        TList<Boolean> expected = TList.ofStatic(
                false, false, false, 
                false, true, true, 
                false, true, true);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testC() {
        // already tested in the other test
    }

    @Test
    public void testNewNe() {
        // already tested in the other test
    }

    @Test
    public void testNewSw() {
        // already tested in the other test
    }

    @Test
    public void testCover() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Rect2d> result = points.map(p->Rect2d.c(point2(0.2, 0.2), p).cover(tested));
        TList<Rect2d> expected = TList.ofStatic(
                Rect2d.c(0, 0, 1.5, 1.5), Rect2d.c(0.2, 0, 1.5, 1.5), Rect2d.c(0.2, 0, 2, 1.5), 
                Rect2d.c(0, 0.2, 1.5, 1.5), Rect2d.c(0.2, 0.2, 1.5, 1.5), Rect2d.c(0.2, 0.2, 2, 1.5), 
                Rect2d.c(0, 0.2, 1.5, 2), Rect2d.c(0.2, 0.2, 1.5, 2), Rect2d.c(0.2, 0.2, 2, 2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Optional<Rect2d>> result = points.map(p->Rect2d.c(point2(0.2, 0.2), p).intersect(tested));
        TList<Optional<Rect2d>> expected = TList.ofStatic(
                Optional.empty(), Optional.empty(), Optional.empty(), 
                Optional.empty(), Optional.of(Rect2d.c(0.5, 0.5, 1, 1)), Optional.of(Rect2d.c(0.5, 0.5, 1.5, 1)), 
                Optional.empty(), Optional.of(Rect2d.c(0.5, 0.5, 1, 1.5)), Optional.of(Rect2d.c(0.5, 0.5, 1.5, 1.5)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect2() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Optional<Rect2d>> result = points.map(p->Rect2d.c(point2(1.8, 1.8), p).intersect(tested));
        TList<Optional<Rect2d>> expected = TList.ofStatic(
                Optional.of(Rect2d.c(0.5, 0.5, 1.5, 1.5)), Optional.of(Rect2d.c(1, 0.5, 1.5, 1.5)), Optional.empty(), 
                Optional.of(Rect2d.c(0.5, 1, 1.5, 1.5)), Optional.of(Rect2d.c(1, 1, 1.5, 1.5)), Optional.empty(), 
                Optional.empty(), Optional.empty(), Optional.empty());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    TList<TPoint2d> points2 = TList.of(point2(0, 0.7), point2(1.2, 0), point2(2, 1.2), point2(0.7, 2));
    @Test
    public void testIntersect3() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Optional<Rect2d>> result = points2.map(p->Rect2d.c(point2(1.0, 1.0), p).intersect(tested));
        TList<Optional<Rect2d>> expected = TList.ofStatic(
                Optional.of(Rect2d.c(0.5, 0.7, 1, 1)), Optional.of(Rect2d.c(1.2, 0.5, 1, 1)), 
                Optional.of(Rect2d.c(1, 1, 1.5, 1.2)), Optional.of(Rect2d.c(1, 1, 0.7, 1.5)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);        
    }
    
    @Test
    public void testFarthest2() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d tested = Rect2d.c(point2(0.5, 1.5), point2(1.5, 0.5));
        TList<Double> result = points.map(p->tested.farthest(p));
        TList<Double> expected = TList.ofStatic(
                sqrt(1.5*1.5*2), sqrt(1.5*1.5+0.5*0.5), sqrt(1.5*1.5*2), 
                sqrt(1.5*1.5+0.5*0.5), sqrt(0.5), sqrt(1.5*1.5+0.5*0.5), 
                sqrt(1.5*1.5*2), sqrt(1.5*1.5+0.5*0.5), sqrt(1.5*1.5*2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRange() {
    }
    
}
