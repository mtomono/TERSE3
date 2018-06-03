/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import static java.lang.Math.PI;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.vector2;

/**
 *
 * @author masao
 */
public class TVector2dNGTest {
    static double err = 0.00001;
    
    public TVector2dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testC() {
        System.out.println(test.TestUtils.methodName(0));
        Vector2d result = TVector2d.c(new Point2d(0, 1), new Point2d(5, 4));
        Vector2d expected = new Vector2d(5, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRetval() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d tested = new TVector2d(1, 2);
        Vector2d result = tested.retval(v->v.add(-1, 2));
        Vector2d expected = new Vector2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, new Vector2d(1, 2));
    }

    @Test
    public void testSelf() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d tested = new TVector2d(1, 2);
        Vector2d result = tested.self(v->v.add(-1, 2));
        Vector2d expected = new Vector2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, new Vector2d(0, 4));
    }

    @Test
    public void testSubR_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.subR(new Vector2d(-1, 2));
        Vector2d expected = new Vector2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.subS(new Vector2d(-1, 2));
        Vector2d expected = new Vector2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.addR(new Vector2d(-1, 2));
        Vector2d expected = new Vector2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_Tuple2d() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.addS(new Vector2d(-1, 2));
        Vector2d expected = new Vector2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAddR_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.addR(-1, 2);
        Vector2d expected = new Vector2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testAddS_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.addS(-1, 2);
        Vector2d expected = new Vector2d(0, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAdd() {
        //already tested in testAddR_double_double()
        testAddR_double_double();
    }

    @Test
    public void testSubR_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.subR(-1, 2);
        Vector2d expected = new Vector2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSubS_double_double() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.subS(-1, 2);
        Vector2d expected = new Vector2d(2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSub() {
        //already tested in testSubR_double_double()
        testSubR_double_double();
    }

    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.scaleR(2);
        Vector2d expected = new Vector2d(2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testScaleS() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(1, 2);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.scaleS(2);
        Vector2d expected = new Vector2d(2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testSizeR() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(3, 4);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.sizeR(15);
        Vector2d expected = new Vector2d(9, 12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testSizeS() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(3, 4);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.sizeS(15);
        Vector2d expected = new Vector2d(9, 12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testNegateR() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(3, 4);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.negateR();
        Vector2d expected = new Vector2d(-3, -4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, original);
    }

    @Test
    public void testNegateS() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(3, 4);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.negateS();
        Vector2d expected = new Vector2d(-3, -4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(tested, result);
    }

    @Test
    public void testAngleO() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new TVector2d(1, 0).angleO(new Vector2d(0, 1)).angle();
        double expected = PI/2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);        
    }

    @Test
    public void testDet() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new TVector2d(1, 0).det(new Vector2d(0, 1));
        double expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);        
    }

    @Test
    public void testDet2() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new TVector2d(1, 0).det(new Vector2d(1, 0));
        double expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);        
    }

    @Test
    public void testNormalizeR() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(3, 4);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.normalizeR();
        Vector2d expected = new Vector2d(3d/5, 4d/5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.epsilonEquals(expected, err));
        assertEquals(tested, original);
    }

    @Test
    public void testNormalizeS() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d original = new TVector2d(3, 4);
        TVector2d tested = new TVector2d(original);
        Vector2d result = tested.normalizeS();
        Vector2d expected = new Vector2d(3d/5, 4d/5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertTrue(result.epsilonEquals(expected, err));
        assertEquals(tested, result);
    }

    @Test
    public void testExpand() {
        System.out.println(test.TestUtils.methodName(0));
        Vector3d result = new TVector2d(3, 4).expand();
        Vector3d expected = new Vector3d(3, 4, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testQuadrantCw() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TVector2d> result = vector2(1, 1).quadrant(TVector2d::rotCw);
        TList<TVector2d> expected = TList.of(vector2(1,1),vector2(1,-1),vector2(-1,-1),vector2(-1,1));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotCcw() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d result = vector2(1,1).rotCcw();
        TVector2d expected = vector2(-1,1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotCw() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d result = vector2(1,1).rotCw();
        TVector2d expected = vector2(1,-1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRot() {
        System.out.println(test.TestUtils.methodName(0));
        TVector2d result = vector2(1,1).rot(PI/2);
        TVector2d expected = vector2(-1,1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertTrue(result.epsilonEquals(expected, err));
    }

    @Test
    public void testQuadrant() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TVector2d> result = vector2(1,1).quadrant(TVector2d::rotCcw);
        TList<TVector2d> expected = TList.of(vector2(1,1),vector2(-1,1),vector2(-1,-1),vector2(1,-1));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    

}
