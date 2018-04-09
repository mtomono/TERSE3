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
 * @author masao
 */
public class IntHolderNGTest {
    
    public IntHolderNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testPush() {
        System.out.println(test.TestUtils.methodName(0));
        IntHolder h = new IntHolder(1);
        int result = h.push(h.get()+1);
        int expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        IntHolder h = new IntHolder(1);
        h.push(h.get()+1);
        int result = h.get();
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        IntHolder h = new IntHolder(1);
        int result = h.set(h.get()+1);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
