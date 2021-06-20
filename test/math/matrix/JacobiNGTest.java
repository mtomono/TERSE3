/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.C2;
import math.CList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class JacobiNGTest {
    
    public JacobiNGTest() {
    }

    @Test
    public void testNext() {
        System.out.println(test.TestUtils.methodName(0));
        Jacobi<Double,C2<Double>> j=new Jacobi<>(CMatrix.derr.b("""
                                                5,1,2,1;
                                                1,4,2,0;
                                                0,2,4,1;
                                                1,1,1,6;
                                                                """));
        CList<Double,C2<Double>> result = j.target(C2.derr.l("2,2,2,2")).conv(TList.sof(2,2,2,2).toC(v->v.doubleValue(), C2.derr)).limit(80).last();
        CList<Double,C2<Double>> expected = TList.sof(62.0/345,106.0/345,34.0/115,14.0/69).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testNext_threshold() {
        System.out.println(test.TestUtils.methodName(0));
        Jacobi<Double,C2<Double>> j=new Jacobi<>(CMatrix.derr.b("""
                                                5,1,2,1;
                                                1,4,2,0;
                                                0,2,4,1;
                                                1,1,1,6;
                                                                """));
        CList<Double,C2<Double>> result = j.target(C2.derr.l("2,2,2,2")).conv(C2.derr.l("2,2,2,2"),C2.derr.b(5e-10)).last();
        CList<Double,C2<Double>> expected = TList.sof(62.0/345,106.0/345,34.0/115,14.0/69).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
