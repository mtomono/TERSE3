/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.err;
import static shape.ShapeUtil.vector2;
import shape.TVector2d;

/**
 *
 * @author masao
 */
public class TMatrix2dNGTest {
    
    public TMatrix2dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testM00() {
    }

    @Test
    public void testM01() {
    }

    @Test
    public void testM10() {
    }

    @Test
    public void testM11() {
    }

    @Test
    public void testDet() {
    }

    @Test
    public void testTr() {
    }

    @Test
    public void testAddR() {
    }

    @Test
    public void testScaleR() {
    }

    @Test
    public void testToColumnVectors() {
    }

    @Test
    public void testTransform() {
    }

    @Test
    public void testEigenValues() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix2d tested = new TMatrix2d(4,1,2,3);
        TList<Double> result = tested.eigenValues();
        TList<Double> expected = TList.sof(2.0,5.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEigenVector() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix2d tested = new TMatrix2d(4,1,2,3);
        TList<Double> result = tested.eigenValues().map(d->tested.eigenVector(d)).map(v->v.y/v.x);
        TList<Double> expected = TList.sof(-2.0,1.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMaxEigenVector() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix2d tested = new TMatrix2d(4,1,2,3);
        TVector2d resultx = tested.maxEigenVector();
        double result = resultx.y/resultx.x;
        double expected = 1.0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected, 0.000001);
    }
    
    
}
