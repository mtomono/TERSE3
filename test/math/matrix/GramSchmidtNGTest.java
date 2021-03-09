/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import collection.TList;
import math.C2;
import math.CList;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class GramSchmidtNGTest {
    
    public GramSchmidtNGTest() {
    }

    @Test
    public void testOrthogonalizeR2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<CList<Double,C2<Double>>> result = GramSchmidt.orthogonalize(CMatrix.derr.b(
                 "1,0,0;"
                +"1,1,1;"
                +"0,0,1"
        ).rows());
        TList<CList<Double,C2<Double>>> expected = CMatrix.derr.b(
                 "1,0,0;"
                +"0.0,  0.7071067811865475, 0.7071067811865475;"
                +"0.0, -0.7071067811865475, 0.7071067811865475"
        ).rows();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testOrthogonalizeR2Fail() {
        System.out.println(test.TestUtils.methodName(0));
        TList<CList<Double,C2<Double>>> result = GramSchmidt.orthogonalize(CMatrix.derr.b(
                 "1,0,0;"
                +"1,1,1;"
                +"0,0,1"
        ).rows());
        TList<CList<Double,C2<Double>>> expected = CMatrix.derr.b(
                 "1,0,0;"
                +"0.0,  0.7071067811865475, 0.7071067811865475;"
                +"0.0, -0.7071067811865475, 0.70710678"
        ).rows();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertNotEquals(result, expected);
    }
    
    @Test
    public void testOrthogonalize() {
        System.out.println(test.TestUtils.methodName(0));
        TList<CList<Double,C2<Double>>> result = GramSchmidt.orthogonalize(CMatrix.derr.b(
                 "1,0,0;"
                +"1,1,0;"
                +"1,1,1"
        ).rows());
        TList<CList<Double,C2<Double>>> expected = CMatrix.derr.b(
                 "1,0,0;"
                +"0,1,0;"
                +"0,0,1"
        ).rows();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
