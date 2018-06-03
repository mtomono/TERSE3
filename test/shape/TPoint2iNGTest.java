/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TPoint2iNGTest {
    
    public TPoint2iNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    TPoint2i p(int x, int y) {
        return new TPoint2i(x,y);
    }

    @Test
    public void testC() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = TPoint2i.c(p(2,3), p(3,2));
        TPoint2i expected = p(1,-1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRetval() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = p(1, 2);
        TPoint2i expected = result.retval(p->p.setX(2));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertNotEquals(result, expected);
    }

    @Test
    public void testSelf() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = p(1, 2);
        TPoint2i expected = result.self(p->p.setX(2));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTo() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = p(1,1).to(p(2,3));
        TPoint2i expected = p(1,2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFrom() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = p(1,1).from(p(2,3));
        TPoint2i expected = p(-1,-2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAddR() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i origin = p(1,1);
        TPoint2i result = origin.addR(p(2,3));
        TPoint2i expected = p(3,4);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        assertNotEquals(origin, result);
    }

    @Test
    public void testAddS() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i origin = p(1,1);
        TPoint2i result = origin.addS(p(2,3));
        TPoint2i expected = p(3,4);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        assertEquals(origin, result);
    }

    @Test
    public void testScaleR_int() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i origin = p(1,1);
        TPoint2i result = origin.scaleR(3);
        TPoint2i expected = p(3,3);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        assertNotEquals(origin, result);
    }

    @Test
    public void testScaleS() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i origin = p(1,1);
        TPoint2i result = origin.scaleS(3);
        TPoint2i expected = p(3,3);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        assertEquals(origin, result);
    }

    @Test
    public void testScaleR_double() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i origin = p(1,1);
        TVector2d result = origin.scaleR(3.3);
        TVector2d expected = new TVector2d(3.3,3.3);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        assertNotEquals(origin, result);
    }

    @DataProvider(name="det")
    public Object[][] detData() {
        return new Object[][]{
            {1,0,0,1,1},
            {0,1,1,0,-1},
            {1,0,2,0,0}
        };
    }
    @Test(dataProvider="det")
    public void testDet(int x0, int y0, int x1, int y1, int det) {
        System.out.println(test.TestUtils.methodName(0));
        int result = p(x0,y0).det(p(x1,y1));
        int expected = det;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name="manhattan")
    public Object[][] manhattanData() {
        return new Object[][]{
            {1,2,3},
            {-1,1,2},
            {1,0,1}
        };
    }
    @Test(dataProvider="manhattan")
    public void testManhattanLength(int x, int y, int distance) {
        System.out.println(test.TestUtils.methodName(0));
        int result = p(x,y).manhattanLength();
        int expected = distance;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name="quadrant")
    public Object[][] quadrant() {
        return new Object[][]{
            {1,0,0},
            {0,1,1},
            {-1,0,2},
            {0,-1,3}
        };
    }
    @Test(dataProvider="quadrant")
    public void testQuadrant(int x,int y,int q) {
        System.out.println(test.TestUtils.methodName(0));
        int result = p(x,y).quadrant();
        int expected = q;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
