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
public class TVector3dNGTest {
    static double err = 0.000000001;
    public TVector3dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testC() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d result = TVector3d.c(new Point3d(0, 1, 0), new Point3d(5, 4, 0));
        Vector3d expected = new Vector3d(5, 3, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRetval() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.retval(v->v.add(-1, 2, 1));
        Vector3d expected = new Vector3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSelf() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.self(v->v.add(-1, 2, 1));
        Vector3d expected = new Vector3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSubR_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.subR(new Vector3d(-1, 2, 1));
        Vector3d expected = new Vector3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.subS(new Vector3d(-1, 2, 1));
        Vector3d expected = new Vector3d(2, 0, 2);
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
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.subR(-1, 2, 1);
        Vector3d expected = new Vector3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.subS(-1, 2, 1);
        Vector3d expected = new Vector3d(2, 0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.addR(new Vector3d(-1, 2, 1));
        Vector3d expected = new Vector3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_Tuple3d() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.addS(new Vector3d(-1, 2, 1));
        Vector3d expected = new Vector3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAdd() {
        testAddR_3args();
    }

    @Test
    public void testAddR_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.addR(-1, 2, 1);
        Vector3d expected = new Vector3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.addS(-1, 2, 1);
        Vector3d expected = new Vector3d(0, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.scaleR(2);
        Vector3d expected = new Vector3d(2, 4, 6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testScaleS() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 3);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.scaleS(2);
        Vector3d expected = new Vector3d(2, 4, 6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSizeR() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 2);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.sizeR(6);
        Vector3d expected = new Vector3d(2, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSizeS() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 2);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.sizeS(6);
        Vector3d expected = new Vector3d(2, 4, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testNegateR() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 2);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.negateR();
        Vector3d expected = new Vector3d(-1, -2, -2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testNegateS() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 2);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.negateS();
        Vector3d expected = new Vector3d(-1, -2, -2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAngleO() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new TVector3d(1, 0, 0).angleO(new Vector3d(0, 1, 0)).angle();
        double expected = PI/2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCross() {
        System.out.println(test.TestUtils.methodName(0));
        TVector3d result = new TVector3d(1, 0, 0).cross(new Vector3d(0, 1, 0));
        TVector3d expected = new TVector3d(0, 0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNormalizeR() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 2);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.normalizeR();
        Vector3d expected = new Vector3d(1d/3, 2d/3, 2d/3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testNormalizeS() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 2, 2);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.normalizeS();
        Vector3d expected = new Vector3d(1d/3, 2d/3, 2d/3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testTransformR() {
        Matrix3d m = new Matrix3d();
        m.rotZ(PI/2);
        System.out.println(test.TestUtils.methodName(0));
        Vector3d original = new Vector3d(1, 0, 0);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.transformR(m);
        Vector3d expected = new Vector3d(0, 1, 0);
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
        Vector3d original = new Vector3d(1, 0, 0);
        TVector3d tested = new TVector3d(original);
        TVector3d result = tested.transformS(m);
        Vector3d expected = new Vector3d(0, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.epsilonEquals(expected, err));
        assertEquals(tested, result);
    }
    
}
