/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.PolygonC2d.X;
import static shape.PolygonC2d.Y;
import static shape.ShapeUtil.pni;
import static shape.ShapeUtil.point2;
import solver.graph.GridCore;

/**
 *
 * @author masao
 */
public class PolygonC2dNGTest {
    
    public PolygonC2dNGTest() {
    }

    @Test
    public void testDivide() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5));
        TList<PolygonC2d> result = tested.divide(1,3);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(4,3),point2(5,5),point2(1,2),point2(0,5),point2(0,0)),PolygonC2d.c(point2(5,5),point2(4,3),point2(5,0)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDivideRev() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5));
        TList<PolygonC2d> result = tested.divide(3,1);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(5,5),point2(4,3),point2(5,0)),PolygonC2d.c(point2(4,3),point2(5,5),point2(1,2),point2(0,5),point2(0,0)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDivideFrom0() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5));
        TList<PolygonC2d> result = tested.divide(0,4);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(0,0),point2(1,2),point2(0,5)),PolygonC2d.c(point2(1,2),point2(0,0),point2(4,3),point2(5,0),point2(5,5)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDivideAtLast() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5));
        TList<PolygonC2d> result = tested.divide(5,3);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(0,5),point2(5,5),point2(1,2)),PolygonC2d.c(point2(5,5),point2(0,5),point2(0,0),point2(4,3),point2(5,0)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMonotonizeY() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5));
        TList<PolygonC2d> result = tested.monotonize(Y);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(4,3),point2(5,5),point2(1,2),point2(0,0)),PolygonC2d.c(point2(5,5),point2(4,3),point2(5,0)),PolygonC2d.c(point2(0,0),point2(1,2),point2(0,5)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMonotonizeX() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(TList.sof(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5)).map(p->point2(p.y,p.x)).reverse());
        TList<PolygonC2d> result = tested.monotonize(X);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(2,1),point2(0,0),point2(5,0)),PolygonC2d.c(point2(3,4),point2(5,5),point2(0,5)),PolygonC2d.c(point2(5,5),point2(3,4),point2(0,0),point2(2,1)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMonotonizeXinNoReflex() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5));
        TList<PolygonC2d> result = tested.monotonize(X);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(0,0),point2(4,3),point2(5,0),point2(5,5),point2(1,2),point2(0,5)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMonotonizeXinConvex() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(5,0),point2(5,5),point2(1,2));
        TList<PolygonC2d> result = tested.monotonize(X);
        TList<PolygonC2d> expected = TList.sof(PolygonC2d.c(point2(0,0),point2(5,0),point2(5,5),point2(1,2)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDigitizeEdges() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(5,0),point2(5,5),point2(1,2));
        TList<List<Integer>> result = tested.digitizeEdges(GridCore.ortho);
        TList<List<Integer>> expected = TList.sof(
                pni(0,0,0),pni(1,0,0),pni(2,0,0),pni(3,0,0),pni(4,0,0),pni(5,0,0),
                pni(5,0,0),pni(5,1,0),pni(5,2,0),pni(5,3,0),pni(5,4,0),pni(5,5,0),
                pni(5,5,0),pni(4,5,0),pni(4,4,0),pni(3,4,0),pni(3,3,0),pni(2,3,0),pni(2,2,0),pni(1,2,0),
                pni(1,2,0),pni(1,1,0),pni(0,1,0),pni(0,0,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDigitize() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(5,0),point2(5,5),point2(1,2));
        TList<List<Integer>> result = tested.digitize(GridCore.ortho);
        TList<List<Integer>> expected = TList.sof(
                pni(0,0,0),pni(0,1,0),
                pni(1,0,0),pni(1,1,0),pni(1,2,0),
                pni(2,0,0),pni(2,1,0),pni(2,2,0),pni(2,3,0),
                pni(3,0,0),pni(3,1,0),pni(3,2,0),pni(3,3,0),pni(3,4,0),
                pni(4,0,0),pni(4,1,0),pni(4,2,0),pni(4,3,0),pni(4,4,0),pni(4,5,0),
                pni(5,0,0),pni(5,1,0),pni(5,2,0),pni(5,3,0),pni(5,4,0),pni(5,5,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDecompose() {
        System.out.println(test.TestUtils.methodName(0));
        PolygonC2d tested = PolygonC2d.c(point2(0,0),point2(2,0),point2(2,-2),point2(4,-2),point2(4,2),point2(0,2),point2(0,0));
        TList<TList<TPoint2d>> result = TList.c();
        tested.decompose(result);
        TList<TList<TPoint2d>> expected = TList.sof(
                TList.sof(point2(2,0),point2(2,-2),point2(4,-2)),
                TList.sof(point2(2.0, 0.0), point2(4.0, -2.0), point2(4.0, 2.0)),
                TList.sof(point2(2.0, 0.0), point2(4.0, 2.0), point2(0.0, 2.0)),
                TList.sof(point2(2.0, 0.0), point2(0.0, 2.0), point2(0.0, 0.0)),
                TList.sof(point2(2.0, 0.0), point2(0.0, 0.0), point2(0.0, 0.0))
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
