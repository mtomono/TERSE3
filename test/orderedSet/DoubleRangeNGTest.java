/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import math.C;
import math.C2;
import static orderedSet.Builder.doubleRange;
import static orderedSet.CBuilder.doubleRange2;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class DoubleRangeNGTest {
    
    public DoubleRangeNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testInterpolate() {
        System.out.println(test.TestUtils.methodName(0));
        C<Double> result = doubleRange.r(0.0,10.0).interpolate1(d->C.d.b(d),C.d.b(0.5));
        C<Double> expected = C.d.b(5.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    @Test
    public void testInterpolate1() {
        System.out.println(test.TestUtils.methodName(0));
        C2<Double> result = doubleRange2.r(C2.d.b(0.0),C2.d.b(10.0)).interpolate1(d->d,C2.d.b(0.5));
        C2<Double> expected = C2.d.b(5.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInterpolate2() {
        System.out.println(test.TestUtils.methodName(0));
        C<Double> result = doubleRange.r(0.0,10.0).interpolate(d->C.d.b(d),C.d.b(0.2),C.d.b(0.8));
        C<Double> expected = C.d.b(2.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWidth() {
        System.out.println(test.TestUtils.methodName(0));
        C<Double> result = doubleRange.r(0.0,10.0).width(d->C.d.b(d));
        C<Double> expected = C.d.b(10.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
