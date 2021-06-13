/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import math.matrix.CMatrix;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class StringOpNGTest {
    
    public StringOpNGTest() {
    }
    
    @Test
    public void testFormula() {
        System.out.println(test.TestUtils.methodName(0));
        C2<String> result = C2.s.b("a").add(C2.s.b("b"));
        C2<String> expected = C2.s.b("a+b");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testMatrix() {
        System.out.println(test.TestUtils.methodName(0));
        var x=CMatrix.s.b("""
                        5,4,2,3;
                        1,2,3,4;
                        2,3,4,5;
                        6,5,1,3;
                             """);
        var y=CMatrix.create("a", 4, 4);
        var result = x.mul(y);
        var expected = CMatrix.s.b("""
                        0+5*a00+4*a10+2*a20+3*a30, 0+5*a01+4*a11+2*a21+3*a31, 0+5*a02+4*a12+2*a22+3*a32, 0+5*a03+4*a13+2*a23+3*a33;
                        0+1*a00+2*a10+3*a20+4*a30, 0+1*a01+2*a11+3*a21+4*a31, 0+1*a02+2*a12+3*a22+4*a32, 0+1*a03+2*a13+3*a23+4*a33;
                        0+2*a00+3*a10+4*a20+5*a30, 0+2*a01+3*a11+4*a21+5*a31, 0+2*a02+3*a12+4*a22+5*a32, 0+2*a03+3*a13+4*a23+5*a33;
                        0+6*a00+5*a10+1*a20+3*a30, 0+6*a01+5*a11+1*a21+3*a31, 0+6*a02+5*a12+1*a22+3*a32, 0+6*a03+5*a13+1*a23+3*a33
                                  """);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
