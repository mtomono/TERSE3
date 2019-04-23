/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import static arithmetic.Calculation.roundAt;
import static java.lang.Math.pow;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CalculationNGTest {
    
    public CalculationNGTest() {
    }

    @Test
    public void testRoundAt() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(249.999999999*pow(10,6));
        double result = roundAt(249.999999934,6);
        double expected = 250.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
