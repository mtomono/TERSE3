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
public class GaussSeidelNGTest {
    
    public GaussSeidelNGTest() {
    }

    @Test
    public void testNext() {
        System.out.println(test.TestUtils.methodName(0));
        GaussSeidel<Double,C2<Double>> g=new GaussSeidel<>(CMatrix.derr.b("""
                                                5,1,2,1;
                                                1,4,2,0;
                                                0,2,4,1;
                                                1,1,1,6;
                                                                """));
        CList<Double,C2<Double>> result = g.target(TList.sof(2,2,2,2).toC(v->v.doubleValue(), C2.derr)).conv(C2.derr.l("2,2,2,2")).limit(17).last();
        CList<Double,C2<Double>> expected = TList.sof(62.0/345,106.0/345,34.0/115,14.0/69).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNextSOR() {
        System.out.println(test.TestUtils.methodName(0));
        GaussSeidel<Double,C2<Double>> g=new GaussSeidel<>(CMatrix.derr.b("""
                                                5,1,2,1;
                                                1,4,2,0;
                                                0,2,4,1;
                                                1,1,1,6;
                                                                """));
        CList<Double,C2<Double>> result = g.target(TList.sof(2,2,2,2).toC(v->v.doubleValue(), C2.derr)).conv(C2.derr.l("2,2,2,2"),C2.derr.b(1.07)).limit(14).last();
        CList<Double,C2<Double>> expected = TList.sof(62.0/345,106.0/345,34.0/115,14.0/69).toC(v->v, C2.derr);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
