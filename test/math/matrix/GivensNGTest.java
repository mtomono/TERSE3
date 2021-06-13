/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.C2;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class GivensNGTest {
    
    public GivensNGTest() {
    }

    @Test
    public void testSpin() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                                                         5,4,2,3;
                                                         1,2,3,4;
                                                         2,3,4,5;
                                                         6,5,1,3;
                                                         """);
        var result=Givens.erase(target,TList.sof(3,0),TList.sofi(3,0)).mul(target);
        var expected = CMatrix.derr.b("""
                7.810249675906654, 6.401843996644799, 2.0485900789263356, 4.225217037785567;
                1.0, 2.0, 3.0, 4.0;
                2.0, 3.0, 4.0, 5.0;
                0.0, 0.12803687993289614, -0.8962581595302718, -0.38411063979868754;
                                     """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPlane() {
    }
    
}
