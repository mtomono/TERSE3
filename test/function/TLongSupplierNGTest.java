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
public class TLongSupplierNGTest {
    
    public TLongSupplierNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSingle() {
        System.out.println("testSingle");
        long result = TLongSupplier.f(()->1L).getAsLong();
        long expected = 1L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDouble() {
        System.out.println("testDouble");
        long result = TLongSupplier.f(()->1L).andThenLong(v->v+1L).andThenLong(v->v*2L).getAsLong();
        long expected = 4L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
