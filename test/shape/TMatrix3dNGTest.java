/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.vector3;

/**
 *
 * @author masao
 */
public class TMatrix3dNGTest {
    
    public TMatrix3dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testRetval() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.retval(v->v.add(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSelf() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.self(v->v.add(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.addR(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.addS(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSubR() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.subR(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Matrix3d expected = new Matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.subS(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Matrix3d expected = new Matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testTransposeR() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.transposeR();
        Matrix3d expected = new Matrix3d(1, 4, 7, 2, 5, 8, 3, 6, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testTransposeS() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.transposeS();
        Matrix3d expected = new Matrix3d(1, 4, 7, 2, 5, 8, 3, 6, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testInvertR() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 0, 0, 0, 1, 0, 0, 0, 1);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.invertR();
        Matrix3d expected = new Matrix3d(1, 0, 0, 0, 1, 0, 0, 0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testInvertS() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 0, 0, 0, 1, 0, 0, 0, 1);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.invertS();
        Matrix3d expected = new Matrix3d(1, 0, 0, 0, 1, 0, 0, 0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testMulR_Matrix3d() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.mulR(new Matrix3d(2, 0, 0, 0, 2, 0, 0, 0, 2));
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testMulS_Matrix3d() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.mulS(new Matrix3d(2, 0, 0, 0, 2, 0, 0, 0, 2));
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testMulR_double() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.mulR(2);
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testMulS_double() {
        System.out.println(test.TestUtils.methodName(0));
        Matrix3d original = new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMatrix3d tested = new TMatrix3d(original);
        TMatrix3d result = tested.mulS(2);
        Matrix3d expected = new Matrix3d(2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testCoordinateTransform() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix3d tested = TMatrix3d.coordinateTransform(new Vector3d(0, 0, 1), new Vector3d(0, 1, 0), new Vector3d(1, 0, 0));
        TVector3d result = tested.transformToVector(new Vector3d(3, 6, 9));
        TVector3d expected = new TVector3d(9, 6, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTransformToVector() {
        testCoordinateTransform();
    }

    @Test
    public void testTransformToPoint() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix3d tested = TMatrix3d.coordinateTransform(new Vector3d(0, 0, 1), new Vector3d(0, 1, 0), new Vector3d(1, 0, 0));
        TPoint3d result = tested.transformToPoint(new Point3d(3, 6, 9));
        TPoint3d expected = new TPoint3d(9, 6, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void maxEigenVector() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix3d tested = new TMatrix3d(1,1,2,0,2,-1,0,0,3);
        TVector3d result = tested.maxEigenVector();
        TVector3d expected = vector3(1,-2,2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.cross(expected).length(), 0.0, 0.0000001);
    }
}
