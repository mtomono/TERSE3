/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class TDoubleSupplierNGTest {
    
    public TDoubleSupplierNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSingle() {
        System.out.println("testSingle");
        double result = TDoubleSupplier.f(()->1d).getAsDouble();
        double expected = 1d;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDouble() {
        System.out.println("testDouble");
        double result = TDoubleSupplier.f(()->1d).andThenDouble((double v)->v+1).andThenDouble((double v)->v*2).getAsDouble();
        double expected = 4d;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
