/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.point2;

/**
 *
 * @author masao
 */
public class Polygon2dNGTest {
    
    public Polygon2dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testC() {
    }

    @Test
    public void testAsEdges() {
    }

    @Test
    public void testAsClosed() {
    }

    @Test
    public void testIsClosed() {
    }

    @Test
    public void testSubPolygon() {
        System.out.println(test.TestUtils.methodName(0));
        Polygon2d result = Polygon2d.c(point2(1, 2), point2(3, 4), point2(5, 6), point2(7, 8), point2(9, 10)).subPolygon(1, 4);
        Polygon2d expected = Polygon2d.c(point2(3, 4), point2(5, 6), point2(7, 8));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    Polygon2d tested = Polygon2d.c(TList.range(0, 1000).map(i->i*(2.0*PI/1000)).map(r->new TPoint2d(cos(r), sin(r))));

    @Test
    public void testPrint() {
        System.out.println(tested);
    }
    
    @Test
    public void testCoverage() {
        System.out.println(test.TestUtils.methodName(0));
        Rect2d result = tested.cover();
        Rect2d expected = Rect2d.c(-1.0, -1.0, 1.0, 1.0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEquality() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2d> result = Polygon2d.c(point2(1, 2), point2(3, 4));
        TList<TPoint2d> expected = TList.of(point2(1, 2), point2(3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquality2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2d> result = Polygon2d.c(point2(1, 2), point2(3, 4));
        List<TPoint2d> expected = Arrays.asList(point2(1, 2), point2(3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquality3() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<TPoint2d>> result = TList.of(Polygon2d.c(point2(1, 2), point2(3, 4)), Polygon2d.c(point2(5,6)));
        List<List<TPoint2d>> expected = Arrays.asList(Arrays.asList(point2(1, 2), point2(3, 4)), Arrays.asList(point2(5,6)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEquality4() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Polygon2d> result = TList.of(Polygon2d.c(point2(1, 2)), Polygon2d.c(point2(3, 4)));
        TList<Polygon2d> expected = TList.of(Polygon2d.c(point2(1, 2)), Polygon2d.c(point2(3, 4)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Polygon2d> result = tested.chunk(300);
        TList<Polygon2d> expected = TList.sof(Polygon2d.c(tested.subList(0, 300)), Polygon2d.c(tested.subList(300, 600)), Polygon2d.c(tested.subList(600, 900)), Polygon2d.c(tested.subList(700, 1000)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testClosestPart() {
        System.out.println(test.TestUtils.methodName(0));
        Segment2d result = tested.closestPart(point2(0.8, 0.8), tested.hint(50, 0.1)).get();
        Segment2d expected = tested.asEdges().get(124);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
