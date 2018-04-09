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
public class TIntSupplierNGTest {
    
    public TIntSupplierNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSingle() {
        System.out.println("testSingle");
        TIntSupplier f = ()->1;
        int result = TIntSupplier.f(()->1).getAsInt();
        int expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDouble() {
        System.out.println("testDouble");
        int result = TIntSupplier.f(()->1).andThenInt((int v)->v+1).andThenInt((int v)->v*2).getAsInt();
        int expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
