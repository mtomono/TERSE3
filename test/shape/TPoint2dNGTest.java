/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TPoint2dNGTest {
    
    public TPoint2dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testRetval() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.retval(v->v.add(-1, 2));
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSelf() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.self(v->v.add(-1, 2));
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSubR_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.subR(new Point2d(-1, 2));
        TPoint2d expected = new TPoint2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.subS(new Point2d(-1, 2));
        TPoint2d expected = new TPoint2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.addR(new Point2d(-1, 2));
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.addS(new Point2d(-1, 2));
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.addR(-1, 2);
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.addS(-1, 2);
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAdd() {
        testAddR_double_double();
    }

    @Test
    public void testSubR_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.subR(-1, 2);
        TPoint2d expected = new TPoint2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.subS(-1, 2);
        TPoint2d expected = new TPoint2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSub() {
        testSubR_double_double();
    }

    @Test
    public void testMoveR() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.moveR(new Vector2d(-1, 2));
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testMoveS() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2d original = new TPoint2d(1, 2);
        TPoint2d tested = new TPoint2d(original);
        TPoint2d result = tested.moveS(new Vector2d(-1, 2));
        TPoint2d expected = new TPoint2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testTo() {
        System.out.println(test.TestUtils.methodName(0));
        Vector2d result = new TPoint2d(1, 2).to(new Point2d(-1, 2));
        Vector2d expected = new Vector2d(-2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFrom() {
        System.out.println(test.TestUtils.methodName(0));
        Vector2d result = new TPoint2d(1, 2).from(new Point2d(-1, 2));
        Vector2d expected = new Vector2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
