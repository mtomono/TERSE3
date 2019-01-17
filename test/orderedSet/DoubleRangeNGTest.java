/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

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
        double result = new Range<Double>(0.0,10.0).interpolate(d->d,0.5);
        double expected = 5.0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInterpolate2() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Range<Double>(0.0,10.0).interpolate(d->d,0.2);
        double expected = 2.0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWidth() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Range<Double>(0.0,10.0).width(d->d);
        double expected = 10.0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
