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
public class TSupplierNGTest {
    
    public TSupplierNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSingle() {
        System.out.println("testSingle");
        Integer result = TSupplier.f(()->1).get();
        Integer expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDouble() {
        System.out.println("testDouble");
        Integer result = TSupplier.f(()->1).andThen(v->v+1).andThen(v->v*2).get();
        Integer expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOrder() {
        System.out.println("testOrder");
        String result = TSupplier.f(()->"0").andThen(s->s+"1").andThen(s->s+"2").get();
        String expected = "012";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMix() {
        System.out.println("testMix");
        double result = TSupplier.f(()->2d).andThenDouble(v->v+72).andThenInt(v->(int)v%70).andThenDouble(v->v/2d).getAsDouble();
        double expected = 2d;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
