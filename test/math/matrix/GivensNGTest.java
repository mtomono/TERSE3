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
    public void testPlane() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        1,2;
                        2,1;
                             """);
        TList<Integer> result=Givens.plane(target);
        var expected = TList.sofi(1,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
