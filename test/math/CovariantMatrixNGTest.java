/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.vector2;
import static shape.ShapeUtil.vector3;
import shape.TMatrix3d;
import shape.TVector2d;
import shape.TVector3d;

/**
 *
 * @author masao
 */
public class CovariantMatrixNGTest {
    
    public CovariantMatrixNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    TList<TVector2d> tested2d() {
        return TList.sof(
                vector2(50,50),
                vector2(50,70),
                vector2(80,60),
                vector2(70,90),
                vector2(90,100)
        );
    }
    
    TList<TVector3d> tested3d() {
        return TList.sof(
                vector3(151, 48, 8),
                vector3(164, 53, 11),
                vector3(146, 45, 8),
                vector3(158, 61, 9)
        );
    }

    @Test
    public void testC() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix3d result = new CovariantMatrixSample().matrix3d(tested3d().map(v->TList.set(v)));
        TMatrix3d expected = new TMatrix3d(62.25000, 38.250000, 10.333333,
                                           38.25000, 48.916667,  4.333333,
                                           10.33333,  4.333333,  2.000000);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        result.toList().pair(expected.toList()).forEach(p->assertEquals(p.l(), p.r(), 0.0001));
    }

    @Test
    public void testCrossFromVector() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Double> result = new CovariantMatrixSample().crossFromVector(tested3d().map(v->TList.set(v)));
        TList<Double> expected = TList.sof(62.25000, 38.250000, 10.333333,
                                           38.25000, 48.916667,  4.333333,
                                           10.33333,  4.333333,  2.000000);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        result.pair(expected).forEach(p->assertEquals(p.l(), p.r(), 0.0001));
    }

    @Test
    public void testMatrixFromVector() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Double>> result = new CovariantMatrix().matrixFromVector(tested2d().map(v->TList.set(v)));
        TList<TList<Double>> expected = TList.sof(TList.sof(256D,188D), TList.sof(188D,344D));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void test00() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TVector2d> tested = TList.sof();
    }
    
}
