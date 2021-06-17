/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math.matrix;

import math.C2;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class HouseholderQrAlternativesNGTest {
    
    public HouseholderQrAlternativesNGTest() {
    }

    @Test
    public void testDecompose() {
    }

    @Test
    public void testDecompose_with_accum() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var result=HouseholderQrAlternatives.decompose_with_accum(target);
        var expected = HouseholderQR.decompose(target);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDecompose_with_accum_novar() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var result=HouseholderQrAlternatives.decompose_with_accum_novar(target);
        var expected = HouseholderQR.decompose(target);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDecompose_with_accum_optional() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var result=HouseholderQrAlternatives.decompose_with_accum_optional(target);
        var expected = HouseholderQR.decompose(target);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDecompose_with_next() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var result=HouseholderQrAlternatives.decompose_with_next(target);
        var expected = HouseholderQR.decompose(target);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDecompose_with_for() {
        System.out.println(test.TestUtils.methodName(0));
        CMatrix<Double,C2<Double>> target=CMatrix.derr.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var result=HouseholderQrAlternatives.decompose_with_for(target);
        var expected = HouseholderQR.decompose(target);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
