/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.math.BigDecimal;
import static math.Interpolate.interpolateB;
import static math.Interpolate.interpolateD;
import orderedSet.Range;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class InterpolateNGTest {
    
    public InterpolateNGTest() {
    }

    @Test
    public void testInterpolateD() {
        System.out.println(test.TestUtils.methodName(0));
        double result = interpolateD(new Range<>(0D,4D),0.75D);
        double expected = 3D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInterpolateB() {
        System.out.println(test.TestUtils.methodName(0));
        BigDecimal result = interpolateB(new Range<>(new BigDecimal(0),new BigDecimal(4)),new BigDecimal(0.75)).setScale(0);
        BigDecimal expected = new BigDecimal(3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    
}
