/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class FloorNGTest {
    
    public FloorNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testO_int() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new Floor().o(10);
        int expected = 10;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_long() {
        System.out.println(test.TestUtils.methodName(0));
        long result = new Floor().o(10L);
        long expected = 10L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO_double() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Floor().o(10.5);
        double expected = 10;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testO_double2() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Floor().o(10.0);
        double expected = 10;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
