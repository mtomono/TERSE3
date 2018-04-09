/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static java.lang.Math.PI;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TPoint3dNGTest {
    static double err = 0.000000001;
    
    public TPoint3dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testRetval() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.retval(v->v.add(-1, 2, 1));
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSelf() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.self(v->v.add(-1, 2, 1));
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSubR_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.subR(new Point3d(-1, 2, 1));
        Point3d expected = new Point3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.subS(new Point3d(-1, 2, 1));
        Point3d expected = new Point3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSub() {
        testSubR_3args();
    }

    @Test
    public void testSubR_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.subR(-1, 2, 1);
        Point3d expected = new Point3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.subS(-1, 2, 1);
        Point3d expected = new Point3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.addR(new Point3d(-1, 2, 1));
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.addS(new Point3d(-1, 2, 1));
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testAddR_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.addR(-1, 2, 1);
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.addS(-1, 2, 1);
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testMoveR() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.moveR(new Vector3d(-1, 2, 1));
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testMoveS() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.moveS(new Vector3d(-1, 2, 1));
        Point3d expected = new Point3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testTo() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TVector3d result = tested.to(new Point3d(-1, 2, 1));
        Point3d expected = new Point3d(-2, 0, -2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testFrom() {
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 2, 3);
        TPoint3d tested = new TPoint3d(original);
        TVector3d result = tested.from(new Point3d(-1, 2, 1));
        Point3d expected = new Point3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testTransformR() {
        Matrix3d m = new Matrix3d();
        m.rotZ(PI/2);
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 0, 0);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.transformR(m);
        Point3d expected = new Point3d(0, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.epsilonEquals(expected, err));
        assertEquals(tested, original);
    }

    @Test
    public void testTransformS() {
        Matrix3d m = new Matrix3d();
        m.rotZ(PI/2);
        System.out.println(test.TestUtils.methodName(0));
        Point3d original = new Point3d(1, 0, 0);
        TPoint3d tested = new TPoint3d(original);
        TPoint3d result = tested.transformS(m);
        Point3d expected = new Point3d(0, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.epsilonEquals(expected, err));
        assertEquals(tested, result);
    }
    
}
